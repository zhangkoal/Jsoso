package cn.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author: sfpy
 * @Date: 5/22/2019 12:21 PM
 * @Descirption 订单工具类
 * @Version 1.0
 */
public class OrderUtil {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            System.out.println(OrderUtil.getOrderNo());
        }
    }
    /**
     * 生成订单编号
     *
     * @return
     */
    public static synchronized String getOrderNo() {
        String str = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            str += random.nextInt(10);
        }

        return str;
    }


}
