package cn.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.dao.BlogDao;
import cn.domain.Blog;
import cn.domain.Type;
import cn.service.BlogService;

/**
 * BlogServiceImp
 */
@Service
public class BlogServiceImp implements BlogService {

    @Override
    public Blog saveBlog(Blog blog) {
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
                Long id = blog.getType().getId();
                if (id != null)
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), id));
                if (blog.isRecommend())
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                //todo
                return null;
            }
        }, pageable);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteById(id);
    }

    @Autowired
    private BlogDao blogDao;
}