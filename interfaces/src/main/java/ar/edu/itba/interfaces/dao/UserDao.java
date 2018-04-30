package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.User;

public interface UserDao {

    User findById(long id);

    User create(String username);

    User create(String username, String password);
}