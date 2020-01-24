package cn.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private TypeDao typeDao;
}