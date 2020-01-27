package cn.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;

import cn.domain.Blog;
import cn.domain.Tag;

/**
 * BlogService
 */
public interface BlogService {

    Blog saveBlog(Blog blog);
    Blog getBlog(Long id);
    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listBlog(Pageable pageable,Tag tag);
    Page<Blog> listBlog(Pageable pageable,Blog blog);
    Page<Blog> listBlog(Pageable pageable,String query);
    List<Pair<Integer,List<Blog>>>listBlogByArchive();
    List<Blog> listTopBlog(Integer num);
    void deleteBlog(Long id);
    Long getTotalNum();
}