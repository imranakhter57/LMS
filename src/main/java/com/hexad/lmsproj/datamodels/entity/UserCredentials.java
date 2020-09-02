package com.hexad.lmsproj.datamodels.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_user_credentials")
public class UserCredentials implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", insertable = false, nullable = false)
    private Long userId;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String PASSWORD;

    @Column(name = "ROLE_ID", nullable = false)
    private Long roleId;

    @Column(name = "LAST_LOGIN")
    private Timestamp lastLogin;

    @Column(name = "LAST_LOGOUT")
    private Timestamp lastLogout;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Timestamp getLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(Timestamp lastLogout) {
        this.lastLogout = lastLogout;
    }

    public String toString() {
      return "UserCredentials{userId=" + userId + 
        ", PASSWORD=" + PASSWORD + 
        ", roleId=" + roleId + 
        ", lastLogin=" + lastLogin + 
        ", lastLogout=" + lastLogout + 
        "}";
    }
}