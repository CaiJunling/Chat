package com.oracle.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

public class User implements Serializable{
	private String username;
	private String password;	
	private String sex;
	private int age;
	private String nickname;
	private String signature;
	private String imagePath;
	private Map<String,HashSet<User>> friends;
	
	public User() {
		super();	
	}
	
	
	public Map<String, HashSet<User>> getFriends() {
		return friends;
	}


	public void setFriends(Map<String, HashSet<User>> friends) {
		this.friends = friends;
	}

	

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public User(String username, String password, String sex, int age, String nickname, String signature,
			String imagePath) {
		super();
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.nickname = nickname;
		this.signature = signature;
		this.imagePath = imagePath;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", sex=" + sex + ", age=" + age + ", nickname="
				+ nickname + ", signature=" + signature + ", imagePath=" + imagePath + "]";
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
	

}
