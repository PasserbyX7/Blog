package cn.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cn.domain.Blog;

/**
 * BlogDao
 */
public interface BlogDao extends JpaRepository<Blog,Long>,JpaSpecificationExecutor<Blog>{
    List<Blog> findByRecommendTrue(Pageable pageable);
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog>findByQuery(Pageable pageable,String query);
    Page<Blog>findByPublishTrue(Pageable pageable);
    List<Blog> findAllByOrderByCreateTimeDesc();
    @Transactional
    @Modifying
    @Query("update Blog b set b.viewNum=b.viewNum+1 where b.id=?1")
    int updateViewNum(Long id);
    
}