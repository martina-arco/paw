package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.User;

public interface UserService {

    User findById(int id);

    /**
     * Create a new user.
     *
     * @param username The name of the user.
     * @return The created user.
     */
    User create(String username);

    User create(String username, String password);
}