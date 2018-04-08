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

    @Column(name = "email", nullable = true, length = 32)
    public String email;
}
