package cn.service;

import cn.domain.User;

/**
 * UserService
 */
public interface UserService {

    User checkUser(String username,String password);
}