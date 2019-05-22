package cn.constant.order;

/**
 * @Author: sfpy
 * @Date: 5/22/2019 2:58 PM
 * @Descirption
 * @Version 1.0
 */
public enum OrderStatus {
    CONFIRMED(10, "已确认"),
    TO_CONFIRMED(20, "待确认"),
    RECEIVED(30, "已收货"),
    CANCELLED(40, "已取消"),
    COMPLETED(50, "已完成");

    private int id;
    private String value;

    OrderStatus(int id, String value) {
        this.id = id;
        this.value = value;
    }



}
