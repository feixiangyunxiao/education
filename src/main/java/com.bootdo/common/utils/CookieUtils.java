package com.bootdo.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * cookieUtils
 * 
 * @author haojiangbo
 *
 */
public class CookieUtils {
	
	/**
	 * set a cookie
	 * @param name
	 * @param value
	 * @param response
	 */
	public static void setCookie(String name, String value, HttpServletResponse response, int times){
		Cookie cookie = new Cookie(name,null);
		cookie.setPath("/");
		cookie.setMaxAge(times);
		try {		
			cookie.setValue(URLEncoder.encode(value,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.addCookie(cookie);
	}
	/**
	 * get a cookie
	 * @param name
	 * @param request
	 * @param response
	 */
	public static String getCookie(String name, HttpServletRequest request){
		Cookie[] cookies =  request.getCookies();
		if(null != cookies && cookies.length > 0){
			for(Cookie c : cookies){
				if(c.getName().equals(name)){
					try {
						return URLDecoder.decode(c.getValue(),"utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
}
