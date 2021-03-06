package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.User;

import java.util.Date;

public interface UserDao {

    User findById(long id);

    User findByUsername(String username);

    User create(String username, String password, String mail, Date currentDay);

    boolean save(User user);
}