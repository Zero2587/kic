package top.weishilei.bean;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author weishilei
 * 记录实体类
 */
public class Record implements Serializable {
    private static final long serialVersionUID = 2809891687181068253L;
    /**
     * 唯一标示
     */
    private long id;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否删除
     * true:删除
     * false:未删除
     */
    private boolean delete;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 创建者名字
     */
    private String createName;
    /**
     * 是否是评论
     * 1：是
     * 0：不是
     */
    private boolean comment;
    /**
     * 评论的父id
     */
    private long pid;
    /**
     * 创建者的头像
     */
    private String createImage;

    public Record() {}

    public Record(int userId, String content, String name) {
        this.userId = userId;
        this.content = content;
        this.createName = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getCreateImage() {
        return createImage;
    }

    public void setCreateImage(String createImage) {
        this.createImage = createImage;
    }

    public String formatDate() {
        return DateFormatUtils.format(this.createDate, "yyyy-MM-dd HH:mm:ss");
    }

}