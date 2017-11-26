package com.oracle.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.oracle.model.User;

public class DBOperator {
	/**
	 * 这是注册方法，注册成功返回true,失败返回false
	 * @return
	 */
	public boolean register(User user){
		File data=new File("databases"+user.getUsername()+".qq");
		if(data.exists()){
			return false;
		}
		return updateprofile(user);
	}
	/**
	 * 这是修改信息的方法，修改成功返回true，失败返回false
	 * @param args
	 */
	public boolean updateprofile(User user){
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("databases"+user.getUsername()+".qq"));
			out.writeObject(user);
			out.flush();
			out.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 这是登录方法,登录成功返回User，以便登录成功后可直接加载出用户信息，登录失败返回null
	 * @param args
	 */
	public static User login(String username,String password){
		File data=new File("databases"+username+".qq");
		if(data.exists()){
			try {
				ObjectInputStream in=new ObjectInputStream(new FileInputStream("databases"+username+".qq"));
				try {
					User dbuser=(User)in.readObject();
					if(password.equals(dbuser.getPassword())){
						return dbuser;
					}else{
						return null;
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return null;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}else{
			return null;
		}
		
	}
	/**
	 * 这是通过条件查找朋友的方法，查找成功返回朋友信息，失败返回null(找不到文件)
	 * @param args
	 */
	public User searchFriendsByCondition(String username){
		File data=new File("databases"+username+".qq");
		if(!data.exists()){
			try {
				//查找外部数据库
				ObjectInputStream in=new ObjectInputStream(new FileInputStream("databases"+username+".qq"));
				try {
					User dbdata=(User)in.readObject();
					return dbdata;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return null;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}else{
			try {
				//查找内部数据库
				ObjectInputStream in=new ObjectInputStream(new FileInputStream("databases"+username+".qq"));
				try {
					User dbdata=(User)in.readObject();
					return dbdata;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return null;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	/**
	 * 这是添加朋友方法，添加成功返回true，失败返回false（说明该用户已经存在）
	 * @param args
	 */
	public boolean addFriend(User user){
		File data=new File("databases"+user.getUsername()+".qq");
		if(!data.exists()){
			return updateprofile(user);
		}else{
			return false;
		}
	}

	public static void main(String[] args) {
		User user1=new User("111", "111", "男", 10, "小白", "好饿", "resourses/images/头像1.png");
		User user2=new User("222", "222", "女", 20, "小红", "好饿哦", "resourses/images/头像2.png");
		User user3=new User("333", "333", "男", 30, "小黄", "好饱", "resourses/images/头像3.png");
		User user4=new User("444", "444", "女", 40, "小绿", "好饿啊", "resourses/images/头像4.png");
		User user5=new User("555", "555", "男", 50, "小蓝", "想吃饭", "resourses/images/头像5.png");
		User user6=new User("666", "666", "女", 60, "小紫", "不想吃饭", "resourses/images/头像6.png");
		User user7=new User("777", "666", "女", 60, "小紫", "不想吃饭", "resourses/images/头像6.png");
		
		Map<String,HashSet<User>> friends=new HashMap<>();
		HashSet<User> f1s=new HashSet<>();
		       f1s.add(user2);
		       f1s.add(user3);
		       f1s.add(user4);
		       f1s.add(user5);
		       f1s.add(user6);
		       f1s.add(user7);
		       
		       friends.put("大学同学", f1s);
		       user1.setFriends(friends);
		
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("databases/"+user1.getUsername()+".qq"));
			out.writeObject(user1);
			out.flush();
			out.close();
			System.out.println("成功");
			
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user2.getUsername()+".qq"));
			out.writeObject(user2);
			out.flush();
			out.close();
			System.out.println("成功2");
			
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user3.getUsername()+".qq"));
			out.writeObject(user3);
			out.flush();
			out.close();
			System.out.println("成功3");
			
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user4.getUsername()+".qq"));
			out.writeObject(user4);
			out.flush();
			out.close();
			
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user5.getUsername()+".qq"));
			out.writeObject(user5);
			out.flush();
			out.close();
			
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user6.getUsername()+".qq"));
			out.writeObject(user6);
			out.flush();
			out.close();
			
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user7.getUsername()+".qq"));
			out.writeObject(user7);
			out.flush();
			out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
