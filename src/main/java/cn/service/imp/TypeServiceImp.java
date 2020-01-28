package cn.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.TypeDao;
import cn.domain.Type;
import cn.service.TypeService;

/**
 * TypeServiceImp
 */
@Service
public class TypeServiceImp implements TypeService {

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeDao.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.findById(id).orElse(null);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeDao.findAll(pageable);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.findByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeDao.findAll();
    }

    @Override
    public List<Type> listTopType(Integer num) {
        Sort sort=Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable=PageRequest.of(0,num,sort);
        return typeDao.findTop(pageable);
    }

    @Override
    public Long getTotalNum() {
        return typeDao.count();
    }

    @Autowired
    private TypeDao typeDao;

}