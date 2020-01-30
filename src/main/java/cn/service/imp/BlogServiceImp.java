package cn.service.imp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
import cn.service.CommentService;
import cn.util.MarkdownUtils;

/**
 * BlogServiceImp
 */
@Service
public class BlogServiceImp implements BlogService {

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if(blog.getId()==null){//如果是新增，新建创建时间和浏览次数
            blog.setCreateTime(LocalDateTime.now());
            blog.setViewNum(0);
        }else{//如果是修改，继承原blog的创建时间和浏览次数
            Blog originBlog=getBlog(blog.getId());
            blog.setCreateTime(originBlog.getCreateTime());
            blog.setViewNum(originBlog.getViewNum());
        }
        //为blog增加简介
        String description=MarkdownUtils.markdownToText(blog.getContent());
        if(description.length()>150)
            description=description.substring(0,150);
        blog.setDescription(description);
        //设置更新时间
        blog.setUpdateTime(LocalDateTime.now());
        return blogDao.save(blog);
    }

    @Transactional
    @Override
    public Blog getBlog(Long id) {
        blogDao.updateViewNum(id);
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
    public Page<Blog> listPublishedBlog(Pageable pageable) {
        return blogDao.findByPublishTrue(pageable);
    }

    @Override
    public Page<Blog> listBlogByTag(Pageable pageable, Tag tag) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.join("tags").get("id"), tag.getId());
			}
            private static final long serialVersionUID = 1L;
        },pageable);
    }

    @Override
    public Page<Blog> listBlogByType(Pageable pageable, Type type) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.join("type").get("id"), type.getId());
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
        return blogDao.findByRecommendTrue(pageable);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        commentService.deleteCommentByBlogId(id);
        blogDao.deleteById(id);
    }

    @Override
    public Blog BlogContentToHtml(Blog blog) {
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        return blog;
    }

    @Autowired
    private BlogDao blogDao;
    @Autowired
    private CommentService commentService;
}