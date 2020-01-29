package cn.service;

import java.util.List;

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
    List<Tag> listTopTag(Integer num);
    List<Tag> listTag();
    Long getTotalNum();
    void deleteTag(Long id);
    boolean containsTag(String tagName);
    String TagListToString(List<Tag> tags);
    List<Tag> StringToTagList(String tagIds);
}