package cn.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.domain.Tag;

/**
 * TagDao
 */
public interface TagDao extends JpaRepository<Tag,Long>{
    Tag findByName(String name);
    @Query("select t from Tag t")
    List<Tag>findTop(Pageable pageable);
}