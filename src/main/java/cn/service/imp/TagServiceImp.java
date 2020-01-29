package cn.service.imp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.TagDao;
import cn.domain.Tag;
import cn.service.TagService;

/**
 * TagServiceImp
 */
@Service
public class TagServiceImp implements TagService {

    @Transactional
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
    public List<Tag> listTopTag(Integer num) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, num, sort);
        return tagDao.findTop(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.findAll();
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagDao.deleteById(id);
    }

    @Override
    public Long getTotalNum() {
        return tagDao.count();
    }

    @Override
    public boolean containsTag(String tagName) {
        return getTagByName(tagName) != null;
    }

    @Override
    public List<Tag> StringToTagList(String tagIds) {
        //将形如"13,23"的字符串转为[13,23]这样的list
        List<Long> ids = Arrays.asList(tagIds.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
        return tagDao.findAllById(ids);
    }

    @Override
    public String TagListToString(List<Tag> tags) {
        //将id为13、23的tags转换形如"13,23"这样的字串
        return String.join(",", tags.stream().map(Tag::getId).map(String::valueOf).collect(Collectors.toList()));
    }

    @Autowired
    private TagDao tagDao;
}