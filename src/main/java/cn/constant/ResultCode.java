package cn.constant;

/**
 * @ClassName ResultCode
 * @Description 状态编码
 * @Author zhangk
 * @Date 2019/2/22 11:07
 * Version 1.0
 **/
public enum  ResultCode {
    OK(200, "返回成功"),
    FAIL(-1, "返回失败");



    private ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }}
