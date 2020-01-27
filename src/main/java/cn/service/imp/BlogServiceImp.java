package cn.service.imp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.BlogDao;
import cn.domain.Blog;
import cn.domain.Tag;
import cn.domain.Type;
import cn.service.BlogService;
import cn.util.MarkdownUtils;

/**
 * BlogServiceImp
 */
@Service
public class BlogServiceImp implements BlogService {

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if(blog.getId()==null)
            blog.setCreateTime(LocalDateTime.now());
        if (blog.getViewNum() == null)
            blog.setViewNum(0);
        String description=MarkdownUtils.markdownToText(blog.getContent());
        if(description.length()>150)
            description=description.substring(0,150);
        blog.setDescription(description);
        blog.setUpdateTime(LocalDateTime.now());
        return blogDao.save(blog);
    }

    @Override
    public Blog getBlog(Long id) {
        return blogDao.findById(id).orElse(null);
    }

    @Override // 分页动态组合查询
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {
        return blogDao.findAll(new Specification<Blog>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                String title = blog.getTitle();
                if (title != null && !title.isEmpty())
                    predicates.add(cb.like(root.<String>get("title"), "%" + title + "%"));
                Long id = null;
                if (blog.getType() != null)
                    id = blog.getType().getId();
                if (id != null)
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), id));
                if (blog.isRecommend())
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogDao.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Tag tag) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join join=root.join("tags");
                return cb.equal(join.get("id"), tag.getId());
			}
            private static final long serialVersionUID = 1L;
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, String query) {
        return blogDao.findByQuery(pageable, "%" + query + "%");
    }

    @Override
    public List<Pair<Integer,List<Blog>>> listBlogByArchive() {
        LinkedList<Pair<Integer,List<Blog>>>list=new LinkedList<>();
        List<Blog>blogs=blogDao.findAll();
        Map<Integer,List<Blog>>map=new TreeMap<>();
        if(blogs!=null&&!blogs.isEmpty())
            for (Blog blog : blogs) {
                int year=blog.getCreateTime().getYear();
                if(!map.containsKey(year))
                    map.put(year, new LinkedList<Blog>());        
                map.get(year).add(blog);
            }
        map.forEach((k,v)->list.addFirst(Pair.of(k,v)));
        return list;
    }

    @Override
    public Long getTotalNum() {
        return blogDao.count();
    }

    @Override
    public List<Blog> listTopBlog(Integer num) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, num, sort);
        return blogDao.findTop(pageable);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteById(id);
    }

    @Autowired
    private BlogDao blogDao;
}