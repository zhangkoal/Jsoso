package cn.constant;

/**
 * @Author: sfpy
 * @Date: 5/22/2019 4:03 PM
 * @Descirption
 * @Version 1.0
 */
public enum  ResultStatus {

    SUCCESS(200, "成功"),
    FAIL(-1, "失败");

    private int id;
    private String value;

    ResultStatus(int id, String value){
        this.id = id;
        this.value = value;

    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
