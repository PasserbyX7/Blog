package cn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.dao.TypeDao;
import cn.domain.Type;
import cn.service.TypeService;

/**
 * saveTest
 */


@SpringBootTest
@WebAppConfiguration
public class saveTest {

    // @Test
    // public void test(){
    //     Type type=new Type();
    //     type.setId(2L);
    //     type.setName("hihi");
    //     typeService.updateType(2L, type);
    // }
    // @Test
    // public void test1(){
    //     Type type=new Type();
    //     type.setId(1L);
    //     type.setName("hehe");
    //     typeDao.save(type);
    // }
    @Autowired
    private TypeService typeService;
    @Autowired
    private TypeDao typeDao;
}