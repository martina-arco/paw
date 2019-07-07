package ar.edu.itba.webapp.model.DTOs;

import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class UserDTO {
    private long id;
    private String username;
    private String password;
    private String mail;
    private TeamShortDTO team;
    private Date currentDay;


    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.mail = user.getMail();
        this.team = new TeamShortDTO(user.getTeam());
        this.currentDay = user.getCurrentDay();
    }

    public UserDTO(long id, String username, String password, String mail, Team team, Date currentDay) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.team = new TeamShortDTO(team);
        this.currentDay = currentDay;
    }

    public UserDTO(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public TeamShortDTO getTeam() {
        return team;
    }

    public void setTeam(TeamShortDTO team) {
        this.team = team;
    }

    public Date getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(Date currentDay) {
        this.currentDay = currentDay;
    }
}