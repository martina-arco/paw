package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;

public interface UserService {

    User findById(int id);

    User findByUsername(String username);

    User create(String username, String password, String mail);

    void setTeam(Team team);
}