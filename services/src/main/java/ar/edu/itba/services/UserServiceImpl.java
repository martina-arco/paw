package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.*;
import ar.edu.itba.interfaces.service.EconomyService;
import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static java.util.Calendar.MONTH;

@Service
@Transactional
public class UserServiceImpl implements UserService {

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
        Team team = teamService.findByUserId(user.getId());
        
        if(isNextMonth(user.getCurrentDay())) {
            economyService.submitReceipt(team, Receipt.Type.PLAYERSSALARIES, -team.getSalaries());
        }

        if(leagueService.isLastMatch(league, user)){
            leagueService.finish(league, user);
            user.setCurrentDay(advanceWeeks(user.getCurrentDay(), 54 - 38));
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

        return cal.get(MONTH) < aux.get(MONTH);
    }

    private Date advanceWeeks(Date date, int amount){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7 * amount);
        return cal.getTime();
    }
}