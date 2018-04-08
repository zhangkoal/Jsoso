package cn.Util;

public class ResultMsg {

    public static Msg success(Object object) {
        Msg msg = new Msg();
        msg.setCode(100);
        msg.setMsg("请求成功");
        msg.setData(object);
        return msg;
    }

    public static Msg fail(Object object) {
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg("请求失败");
        msg.setData(object);
        return msg;
    }

}
