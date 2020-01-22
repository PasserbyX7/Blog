package cn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.domain.User;

/**
 * UserDao
 */
public interface UserDao extends JpaRepository<User,Long>{
    User findByUsernameAndPassword(String username,String password);
}