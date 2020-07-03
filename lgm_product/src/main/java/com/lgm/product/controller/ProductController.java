package com.lgm.product.controller;

import com.lgm.product.pojo.Audit;
import com.lgm.product.pojo.Product;
import com.lgm.product.service.AuditService;
import com.lgm.product.service.ProductService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AuditService auditService;

    @GetMapping("/getProjectList")
    public Result getProjectList(@RequestParam("limit") int limit,
                                 @RequestParam("offset") int offset,
                                 @RequestParam(value = "searchName", required = false) String searchName) {
        Page<Product> projectList = productService.getProjectList(limit, offset);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Product>(projectList.getTotalElements(), projectList.getContent()));
    }

    @GetMapping("/getDestoryList")
    public Result getDestoryList(@RequestParam("limit") int limit,
                                 @RequestParam("offset") int offset) {
        Page<Product> projectList = productService.getDestoryList(limit, offset);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Product>(projectList.getTotalElements(), projectList.getContent()));
    }

    @GetMapping("/getRepairList")
    public Result getRepairList(@RequestParam("limit") int limit,
                                @RequestParam("offset") int offset,
                                @RequestParam(value = "searchName", required = false) String searchName) {
        Page<Product> projectList = productService.getRepairList(limit, offset);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Product>(projectList.getTotalElements(), projectList.getContent()));
    }

    @GetMapping("/destoryProjectById")
    public Result destoryProjectById(@RequestParam("id") String id) {
        productService.destoryProjectById(id);
        return new Result(true, StatusCode.OK, "报废成功");
    }

    @GetMapping("/repairProjectById")
    public Result repairProjectById(@RequestParam("id") String id) {
        productService.repairProjectById(id);
        return new Result(true, StatusCode.OK, "修复成功");
    }

    @GetMapping("/addProject")
    public Result addProject(@RequestParam("projectName") String projectName) {
        auditService.addProject(projectName);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @GetMapping("/getAuthList")
    public Result getAuthList(@RequestParam("limit") int limit,
                              @RequestParam("offset") int offset) {
        Page<Audit> pageList = auditService.findAll(offset, limit);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Audit>(pageList.getTotalElements(), pageList.getContent()));
    }

    @GetMapping("/getBuyList")
    public Result getBuyList(@RequestParam("limit") int limit,
                             @RequestParam("offset") int offset) {
        Page<Audit> pageList = auditService.findAll(offset, limit);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Audit>(pageList.getTotalElements(), pageList.getContent()));
    }

    @GetMapping("/promiseSomeAuthRequest")
    public Result promiseSomeAuthRequest(@RequestParam("id") String id,
                                         @RequestParam("reason") String reason) {
        auditService.promiseSomeAuthRequest(id, reason);
        return new Result(true, StatusCode.OK, "审核成功");
    }
}
