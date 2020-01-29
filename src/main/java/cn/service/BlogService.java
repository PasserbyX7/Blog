package cn.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;

import cn.domain.Blog;
import cn.domain.Tag;
import cn.domain.Type;

/**
 * BlogService
 */
public interface BlogService {

    Blog saveBlog(Blog blog);
    Blog getBlog(Long id);
    Page<Blog> listPublishedBlog(Pageable pageable);
    Page<Blog> listBlogByTag(Pageable pageable,Tag tag);
    Page<Blog> listBlogByType(Pageable pageable,Type type);
    Page<Blog> listBlog(Pageable pageable,Blog blog);
    Page<Blog> listBlog(Pageable pageable,String query);
    Blog BlogContentToHtml(Blog blog);
    List<Pair<Integer,List<Blog>>>listBlogByArchive();
    List<Blog> listTopBlog(Integer num);
    void deleteBlog(Long id);
    Long getTotalNum();
}