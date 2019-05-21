package cn.constant;

/**
 * @Author: sfpy
 * @Date: 5/21/2019 3:17 PM
 * @Descirption
 * @Version 1.0
 */
public enum  RedisWorkspace {
    API_SPACE(10, "api-token:");



    private int id;
    private String value;

    RedisWorkspace(int id, String value){
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
