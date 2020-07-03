package com.lgm.product.dao;

import com.lgm.product.pojo.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AuditDao extends JpaRepository<Audit, String>, JpaSpecificationExecutor<Audit> {

    @Modifying
    @Query(value = "update tb_audit set status = ? where id = ?", nativeQuery = true)
    public void updateAuditStatusById(String status, String id);
}
