package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.User;

public interface UserService {

    User findById(int id);

    User findByUsername(String username);

    User create(String username, String password);
}