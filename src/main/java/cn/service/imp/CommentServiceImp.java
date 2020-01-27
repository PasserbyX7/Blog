package cn.service.imp;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.CommentDao;
import cn.domain.Comment;
import cn.service.CommentService;

/**
 * CommentServiceImp
 */
@Service
public class CommentServiceImp implements CommentService {

    @Override
    public List<Comment> listCommentByBlogId(Long id) {
        Sort sort=Sort.by(Sort.Direction.DESC, "createTime");
        return commentDao.findByBlogId(id, sort);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        comment.setParentComment(commentDao.findById(comment.getParentComment().getId()).orElse(null));
        comment.setCreateTime(LocalDateTime.now() );
        return commentDao.save(comment);
    }
    @Autowired
    CommentDao commentDao;
    
}