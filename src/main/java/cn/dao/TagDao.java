package cn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.domain.Tag;

/**
 * TagDao
 */
public interface TagDao extends JpaRepository<Tag,Long>{
    Tag findByName(String name);
}