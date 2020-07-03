package com.lgm.product.service;

import com.lgm.product.dao.ProductDao;
import com.lgm.product.pojo.Audit;
import com.lgm.product.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Page<Product> getProjectList(int limit, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return productDao.findAll(pageRequest);
    }

    public Page<Product> getDestoryList(int limit, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return productDao.findAll(new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                Predicate predicate = criteriaBuilder.equal(root.get("status").as(String.class), "2");
                predicateList.add(predicate);
                Predicate[] predicates = new Predicate[predicateList.size()];
                predicates = predicateList.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        }, pageRequest);
    }

    public Page<Product> getRepairList(int limit, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return productDao.findAll(new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                Predicate predicate = criteriaBuilder.equal(root.get("isrepair").as(String.class), "1");
                predicateList.add(predicate);
                Predicate[] predicates = new Predicate[predicateList.size()];
                predicates = predicateList.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        }, pageRequest);
    }

    public void destoryProjectById(String id) {
        productDao.updateProductStatus(id);
    }

    public void repairProjectById(String id) {
        productDao.updateProductStatusAndIsRapir(id);
    }

    public void addProductList(Audit audit) {
        Product product = new Product();
        product.setId(audit.getId());
        product.setProname(audit.getProjectname());
        product.setStatus("1");
        product.setIsrepair("0");
        productDao.save(product);
    }
}
