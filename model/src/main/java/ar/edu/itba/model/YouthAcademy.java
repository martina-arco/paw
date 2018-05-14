package ar.edu.itba.model;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class YouthAcademy {
    private List<Player> players;
    private Team team;
    private long id;

    public YouthAcademy(long id, Team team, List<Player> players){
        this.id = id;
        this.team = team;
        this.players = new ArrayList<>();
        this.players.addAll(players);
    }

    public List<Player> getPlayers(){
        return players;
    }

    public Team getTeam(){
        return team;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
