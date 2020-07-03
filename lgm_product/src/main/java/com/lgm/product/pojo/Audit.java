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
@Table(name = "tb_audit")
@Data
@Setter
@Getter
public class Audit implements Serializable {
    @Id
    private String id;

    private String creator;
    private String auther;
    private String status;
    private String projectname;
    private String rejectreason;
    private String buyreason;
    private Date updatetime;
}
