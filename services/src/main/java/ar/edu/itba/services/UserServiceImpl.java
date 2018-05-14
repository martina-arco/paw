package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.UserDao;
import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public User create(String username, String password, String mail, Date currentDay) {
        return userDao.create(username, passwordEncoder.encode(password), mail, currentDay);
    }

    @Override
    public void setTeam(Team team) {
//        user.setTeam(team);
//        userDao.save(user);
    }
}