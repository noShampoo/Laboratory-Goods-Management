package com.lgm.product.dao;

import com.lgm.product.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductDao extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

    @Modifying
    @Query(value = "update tb_product set status = 2 where id = ?", nativeQuery = true)
    public void updateProductStatus(String id);

    @Modifying
    @Query(value = "update tb_product set status = 0, isrepair = 1 where id = ?", nativeQuery = true)
    public void updateProductStatusAndIsRapir(String id);
}
