package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.*;

import java.util.List;

public interface TeamDao {

    Team create(String name, League league, Stadium stadium, Formation formation, List<Player> players,
                List<Player> youthAcademy, int fanCount, int fanTrust, int boardTrust, int money);

    Team create(String name, long league, long stadium, long formation, int fanCount, int fanTrust, int boardTrust, int money);

    boolean save(Team team);

    Team findById(long id);

    List<Team> findAllByLeagueId(long id);
}
