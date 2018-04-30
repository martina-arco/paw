package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.UserDao;
import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User create(String username) {
        return userDao.create(username);
    }

    @Override
    public User create(String username, String password) {
        return userDao.create(username, password);
    }
}