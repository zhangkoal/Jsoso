package cn.exception;

/**
 * @Author: sfpy
 * @Date: 5/9/2019 12:48 PM
 * @Descirption
 * @Version 1.0
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String msg) {
        super(msg);
    }
}
