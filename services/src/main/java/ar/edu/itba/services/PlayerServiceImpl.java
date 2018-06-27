package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.PlayerDao;
import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private TeamDao teamDao;

    @Override
    public Player findById(List<Player> players, long id) {
        if(players == null)
            return null;
        for(Player player : players){
            if(player.getId() == id)
                return player;
        }
        return null;
    }

    @Override
    public Player create(String name, Team team, int age, int value, int potential, int skillLevel, int goalkeeping,
                         int finish, int defending, int passing, int fitness, int salary, Date contractExpiration, boolean youth) {
        return playerDao.create(name, team, age, value, potential, skillLevel, goalkeeping, finish, defending, passing,
                fitness, salary, contractExpiration, youth);
    }

    @Override
    public Player findById(long id) {
        return playerDao.findById(id);
    }

    @Override
    public void retire(Player player) {
        Team team = player.getTeam();
        team.removePlayer(player);
        teamDao.save(team);
    }

    @Override
    public List<Player> getPlayers(Team team) {
        return playerDao.findAdultsByTeamId(team.getId());
    }
}
