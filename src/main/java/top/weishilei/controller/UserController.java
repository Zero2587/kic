package top.weishilei.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import top.weishilei.bean.User;
import top.weishilei.result.Result;
import top.weishilei.result.ResultCode;
import top.weishilei.service.UserService;
import top.weishilei.util.QynImageUtil;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weishilei
 * 用户控制层
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    private Map<String, Integer> question = new HashMap<>();

    public UserController() {
        question.put("你父亲的名字？", 1);
        question.put("你母亲的名字？", 2);
        question.put("你的出生地在哪里？", 3);
        question.put("你小学班主任老师的名字？", 4);
        question.put("你初中班主任老师的名字？", 5);
        question.put("你高中班主任老师的名字？", 6);
    }

    /**
     * 用户登录页面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin() {
        return new ModelAndView("user/login");
    }

    /**
     * 用户注册页面
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        ModelAndView modelAndView = new ModelAndView("user/register");
        modelAndView.addObject("registerQuestion", this.question);

        return modelAndView;
    }

    /**
     * 用户重置密码页面
     * @return
     */
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView showReset() {
        ModelAndView modelAndView = new ModelAndView("user/reset");
        modelAndView.addObject("resetQuestion", this.question);

        return modelAndView;

    }

    /**
     * 用户中心页面
     * @return
     */
    @RequestMapping(value = "/center", method = RequestMethod.GET)
    public ModelAndView showCenter() {
        return new ModelAndView("user/center");
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("user") String username, @RequestParam("pwd") String password) {
            Result result = new Result(ResultCode.RESPONSE_LOGIN_FAIL);
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return result.toJSONOString();
        }

        User user = userService.findUserByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            super.getSession().setAttribute("loginUser", user);
            result.setResultCode(ResultCode.RESPONSE_SUCCESS);
        }

        return result.toJSONOString();
    }

    /**
     * 用户注册
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestBody JSONObject data) {
        Result result = new Result();
        if (!data.containsKey("username") || !data.containsKey("password") || !data.containsKey("name")
            || !data.containsKey("question") || !data.containsKey("answer")) {
            result.setResultCode(ResultCode.RESPONSE_REGISTER_PARMTER_IS_EMPTY);
            return result.toJSONOString();
        }

        String username = data.getString("username");
        if (userService.isUserExists(username)) {
            result.setResultCode(ResultCode.RESPONSE_REGISTER_USER_EXISTS);
            return result.toJSONOString();
        }

        int questionId = this.question.get(data.getString("question"));
        data.remove("question");
        data.put("questionId", questionId);
        User user = null;
        try {
            user = JSONObject.toJavaObject(data, User.class);
            user = userService.addUser(user);
        } catch (Exception e) {}

        if (user.getId() > 0) {
            result.setResultCode(ResultCode.RESPONSE_SUCCESS);
        }

        return result.toJSONOString();
    }

    /**
     * 修改用户密码
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public String reset(String username, String password, String question, String answer) {
        Result result = new Result(ResultCode.RESPONSE_RESET_FAIL);

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(answer)) {
            result.setResultCode(ResultCode.RESPONSE_RESET_PARMTER_IS_EMPTY);
            return result.toJSONOString();
        }

        User user = userService.findUserByUsername(username);
        if (user == null) {
            result.setResultCode(ResultCode.RESPONSE_RESET_NOT_EXISTS);
            return result.toJSONOString();
        }

        boolean flag = false;
        int questionId = this.question.get(question);
        if (questionId == user.getQuestionId() && answer.equals(user.getAnswer())) {
            flag = userService.updateUserPasswordByUsername(username, password);
        }

        if (flag) {
            result.setResultCode(ResultCode.RESPONSE_SUCCESS);
        }

        return result.toJSONOString();
    }

    /**
     * 用户退出
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        super.getSession().removeAttribute("loginUser");

        return new ModelAndView("index");
    }

    /**
     * 修改用户头像
     * @param file
     * @return
     */
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String updateImage(MultipartFile file) {
        Result result = new Result(ResultCode.RESPONSE_RECORD_FAIL);

        if (!QynImageUtil.isImage(file)) {
            result.setResultCode(ResultCode.RESPONSE_IMAGE_NOT_IS_IMAGE);
            return result.toJSONOString();
        }

        String imagePath = null;
        String imageName = QynImageUtil.jointImageName(file.getOriginalFilename());
        try {
            imagePath = QynImageUtil.saveImage((FileInputStream) file.getInputStream(), imageName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (imagePath != null) {
            User user = super.getUser();
            userService.updateUserImageById(user.getId(), imagePath);
            getSession().setAttribute("loginUser", userService.findUserById(user.getId()));
            result.setResultCode(ResultCode.RESPONSE_SUCCESS);
        }

        return result.toJSONOString();
    }
}
