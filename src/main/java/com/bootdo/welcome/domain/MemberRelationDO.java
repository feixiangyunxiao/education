package com.bootdo.welcome.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-05-17 11:12:06
 */
public class MemberRelationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long parentId;
	//
	private Long studentId;
	//
	private String spare;
	//
	private Date createTime;

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
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：
	 */
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	/**
	 * 获取：
	 */
	public Long getStudentId() {
		return studentId;
	}
	/**
	 * 设置：
	 */
	public void setSpare(String spare) {
		this.spare = spare;
	}
	/**
	 * 获取：
	 */
	public String getSpare() {
		return spare;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
