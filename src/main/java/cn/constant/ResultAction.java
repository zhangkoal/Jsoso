package cn.constant;

/**
 * @ClassName ResultAction
 * @Description api接口返回通用类
 * @Author zhangk
 * @Date 2019/1/10 16:16
 * Version 1.0
 **/
public class ResultAction {
    private String msg;
    private Object data;
    private int code;
    private Integer nextPage;
    public ResultAction(){

    }
    public ResultAction(int code, String info) {
        this.code = code;
        this.msg = info;
    }

    public ResultAction(String info, Object data, int code) {
        this.msg = info;
        this.data = data;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
}
