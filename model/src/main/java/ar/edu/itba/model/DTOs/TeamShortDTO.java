package ar.edu.itba.model.DTOs;

import ar.edu.itba.model.Team;

import java.util.List;

public class TeamShortDTO {
    private long id;
    private long leagueId, stadiumId, formationId;
    private String name;

    public TeamShortDTO(Team team) {
        this.id = team.getId();
        this.leagueId = team.getLeagueId();
        this.stadiumId = team.getStadiumId();
        this.formationId = team.getFormationId();
        this.name = team.getName();
    }
}