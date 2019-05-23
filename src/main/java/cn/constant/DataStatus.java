package cn.constant;

/**
 * @Author: sfpy
 * @Date: 5/22/2019 4:03 PM
 * @Descirption
 * @Version 1.0
 */
public enum DataStatus {

    NORMAL(10, "正常"),
    DELETE(-1, "已删除");

    private Integer id;
    private String value;

    DataStatus(Integer id, String value){
        this.id = id;
        this.value = value;

    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
