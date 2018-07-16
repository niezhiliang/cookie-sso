package com.niezhiliang.cookie.sso.controller;

import com.alibaba.fastjson.JSON;
import com.niezhiliang.cookie.sso.entity.CookieEntity;
import com.niezhiliang.cookie.sso.entity.ResponseEntity;
import com.niezhiliang.cookie.sso.entity.User;
import com.niezhiliang.cookie.sso.exception.CookieErrorException;
import com.niezhiliang.cookie.sso.utils.AuthenteUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @Value("${cookie.name}")
    private String cookieName;
    @Value("${web.father}")
    private String fatherurl;
    @RequestMapping(value = "login")
    public String login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
        boolean flag =
            AuthenteUtil.checkLogin(user.getUsername(),user.getPassword());
        String ip = AuthenteUtil.getIpAdrress(request);
        ResponseEntity responseEntity =
                            new ResponseEntity();
        if (flag) {
            Cookie cookie = new Cookie(cookieName, AuthenteUtil.generalCookie(ip));
            cookie.setMaxAge(30* 60);// 设置为30min
            //cookie.setDomain(".test.com");
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            System.out.println("已添加===============");
            response.addCookie(cookie);
            responseEntity.setCode(200);
        } else {
            throw new CookieErrorException("账号或密码输入错误");
        }

        return JSON.toJSONString(responseEntity);
    }

    @RequestMapping(value = "check")
    public String checklogin(HttpServletRequest request) {
        Cookie [] cookies = request.getCookies();
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode(50);
        if (cookies == null) {
            return null;
        }
        CookieEntity cookieEntity = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {

                cookieEntity = AuthenteUtil.splitCookie(cookie.getValue());
                responseEntity.setCode(20);
                responseEntity.setData(cookieEntity);
            }
        }
        return JSON.toJSONString(responseEntity);
    }
}
