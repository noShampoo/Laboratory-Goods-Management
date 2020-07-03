package com.lgm.account.dao;


import com.lgm.account.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AccountDao {

    @Select("select * from tb_account where com.lgm.account = #{com.lgm.account}")
    public Account findAccountObjByAccount(String account);

    @Select("select * from tb_account where id = #{id}")
    public Account findAccountObjById(String id);

    @Update("update tb_account set password = #{newPassword} where id = #{id}")
    public void updatePassword(String id, String newPassword);

}
