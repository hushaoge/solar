package user;

import com.solar.services.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by hushaoge on 2018/3/19.
 */
@Test
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:data/SpringData.xml"
})
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public UserInfoService userInfoService;

    public void testUser(){
        userInfoService.toString();
    }
}
