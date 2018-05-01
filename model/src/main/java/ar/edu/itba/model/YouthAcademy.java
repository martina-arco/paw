package ar.edu.itba.model;

import java.util.ArrayList;
import java.util.List;

public class YouthAcademy {
    private List<Player> players;
    private Team team;

    public YouthAcademy(Team team){
        this.team = team;
    }

    public YouthAcademy(Team team, List<Player> players){
        this(team);
        this.players = new ArrayList<>();
        this.players.addAll(players);
    }

    public List<Player> getPlayers(){
        return players;
    }

    public Team getTeam(){
        return team;
    }
}
