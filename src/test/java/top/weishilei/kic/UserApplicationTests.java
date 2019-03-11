package top.weishilei.kic;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.weishilei.bean.User;
import top.weishilei.mapper.UserMapper;

public class UserApplicationTests extends KicApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //@Test
    public void testUserSelectAll() {
        System.out.println(userMapper.selectAllUser());
    }

    //@Test
    public void testUserSelectUserById() {
        System.out.println(userMapper.selectUserById(1));
    }

    //@Test
    public void testUserSelectUserByUsername() {
        System.out.println(userMapper.selectUserByUsername("1"));
    }

    //@Test
    public void testUserInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setName("test");
        user.setQuestionId(1);
        user.setAnswer("test");
        System.out.println(userMapper.insertUser(user));
        System.out.println(user.getId());
    }

    @Test
    public void testUserUpdateUserPassword() {
        System.out.println(userMapper.updateUserPasswordByUsername("non2587", "qqwei2587"));
    }

}
