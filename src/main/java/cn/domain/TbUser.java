package cn.domain;

import javax.persistence.*;

@Entity
public class TbUser {

    @Id
    @GeneratedValue
    public int userId;

    @Column(name = "uesrName", nullable = true, length = 32)
    public String uesrName;

    @Column(name = "password", nullable = true, length = 32)
    public String password;

    @Column(name = "userType", nullable = true)
    public int userType;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUesrName() {
        return uesrName;
    }

    public void setUesrName(String uesrName) {
        this.uesrName = uesrName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
