package top.weishilei.result;


import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author weishilei
 * 返回值封装
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 1881316111534727334L;

    /**
     * 返回码状态
     */
    private int code;
    /**
     * 返回值消息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;

    public Result() {}

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public String toJSONOString() {
        return JSONObject.toJSONString(this);
    }

}
