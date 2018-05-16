package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Override
    @Transactional
    public Team create(String name, League league, Stadium stadium, Formation formation, List<Player> players, List<Player> youthAcademy, int fanCount, int fanTrust, int boardTrust, int money) {
        return teamDao.create(name, league, stadium, formation, players, youthAcademy, fanCount, fanTrust, boardTrust, money);
    }

    @Override
    public Team findById(long id) {
        return teamDao.findById(id);
    }

    @Override
    public List<Team> findByLeague(League league) {
        return teamDao.findAllByLeagueId(league.getId());
    }

    @Override
    public int getSalaries(Team team) {
        int salaries = 0;

//        for (Player player:team.getPlayers()) {
//            salaries += player.getSalary();
//        }

        return salaries;
    }

    @Override
    public int getTicketsSold(Team team) {

        int ticketsSold = 0;

//        for (Receipt receipt:team.getFinance()) {
//
//            if(receipt.getType() == Receipt.Type.MATCHINCOME)
//                ticketsSold += receipt.getAmount();
//        }

        return ticketsSold;
    }

    @Override
    public Team findByUserId(long id) {
        return teamDao.findByUserId(id);
    }
}
