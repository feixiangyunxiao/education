package com.bootdo.welcome.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-14 15:29:13
 */
public class MycarouselDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String title;
	//
	private String picname;
	//
	private String href;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setPicname(String picname) {
		this.picname = picname;
	}
	/**
	 * 获取：
	 */
	public String getPicname() {
		return picname;
	}
	/**
	 * 设置：
	 */
	public void setHref(String href) {
		this.href = href;
	}
	/**
	 * 获取：
	 */
	public String getHref() {
		return href;
	}
}
