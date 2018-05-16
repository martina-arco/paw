package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.PlayerDao;
import ar.edu.itba.interfaces.dao.ReceiptDao;
import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.AiService;
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
    @Autowired
    private PlayerDao playerDao;
    @Autowired
    private AiService formationService;
    @Autowired
    private ReceiptDao receiptDao;

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

        for (Player player: team.getPlayers()) {
            salaries += player.getSalary();
        }

        return salaries;
    }

    @Override
    public int getTicketsSold(Team team) {

        int ticketsSold = 0;

        if(team.getFinance() != null) {
            for (Receipt receipt : team.getFinance()) {

                if (receipt.getType() == Receipt.Type.MATCHINCOME)
                    ticketsSold += receipt.getAmount();
            }
        }

        return ticketsSold;
    }

    @Override
    public Team findByUserId(long id) {
        return teamDao.findByUserId(id);
    }

    @Override
    public void setPlayers(Team team) {
        if(team != null){
            team.setPlayers(playerDao.findAdultsByTeamId(team.getId()));
        }
    }

    @Override
    public void setFinance(Team team) {
        if(team != null){
            team.setFinance(receiptDao.findAllByTeamId(team.getId()));
        }
    }

    @Override
    public void setFormation(Team team) {
        if (team != null && team.getPlayers() != null) {
            team.setFormation(formationService.getFormation(team.getPlayers()));
        }
    }


}
