package cn.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.domain.Tag;

/**
 * TagService
 */
public interface TagService {

    Tag saveTag(Tag tag);
    Tag getTag(Long id);
    Tag getTagByName(String name);
    Page<Tag> listTag(Pageable pageable);
    void deleteTag(Long id);
}