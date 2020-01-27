package cn.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.UserDao;
import cn.domain.User;
import cn.service.UserService;
import cn.util.MD5Utils;

/**
 * UserServiceImp
 */
@Service
public class UserServiceImp implements UserService {

    @Override
    public User checkUser(String username, String password) {
        return userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
    }
    @Autowired
    private UserDao userDao;
}