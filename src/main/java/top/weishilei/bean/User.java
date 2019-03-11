package top.weishilei.bean;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author weishilei
 * 用户实体类
 */
public class User implements Serializable {
    private static final long serialVersionUID = -3995710591878877093L;
    /**
     * 用户唯一id
     */
    private int id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户名字
     */
    private String name;
    /**
     * 问题id
     */
    private int questionId;
    /**
     * 问题答案
     */
    private String answer;
    /**
     * 个性签名
     */
    private String signature = "这家伙很懒什么也没有写～";
    /**
     * 头像
     */
    private String photo = "/image/default.png";
    /**
     * 创建时间
     */
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String formatDate() {
        return DateFormatUtils.format(this.createDate, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", questionId=" + questionId +
                ", answer='" + answer + '\'' +
                ", signature='" + signature + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
