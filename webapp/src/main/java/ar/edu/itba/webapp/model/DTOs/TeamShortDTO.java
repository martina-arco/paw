package ar.edu.itba.webapp.model.DTOs;

import ar.edu.itba.model.Team;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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

    public long getId() {
        return id;
    }

    public long getLeagueId() {
        return leagueId;
    }

    public long getStadiumId() {
        return stadiumId;
    }

    public long getFormationId() {
        return formationId;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    public void setStadiumId(long stadiumId) {
        this.stadiumId = stadiumId;
    }

    public void setFormationId(long formationId) {
        this.formationId = formationId;
    }

    public void setName(String name) {
        this.name = name;
    }
}