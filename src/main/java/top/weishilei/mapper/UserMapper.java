package top.weishilei.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.weishilei.bean.User;

import java.util.List;

/**
 * @author weishilei
 * 用户持久层
 */
@Mapper
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAllUser();

    /**
     * 根据用户的id查询用户
     * @param id
     * @return
     */
    User selectUserById(Integer id);

    /**
     * 根据用户的用户名查询用户
     * @param username
     * @return
     */
    User selectUserByUsername(String username);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据用户的用户名修改密码
     * @param username
     * @param password
     * @return
     */
    int updateUserPasswordByUsername(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户到id修改头像
     * @param id
     * @param image
     * @return
     */
    int updateUserImageById(@Param("id") Integer id, @Param("image") String image);
}
