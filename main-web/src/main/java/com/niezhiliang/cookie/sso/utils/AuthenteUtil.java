package com.niezhiliang.cookie.sso.utils;

import com.niezhiliang.cookie.sso.entity.CookieEntity;
import com.niezhiliang.cookie.sso.entity.User;

import javax.servlet.http.HttpServletRequest;

public class AuthenteUtil {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final Long USERID = 1l;

    /**
     * 判断是否登录
     * @param username
     * @param password
     * @return
     */
    public static boolean checkLogin(String username, String password) {
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            return true;
        }
        return false;
    }

    public static boolean checkCookie( String cookieValue) {

        return false;
    }

    /**
     * 拿到cookie 解析出userid一些其他参数
     * @param token
     * @return
     */
    public static User splitCookie(String token) {
        User user = new User();
        String ip = new JwtUtil().getIp(token);
        Long userid = Long.parseLong(new JwtUtil().getUserIdByToken(token));
        user.setIp(ip);
        user.setUserid(userid);
        return user ;
    }

    /**
     * 生成cookie
     * @param ip
     * @return
     */
    public static String generalCookie(String ip) {
        User user = new User(USERID,USERNAME,PASSWORD,ip);
        new JwtUtil().generateToken(user);
        return new JwtUtil().generateToken(user);
    }

    /**
     * 获取Ip地址
     * @param request
     * @return
     */
    public static String getIpAdrress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
