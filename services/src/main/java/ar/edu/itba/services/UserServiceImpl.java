package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.*;
import ar.edu.itba.interfaces.service.EconomyService;
import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Receipt;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.MONTH;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final int WEEKSINYEAR = 52;

    @Autowired
    private UserDao userDao;
    @Autowired
    private LeagueDao leagueDao;
    @Autowired
    private MatchDao matchDao;
    @Autowired
    private ReceiptDao receiptDao;
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private TeamService teamService;

    @Autowired
    private EconomyService economyService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User create(String username, String password, String mail, Date currentDay) {
        User user = userDao.create(username, passwordEncoder.encode(password), mail, currentDay);
        leagueService.generateFixture(user, leagueService.findByUser(user).get(0));
        return user;
    }

    @Override
    public void setTeam(User user, Team team) {
        user.setTeam(team);

        userDao.save(user);
    }

    @Override
    public String getCurrentDay(User user) {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM YYY");

        return format.format(user.getCurrentDay());
    }

    @Override
    public void advanceDate(User user) {
        League league = leagueService.findByUser(user).get(0);
        List<Team> teams = teamService.findByLeague(league);
        
        if(isNextMonth(user.getCurrentDay())) {
            for(Team team : teams)
                economyService.submitReceipt(team, Receipt.Type.PLAYERSSALARIES, -team.getSalaries());
        }

        if(leagueService.isLastMatch(league, user)){
            leagueService.finish(league, user);
            user.setCurrentDay(advanceWeeks(user.getCurrentDay(), WEEKSINYEAR - league.getFixture().size()));
            leagueService.generateFixture(user, league);
        } else {
            user.setCurrentDay(advanceWeeks(user.getCurrentDay(), 1));
        }

        userDao.save(user);
    }


    private boolean isNextMonth(Date date){
        Calendar cal = Calendar.getInstance(), aux = Calendar.getInstance();
        cal.setTime(date);
        aux.setTime(date);
        cal.add(Calendar.DATE, 7);

        return cal.get(MONTH) != aux.get(MONTH);
    }

    private Date advanceWeeks(Date date, int amount){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7 * amount);
        return cal.getTime();
    }
}