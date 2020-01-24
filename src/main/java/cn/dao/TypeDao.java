package cn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.domain.Type;

/**
 * TypeDao
 */
public interface TypeDao extends JpaRepository<Type,Long>{
    Type findByName(String name);
}