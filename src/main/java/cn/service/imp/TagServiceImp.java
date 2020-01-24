package cn.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.dao.TagDao;
import cn.domain.Tag;
import cn.service.TagService;

/**
 * TagServiceImp
 */
@Service
public class TagServiceImp implements TagService {

    @Override
    public Tag saveTag(Tag tag) {
        return tagDao.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.findById(id).orElse(null);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagDao.findAll(pageable);
    }

    @Override
    public void deleteTag(Long id) {
        tagDao.deleteById(id);
    }
    @Autowired
    private TagDao tagDao;
}