package com.lgm.product.service;

import com.lgm.product.dao.AuditDao;
import com.lgm.product.pojo.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

@Service
@Transactional
public class AuditService {

    @Autowired
    private AuditDao auditDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ProductService productService;

    public void addProject(String projectName) {
        Audit audit = new Audit();
        audit.setId(idWorker.nextId() + "");
        audit.setCreator("bhj");
        audit.setAuther("bhj");
        audit.setStatus("0");
        audit.setProjectname(projectName);
        auditDao.save(audit);
        productService.addProductList(audit);
    }

    public Page<Audit> findAll(int offset, int limit) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return auditDao.findAll(pageRequest);
    }

    public void promiseSomeAuthRequest(String id, String reason) {
        if (reason.equals("true")) {
            auditDao.updateAuditStatusById("1", id);
        } else if (reason.equals("false")) {
            auditDao.updateAuditStatusById("2", id);
        }
    }
}
