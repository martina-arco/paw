package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;

import java.util.Date;

public interface UserService {

    User findById(int id);

    User findByUsername(String username);

    User create(String username, String password, String mail, Date currentDay);

    void setTeam(User user, Team team);

    void advanceDate(User user);

    String getCurrentDay(User user);

    Date getPreviousDate(User user);
}