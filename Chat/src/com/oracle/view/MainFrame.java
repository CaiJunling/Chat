package com.oracle.view;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
	private JTree friends;
	private JLabel lblNewLabel,lblNewLabel_1,lblNewLabel_2;
	private JTabbedPane tabbedPane;
	private JTree tree;

	public MainFrame() {
		setSize(300, 600);
		setLocation(50, 50);
		setVisible(true);
		setResizable(false);
		setTitle("主界面");
		setIconImage(Toolkit.getDefaultToolkit().createImage("resourses/images/头像1.jpg"));
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("头像");
		lblNewLabel.setBounds(0, 0, 114, 132);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("昵称");
		lblNewLabel_1.setBounds(124, 0, 160, 26);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("个性签名");
		lblNewLabel_2.setBounds(124, 36, 160, 96);
		getContentPane().add(lblNewLabel_2);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(10, 142, 274, 416);
		getContentPane().add(tabbedPane);
		
		tree = new JTree();
		tabbedPane.addTab("联系人", null, tree, null);
		
	}
	
	public void initComponent() {
	//	String[] f=new String[] {"xiao","ming"};
		friends =new JTree();
		friends.setLocation(10,10);
		friends.setRootVisible(true);
		getContentPane().add(friends);
		
	}
	public static void main(String[] args) {
		MainFrame m=new MainFrame();
		m.setVisible(true);
	}
}
