package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.*;
import ar.edu.itba.interfaces.service.LeagueService;
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
        return userDao.create(username, passwordEncoder.encode(password), mail, currentDay);
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
        Calendar cal = Calendar.getInstance();
        Date currentDate = user.getCurrentDay();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, 7);
        Date newDate = cal.getTime();
        user.setCurrentDay(newDate);
        Team team = teamDao.findByUserId(user.getId());
        if(newDate.getMonth() > currentDate.getMonth()) {
            subtractPlayerSalaries(team);
        }
        if(matchDao.findByLeagueIdAndFromDate(team.getLeagueId(), newDate).isEmpty()) {
            League league = leagueDao.findById(team.getLeagueId());
            int higherPoints = -1;
            Team higherTeam = null;
            for (Map.Entry<Team, Integer> entry : leagueService.getTeamPoints(league, currentDate).entrySet())
            {
                if(entry.getValue() > higherPoints) {
                    higherPoints = entry.getValue();
                    higherTeam = entry.getKey();
                }
            }
            int amount = league.getPrize();
            if(higherTeam != null) {
                if (higherTeam.getId() == team.getId()) {
                    receiptDao.create(team, amount, Receipt.Type.TOURNAMENTPRIZE);
                    team.addMoney(amount);
                }
            }
        }
        userDao.save(user);
    }

    private void subtractPlayerSalaries(Team team) {
        int amount = team.getSalaries();
        receiptDao.create(team, amount, Receipt.Type.PLAYERSSALARIES);
        team.addMoney(-amount);
    }
}