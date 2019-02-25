package cn.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ClassName
 * @Description 用户信息
 * @Author zhangk
 * @Date 2019/2/22 12:13
 * Version 1.0
 **/
@Entity
public class LoginUserInfo {

    @Id
    @GeneratedValue
    public int id;

    @Column(name = "qqUserId", nullable = true, length = 100)
    public String qqUserId;

    @Column(name = "acceeToken", nullable = true, length = 100)
    private String acceeToken;

    @Column(name = "userName", nullable = true, length = 100)
    private String userName;

    @Column(name = "imgUrl", nullable = true, length = 100)
    private String imgUrl;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQqUserId() {
        return qqUserId;
    }

    public void setQqUserId(String qqUserId) {
        this.qqUserId = qqUserId;
    }

    public String getAcceeToken() {
        return acceeToken;
    }

    public void setAcceeToken(String acceeToken) {
        this.acceeToken = acceeToken;
    }
}
