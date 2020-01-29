package cn.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.domain.Comment;

/**
 * CommentDao
 */
public interface CommentDao extends JpaRepository<Comment,Long>{
    List<Comment>findByBlogId(Long id,Sort sort);
    List<Comment>findByParentCommentIdEquals(Long id);
    List<Comment>findByBlogIdEquals(Long id);
}