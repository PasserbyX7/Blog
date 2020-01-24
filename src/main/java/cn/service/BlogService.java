package cn.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.domain.Blog;

/**
 * BlogService
 */
public interface BlogService {

    Blog saveBlog(Blog blog);
    Blog getBlog(Long id);
    Page<Blog> listBlog(Pageable pageable,Blog blog);
    void deleteBlog(Long id);

}