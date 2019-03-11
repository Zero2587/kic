package top.weishilei.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weishilei.bean.User;
import top.weishilei.mapper.UserMapper;

/**
 * @author weishilei
 * 用户服务层
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名判断用户是否存在
     * @param username
     * @return
     */
    public boolean isUserExists(String username) {
        User user = userMapper.selectUserByUsername(username);
        return user != null;
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    public User addUser(User user) {
        if (user == null) {
            return null;
        }

        int successNum = userMapper.insertUser(user);
        if (successNum < 1) {
            return null;
        }

        return user;
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    public User findUserById(Integer id) {
        if (id == null) {
            return null;
        }

        return userMapper.selectUserById(id);
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findUserByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }

        return userMapper.selectUserByUsername(username);
    }

    /**
     * 根据用户名修改密码
     * @param username
     * @param password
     * @return
     */
    public boolean updateUserPasswordByUsername(String username, String password) {
        return userMapper.updateUserPasswordByUsername(username, password) > 0;
    }

    /**
     * 根据用户id修改头像
     * @param id
     * @param image
     * @return
     */
    public boolean updateUserImageById(Integer id, String image) {
        return userMapper.updateUserImageById(id, image) > 0;
    }
}
