package cn.constant;

public enum  CommonStatus {

    NORMAL(10, "正常"),
    ERROR(-1, "错误");

    public int id;
    public String desc;
    CommonStatus(int id, String desc){
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
