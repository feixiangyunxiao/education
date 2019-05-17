package com.bootdo.welcome.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-15 17:11:06
 */
public class SchoolNewsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String title;
	//
	private String content;
	//
	private Date createtime;
	//浏览次数
	private Integer browseNumber;

	private Integer status;

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
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：浏览次数
	 */
	public void setBrowseNumber(Integer browseNumber) {
		this.browseNumber = browseNumber;
	}
	/**
	 * 获取：浏览次数
	 */
	public Integer getBrowseNumber() {
		return browseNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SchoolNewsDO{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", createtime=" + createtime +
				", browseNumber=" + browseNumber +
				", status=" + status +
				'}';
	}
}
