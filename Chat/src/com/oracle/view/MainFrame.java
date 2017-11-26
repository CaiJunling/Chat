package com.oracle.view;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTree;

public class MainFrame extends JFrame {
	private JTree friends;

	public MainFrame() {
		setSize(300, 600);
		setLocation(50, 50);
		setVisible(true);
		setResizable(false);
		setTitle("主界面");
		setIconImage(Toolkit.getDefaultToolkit().createImage("resourses/images/66.jpg"));
	}
	
	public void initComponent() {
	//	String[] f=new String[] {"xiao","ming"};
		friends =new JTree();
		friends.setLocation(10,10);
		friends.setRootVisible(true);
		this.add(friends);
		
	}
	

}
