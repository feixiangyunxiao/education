package com.bootdo.teacher.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-07-23 17:00:11
 */
public class TeacherDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String name;
	//0代表女 1代表男
	private Integer sex;
	//
	private Integer age;
	//
	private String photo;
	//
	private String mobile;
	//
	private Long deptId;
	//
	private String teaOpenId;
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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：0代表女 1代表男
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：0代表女 1代表男
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * 获取：
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：
	 */
	public Long getDeptId() {
		return deptId;
	}
	/**
	 * 设置：
	 */
	public void setTeaOpenId(String teaOpenId) {
		this.teaOpenId = teaOpenId;
	}
	/**
	 * 获取：
	 */
	public String getTeaOpenId() {
		return teaOpenId;
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
