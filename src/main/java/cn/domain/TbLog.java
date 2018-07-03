package cn.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TbLog {

    @Id
    @GeneratedValue
    public int logId;

    @Column(name = "country", nullable = true, length = 100)
    public String country;

    @Column(name = "area", nullable = true, length = 100)
    public String area;

    @Column(name = "region", nullable = true, length = 100)
    public String region;

    @Column(name = "city", nullable = true, length = 100)
    public String city;

    @Column(name = "county", nullable = true, length = 32)
    public String county;

    @Column(name = "isp", nullable = true, length = 32)
    public String isp;

    @Column(name = "ip", nullable = true, length = 32)
    public String ip;

    @Column(name = "loginTime", nullable=true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
