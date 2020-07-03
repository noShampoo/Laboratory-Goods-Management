package com.lgm.product.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_product")
@Data
@Getter
@Setter
public class Product implements Serializable {

    @Id
    private String id;

    private String proname;
    private String status;
    private String isrepair;
    private Date createtime;
    private Date updatetime;
    private String reason;
}
