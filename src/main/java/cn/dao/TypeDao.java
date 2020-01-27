package cn.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.domain.Type;

/**
 * TypeDao
 */
public interface TypeDao extends JpaRepository<Type,Long>{
    Type findByName(String name);
    @Query("select t from Type t")
    List<Type>findTop(Pageable pageable);
}