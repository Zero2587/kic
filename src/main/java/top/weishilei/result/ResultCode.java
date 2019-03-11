package top.weishilei.result;


/**
 * @author weishilei
 * 统一返回状态码
 */
public enum ResultCode {

    RESPONSE_SUCCESS(0, "成功"),
    RESPONSE_FAIL(10000, "未知错误！"),
    /**
     * 用户相关
     */
    RESPONSE_LOGIN_FAIL(20001, "登录失败！用户名或密码错误！"),
    RESPONSE_REGISTER_USER_EXISTS(20002, "注册失败！用户名已存在！"),
    RESPONSE_RESET_FAIL(20003, "修改失败！信息填写有误！"),
    RESPONSE_RESET_PARMTER_IS_EMPTY(20004, "修改失败！信息不能为空！"),
    RESPONSE_RESET_NOT_EXISTS(20005, "修改失败！该用户未注册！"),
    RESPONSE_REGISTER_PARMTER_IS_EMPTY(20006, "注册失败！请完善注册信息！"),
    /**
     * 记录相关
     */
    RESPONSE_RECORD_NOT_LOGIN(30001, "发布失败！请先登录！"),
    RESPONSE_RECORD_FAIL(30002, "发布失败！"),
    RESPONSE_RECORD_CONTENT_IS_EMPTY(30003, "发布失败！内容不能空！"),
    /**
     * 图片上传相关
     */
    RESPONSE_IMAGE_NOT_IS_IMAGE(40001, "请上传正确的图片格式(jpg, jpeg, png, gif)！");

    private int code;
    private String msg;
    public final static String RESPONSE_FAIL_NOT_LOGIN = "{\"code\": 20002,\"msg\": \"请先登录\"}";

    private ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{" +
                "code:" + code +
                ", msg:'" + msg + "}";
    }
}
