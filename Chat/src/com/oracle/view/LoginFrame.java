package com.oracle.view;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javafx.scene.text.Font;

public class LoginFrame extends JFrame {
	private JLabel headImage,loginname,registername;
	private JButton login, register;
	private JComboBox username;
	private JPasswordField passwordInput;


	public LoginFrame() {
		setSize(500, 400);
		setLocationRelativeTo(null);// 设置相对位置
		setVisible(true);// 窗口显示
		setResizable(false);// 窗口不可改大小
		setTitle("芒果聊天");
		setLayout(null);// 不采用任何默认布局
		setDefaultCloseOperation(EXIT_ON_CLOSE);// 关掉xx的时候程序也关掉了
		setIconImage(Toolkit.getDefaultToolkit().createImage("resourses/images/66.jpg"));
		initComponent();
		paintComponents(getGraphics());// 避免组件不是一次性出来
		paintAll(getGraphics());// 避免组件不是一次性出来

	}

	public void initComponent() {// 初始化窗口组件的方法
		loginname=new JLabel("账号:");
		loginname.setSize(50, 30);
		loginname.setLocation(80, 150);
		this.add(loginname);
		
		loginname=new JLabel("密码：");
		loginname.setSize(50, 30);
		loginname.setLocation(80, 200);
		this.add(loginname);
		
		username = new JComboBox(new Object[]{"111","222","333"});
		username.setSize(230, 30);
		username.setLocation(130, 150);
		this.add(username);

		passwordInput = new JPasswordField("111");
		passwordInput.setSize(230, 30);
		passwordInput.setLocation(130, 200);
		this.add(passwordInput);

		login = new JButton("登录");
		// login.setBorder(BorderFactory.createLineBorder(Color.red));
	//	login.addMouseListener(new MyMouseListener(this));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1.先做表单验证
				//2.建立和服务器的连接（socket连接）
				//3.在socket的基础上获取输入输出流，然后用输出流将消息发送给服务器，让服务器校验我们的账号和密码是否成功	
			}
		});
		login.setSize(110, 40);
		login.setLocation(130, 260);
		this.add(login);

		register = new JButton("注册");
		register.setSize(110, 40);
		register.setLocation(250, 260);
		this.add(register);
		
		headImage = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resourses/images/3.jpg")));
		// headImage.setBackground(Color.blue);//设置边框颜色
		// headImage.setBorder(BorderFactory.createLineBorder(Color.red));//组件边框颜色
		headImage.setSize(500, 400);
		headImage.setLocation(0, 0);
		this.add(headImage);
	}
	
	

	public JButton getLogin() {
		return login;
	}

	public void setLogin(JButton login) {
		this.login = login;
	}

	public static void main(String[] args) {
		LoginFrame i = new LoginFrame();
	}
	
	

}
