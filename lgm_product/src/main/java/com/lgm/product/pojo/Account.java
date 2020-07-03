package com.lgm.product.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户实体类
 * @author 包海金
 */
@Data
@Getter
@Setter
public class Account implements Serializable {
    private String id;
    private String account;
    private String username;
    private String password;

    public Account(String id, String account, String username, String password) {
        this.id = id;
        this.account = account;
        this.username = username;
        this.password = password;
    }
}
