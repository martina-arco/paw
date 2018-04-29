package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.UserDao;
import ar.edu.itba.interfaces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
}