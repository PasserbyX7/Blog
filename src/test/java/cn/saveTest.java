package cn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StringUtils;

import cn.dao.TagDao;
import cn.domain.Blog;
import cn.domain.Tag;
import cn.service.TagService;
import cn.service.UserService;
import cn.util.MD5Util;


/**
 * saveTest
 */


// @SpringBootTest
// @WebAppConfiguration
public class saveTest {

    @Test
    public void test() {
        System.out.println("-------------------------");
        Blog blog=new Blog();
        // Tag tag1=new Tag();
        // tag1.setId(1L);
        // Tag tag2=new Tag();
        // tag2.setId(2L);
        // Tag tag3=new Tag();
        // tag3.setId(3L);
        // blog.getTags().add(tag1);
        // blog.getTags().add(tag2);
        // blog.getTags().add(tag3);
        // //List<Long>[1,2,3]->"1,2,3"
        // List<String> result=blog.getTags().stream().map(Tag::getId).map(String::valueOf).collect(Collectors.toList());
        // System.out.println(String.join(",", result));
        String tagIds="";
        blog=null;
        String.join(",", blog.getTags().stream().map(Tag::getId).map(String::valueOf).collect(Collectors.toList()));
        // Arrays.asList(tagIds.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
        // System.out.println(Arrays.asList(tagIds.split(",")).stream().map(Long::parseLong).collect(Collectors.toList()));
        // tagService.listTag(Arrays.asList(tagIds.split(",")).stream().map(Long::parseLong).collect(Collectors.toList()));
    }
    // @Autowired
    // TagDao tagDao;
    // @Autowired
    // UserService userService;
    // @Autowired
    // TagService tagService;
}