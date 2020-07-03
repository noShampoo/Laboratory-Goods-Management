package com.lgm.account.controller;


import com.lgm.account.pojo.Account;
import com.lgm.account.pojo.ChangePassAccountPojo;
import com.lgm.account.service.AccountService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 登录
     * @param loginRequestBody
     * @param httpServletResponse
     * @return
     */
    @PostMapping("/login")
    public Result accountLogin(@RequestBody Account loginRequestBody,
                               HttpServletResponse httpServletResponse) {
        System.out.println(loginRequestBody.getAccount());
        System.out.println(loginRequestBody.getPassword());
        Account accountObj = accountService.accountLogin(loginRequestBody.getAccount(),
                loginRequestBody.getPassword(),
                httpServletResponse);
        if (accountObj == null) {
            return new Result(false, StatusCode.ERROR, "登录出错，密码与账号无法匹配");
        }
        Map<String, String> map = new HashMap<>();
        map.put("userName", accountObj.getUsername());
        map.put("account", accountObj.getAccount());
        map.put("id", accountObj.getId());
        return new Result(true, StatusCode.OK, "登录成功", map);
    }

    /**
     * 鉴权
     * @return
     */
    @GetMapping("/checkLogin")
    public Result checkLogin() {
        Account accountObj = accountService.checkLogin();
        if (accountObj == null) {
            return new Result(false, StatusCode.PERMISSION, "没有权限");
        }
        Map<String, String> map = new HashMap<>();
        map.put("userName", accountObj.getUsername());
        map.put("com.lgm.account", accountObj.getAccount());
        map.put("id", accountObj.getId());
        return new Result(true, StatusCode.OK, "您已登录", map);

//        Account accountObj = new Account("1", "bhj", "bhj", "1234");
//        Map<String, String> map = new HashMap<>();
//        map.put("userName", accountObj.getUsername());
//        map.put("account", accountObj.getAccount());
//        map.put("id", accountObj.getId());
//        return new Result(true, StatusCode.OK, "您已登录", map);
    }


    /**
     * 登出
     * @return
     */
    @GetMapping("/logout")
    public Result logout() {
        boolean flag = accountService.logout();
        return flag ?
                new Result(flag, StatusCode.OK, "登出成功") :
                new Result(flag, StatusCode.ERROR, "登出出错");
    }

    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody ChangePassAccountPojo changePassAccountPojo) {
        System.out.println(changePassAccountPojo.getId());
        System.out.println(changePassAccountPojo.getAccount());
        System.out.println(changePassAccountPojo.getOldPassword());
        System.out.println(changePassAccountPojo.getNewPassword());
        boolean flag = accountService.changePassword(changePassAccountPojo.getId(),
                changePassAccountPojo.getOldPassword(),
                changePassAccountPojo.getNewPassword());
        return flag ?
                new Result(flag, StatusCode.OK, "修改成功") :
                new Result(flag, StatusCode.ERROR, "修改失败");
    }

}
