package com.oracle.view;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.oracle.control.ServerFrameUIConfig;
import com.oracle.model.MessageBox;
import com.oracle.model.User;

import java.awt.Font;

public class LoginFrame extends JFrame {
	private Socket client;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private JLabel headImage,loginname,registername;
	private JLabel loginname_1;
	private JButton login, register;
	private JComboBox username;
	private JPasswordField passwordInput;


	public LoginFrame() {
		setSize(500, 400);
		setLocationRelativeTo(null);// 设置相对位置
		setVisible(true);// 窗口显示
		setResizable(false);// 窗口不可改大小
		setTitle("熊猫聊天");
		getContentPane().setLayout(null);// 不采用任何默认布局
		setDefaultCloseOperation(EXIT_ON_CLOSE);// 关掉xx的时候程序也关掉了
		setIconImage(Toolkit.getDefaultToolkit().createImage("resourses/images/头像1.jpg"));
		initComponent();
		paintComponents(getGraphics());// 避免组件不是一次性出来
		paintAll(getGraphics());// 避免组件不是一次性出来

	}

	public void initComponent() {// 初始化窗口组件的方法
		loginname=new JLabel("账号:");
		loginname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		loginname.setSize(73, 30);
		loginname.setLocation(57, 150);
		getContentPane().add(loginname);
		
		loginname_1=new JLabel("密码：");
		loginname_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		loginname_1.setSize(73, 30);
		loginname_1.setLocation(57, 200);
		getContentPane().add(loginname_1);
		
		username = new JComboBox(new Object[]{"111","222","333"});
		username.setSize(230, 30);
		username.setLocation(130, 150);
		getContentPane().add(username);

		passwordInput = new JPasswordField();
		passwordInput.setSize(230, 30);
		passwordInput.setLocation(130, 200);
		getContentPane().add(passwordInput);

		login = new JButton("登录");
		login.setFont(new Font("宋体", Font.BOLD, 15));
		// login.setBorder(BorderFactory.createLineBorder(Color.red));
	//	login.addMouseListener(new MyMouseListener(this));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1.先做表单验证
				//trim是去除字符串前后空白
				String yourInputUsername=username.getSelectedItem().toString().trim();
				String yourInputPassword=passwordInput.getText().toString();//密码不用去前后
				if(yourInputUsername.length()<3){
					JOptionPane.showMessageDialog(LoginFrame.this, "账号长度不够!", "温馨提示", JOptionPane.ERROR_MESSAGE);
					return;//不执行接下来的代码
				}else{
					if(yourInputPassword.length()<3){
						JOptionPane.showMessageDialog(LoginFrame.this, "密码长度不够!", "温馨提示", JOptionPane.ERROR_MESSAGE);
						passwordInput.requestFocus();//焦点获得
						return;
					}else{
						
						
						//2.建立和服务器的连接（socket连接）
						if(client!=null){
							try {
								client=new Socket(ServerFrameUIConfig.serverIP, ServerFrameUIConfig.serverPort);
								//消息要封装成对象，所以，要传递消息要用Object流
								out=new ObjectOutputStream(client.getOutputStream());
								in=new ObjectInputStream(client.getInputStream());
							} catch (Exception e1) {
								e1.printStackTrace();
								JOptionPane.showConfirmDialog(LoginFrame.this, "无法连接服务器，请检查网络!", "温馨提示", JOptionPane.ERROR_MESSAGE);
							}
						}
						
						//3.在socket的基础上获取输入输出流，然后用输出流将消息发送给服务器，让服务器校验我们的账号和密码是否成功	
						//因为我们将消息封装成特定的类型，所以，每次再给服务器发送消息时，都要封装成特定的消息对象才可以
						
						try {
							MessageBox loginMessage=new MessageBox();
						    User willLoginUser=new User(yourInputUsername,yourInputPassword);
						    loginMessage.setFrom(willLoginUser);
						    loginMessage.setType("login");
						    
							out.writeObject(loginMessage); 
							out.flush();
							
							//当客户端把登陆消息发送出去后，应该立马读取服务器回发的登陆结果消息
							MessageBox result=(MessageBox)in.readObject();
							if(result.getFrom()==null){
								JOptionPane.showConfirmDialog(LoginFrame.this, "登陆失败,请检查用户名和密码!", "温馨提示", JOptionPane.ERROR_MESSAGE);
							}else{
								User u=result.getFrom();//服务器发来登录资料
								System.out.println(u);
								MainFrame m=new MainFrame();
								m.setVisible(true);
								LoginFrame.this.setVisible(false);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
					}
				}
			}
		});
		login.setSize(110, 40);
		login.setLocation(130, 260);
		getContentPane().add(login);

		register = new JButton("注册");
		register.setFont(new Font("宋体", Font.BOLD, 15));
		register.setSize(110, 40);
		register.setLocation(250, 260);
		getContentPane().add(register);
		
		headImage = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resourses/images/3.jpg")));
		// headImage.setBackground(Color.blue);//设置边框颜色
		// headImage.setBorder(BorderFactory.createLineBorder(Color.red));//组件边框颜色
		headImage.setSize(500, 400);
		headImage.setLocation(0, 0);
		getContentPane().add(headImage);
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
