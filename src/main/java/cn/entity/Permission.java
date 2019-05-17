package cn.entity;

/**
 * @Author: sfpy
 * @Date: 5/14/2019 11:33 PM
 * @Descirption
 * @Version 1.0
 */
public class Permission {
    private Long id;
    private String permission;
    private TbRole tbRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public TbRole getRole() {
        return tbRole;
    }

    public void setRole(TbRole tbRole) {
        this.tbRole = tbRole;
    }
}
