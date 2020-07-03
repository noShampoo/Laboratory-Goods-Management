package com.lgm.account.service;

import com.lgm.account.dao.AccountDao;
import com.lgm.account.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 登录
     * @param account
     * @param password
     * @param httpServletResponse
     * @return
     */
    public Account accountLogin(String account, String password, HttpServletResponse httpServletResponse) {
        Account accountObj = accountDao.findAccountObjByAccount(account);

        if (accountObj == null) {
            return null;
        }
        //实现单点登录
        boolean flag = accountObj.getPassword().equals(password);
        if (flag) {
            Cookie[] cookies = httpServletRequest.getCookies();
            String sessionId = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        sessionId = cookie.getValue();
                        System.out.println("sessionId:" + sessionId);
                        break;
                    }
                }
            } else {
                HttpSession session = httpServletRequest.getSession();
                sessionId = session.getId();
            }
            System.out.println("sessionId======>" + sessionId);
            String s = redisTemplate.opsForValue().get(sessionId + "_account");
            System.out.println(s);
            redisTemplate.opsForValue().set(sessionId + "_account", account, 1, TimeUnit.DAYS);
            Cookie cookie = new Cookie("JSESSIONID", sessionId);
            cookie.setMaxAge(24 * 60 * 60 * 7);
            httpServletResponse.addCookie(cookie);
        }
        return flag ? accountObj : null;
    }

    /**
     * 鉴权
     * @return
     */
    public Account checkLogin() {
        Cookie[] cookies = httpServletRequest.getCookies();
        System.out.println("cookie length::::::::::::::::" + cookies.length);
        String sessionId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    sessionId = cookie.getValue();
                    System.out.println("sessionId:" + sessionId);
                    break;
                }
            }
        } else {
            System.out.println("cookies is null");
            return null;
        }
        String account = redisTemplate.opsForValue().get(sessionId + "_account");
        return accountDao.findAccountObjByAccount(account);
    }

    public boolean logout() {
        Cookie[] cookies = httpServletRequest.getCookies();
        String sessionId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
        return redisTemplate.delete(sessionId + "_account");
    }

    public boolean changePassword(String id, String oldPassword, String newPassword) {
        Account accountObj = accountDao.findAccountObjById(id);
        if (accountObj == null) return false;
        if (oldPassword.equals(accountObj.getPassword())) {
            try {
                accountDao.updatePassword(id, newPassword);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }
}
