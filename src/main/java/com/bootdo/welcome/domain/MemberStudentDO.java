package com.bootdo.welcome.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-05-17 11:12:02
 */
public class MemberStudentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String headPhoto;
	//
	private String signature;
	//
	private String nickName;
	//
	private Integer stuNumber;
	//
	private String password;
	//备用字段
	private String spare;
	//
	private String stuOpenid;
	//
	private Date createTime;

	private String mobile;

	private String androidClientId;

	private String iosClientId;

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
	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}
	/**
	 * 获取：
	 */
	public String getHeadPhoto() {
		return headPhoto;
	}
	/**
	 * 设置：
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	/**
	 * 获取：
	 */
	public String getSignature() {
		return signature;
	}
	/**
	 * 设置：
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 获取：
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * 设置：
	 */
	public void setStuNumber(Integer stuNumber) {
		this.stuNumber = stuNumber;
	}
	/**
	 * 获取：
	 */
	public Integer getStuNumber() {
		return stuNumber;
	}
	/**
	 * 设置：
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：备用字段
	 */
	public void setSpare(String spare) {
		this.spare = spare;
	}
	/**
	 * 获取：备用字段
	 */
	public String getSpare() {
		return spare;
	}
	/**
	 * 设置：
	 */
	public void setStuOpenid(String stuOpenid) {
		this.stuOpenid = stuOpenid;
	}
	/**
	 * 获取：
	 */
	public String getStuOpenid() {
		return stuOpenid;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAndroidClientId() {
		return androidClientId;
	}

	public void setAndroidClientId(String androidClientId) {
		this.androidClientId = androidClientId;
	}

	public String getIosClientId() {
		return iosClientId;
	}

	public void setIosClientId(String iosClientId) {
		this.iosClientId = iosClientId;
	}

	@Override
	public String toString() {
		return "MemberStudentDO{" +
				"id=" + id +
				", headPhoto='" + headPhoto + '\'' +
				", signature='" + signature + '\'' +
				", nickName='" + nickName + '\'' +
				", stuNumber=" + stuNumber +
				", password='" + password + '\'' +
				", spare='" + spare + '\'' +
				", stuOpenid='" + stuOpenid + '\'' +
				", createTime=" + createTime +
				", mobile='" + mobile + '\'' +
				'}';
	}
}
