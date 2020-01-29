package cn.service;

import java.util.List;

import cn.domain.Comment;

/**
 * CommentService
 */
public interface CommentService {
    List<Comment>listCommentByBlogId(Long id);
    Comment getComment(Long id);
    Comment saveComment(Comment comment);
    void deleteComment(Long id);
    void deleteCommentByBlogId(Long id);
}