package com.oracle.model;

import java.io.Serializable;

/**
 * 这个类是封装消息对象的类型，里面应该定义所有消息应该共有的属性
 * @author ASUS
 *
 */

public class MessageBox implements Serializable {
	private User from;
	private User to;
	private String content;
	private String type;
	private String time;
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public MessageBox(User from, User to, String content, String type, String time) {
		super();
		this.from = from;
		this.to = to;
		this.content = content;
		this.type = type;
		this.time = time;
	}
	public MessageBox() {
		super();
	}
	@Override
	public String toString() {
		return "MessageBox [from=" + from + ", to=" + to + ", content=" + content + ", type=" + type + ", time=" + time
				+ "]";
	}
	
	
	
	

}
