package com.oracle.view;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
	private JTree friends;

	public MainFrame() {
		setSize(300, 600);
		setLocation(50, 50);
		setVisible(true);
		setResizable(false);
		setTitle("主界面");
		setIconImage(Toolkit.getDefaultToolkit().createImage("resourses/images/66.jpg"));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("头像");
		lblNewLabel.setBounds(0, 0, 114, 132);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("昵称");
		lblNewLabel_1.setBounds(124, 0, 160, 26);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("个性签名");
		lblNewLabel_2.setBounds(124, 36, 160, 96);
		getContentPane().add(lblNewLabel_2);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 142, 274, 416);
		getContentPane().add(tabbedPane);
	}
	
	public void initComponent() {
	//	String[] f=new String[] {"xiao","ming"};
		friends =new JTree();
		friends.setLocation(10,10);
		friends.setRootVisible(true);
		getContentPane().add(friends);
		
	}
}
