package cn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.dao.TagDao;
import cn.domain.Tag;
import cn.domain.User;
import cn.service.UserService;


/**
 * saveTest
 */


@SpringBootTest
@WebAppConfiguration
public class saveTest {

    @Test
    public void test() {
        // Tag tag=new Tag();
        // tag.setId(1L);
        // tag.setName("hi");
        // tagDao.save(tag);
        // User user=new User();
        // user.getAvatar();
        // user.getNickName();
        User user=userService.checkUser("passerby", "cao981127");
        System.out.println(user);
    }
    @Autowired
    TagDao tagDao;
    @Autowired
    UserService userService;
}