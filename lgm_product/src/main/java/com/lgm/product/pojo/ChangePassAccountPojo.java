package com.lgm.product.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class ChangePassAccountPojo implements Serializable {
    private String id;
    private String account;
    private String oldPassword;
    private String newPassword;
}
