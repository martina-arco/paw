package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.*;

import java.util.List;

public interface TeamService {

    Team create(String name, League league, Stadium stadium, Formation formation, List<Player> players, List<Player> youthAcademy, int fanCount, int fanTrust, int boardTrust, int money);

    Team findById(long id);

    List<Team> findByLeague(League league);

    int getSalaries(Team team);

    int getTicketsSold(Team team);

    Team findByUserId(long id);

    void setPlayers(Team team);

    void setFormation(Team team);

}
