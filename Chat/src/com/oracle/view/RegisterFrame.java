package com.oracle.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.tools.Tool;

import com.oracle.model.MessageBox;
import com.oracle.model.User;

import javafx.scene.image.Image;
import sun.awt.image.ToolkitImage;
import sun.invoke.util.BytecodeName;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class RegisterFrame extends JFrame {

	private Socket Client;
	private LoginFrame login;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JRadioButton rdbtnNewRadioButton,rdbtnNewRadioButton_1;
	private JTextField textField_2;
	private JTextField textField;
	private JComboBox comboBox,comboBox_1;
	private JTextArea textArea;
	private JButton btnNewButton,btnNewButton_1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public RegisterFrame(ObjectOutputStream out,ObjectInputStream in,LoginFrame login) {
		this.login=login;
		this.out=out;
		this.in=in;
		
		setTitle("注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 600);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().createImage("resourses/images/头像1.jpg"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户账号：");
		lblNewLabel.setBounds(30, 40, 70, 20);
		contentPane.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(110, 40, 140, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("用户密码：");
		lblNewLabel_1.setBounds(30, 80, 70, 20);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 80, 140, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("确认密码：");
		lblNewLabel_2.setBounds(30, 120, 70, 20);
		contentPane.add(lblNewLabel_2);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(110, 120, 140, 20);
		contentPane.add(passwordField_1);
		
		JLabel label = new JLabel("性别：");
		label.setBounds(30, 160, 70, 20);
		contentPane.add(label);
		
		rdbtnNewRadioButton = new JRadioButton("男");
		rdbtnNewRadioButton.setBounds(120, 160, 60, 20);
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("女");
		rdbtnNewRadioButton_1.setBounds(190, 160, 60, 20);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("年龄：");
		lblNewLabel_3.setBounds(30, 200, 70, 20);
		contentPane.add(lblNewLabel_3);
		
		comboBox = new JComboBox();
		for (int i = 1; i <101; i++) {
			comboBox.addItem(i+"");
		}
		comboBox.setBounds(110, 200, 140, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("昵称：");
		lblNewLabel_4.setBounds(30, 240, 70, 20);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(110, 240, 140, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("头像：");
		lblNewLabel_5.setBounds(30, 280, 70, 20);
		contentPane.add(lblNewLabel_5);
		
		
		String[] image=new String[10];
		for(int i=100;i<110;i++){
			image[i-100]="resourses/images/"+i+".gif";
		}
		comboBox_1 = new JComboBox();
       for(int i=100;i<=110;i++){
    	   ImageIcon icon=new ImageIcon(Toolkit.getDefaultToolkit().createImage("resourses/images/"+i+".gif"));
    	   icon.setDescription("resourses/images/"+i+".gif");
    	   comboBox_1.addItem(icon);
       }
		comboBox_1.setBounds(110, 280, 140, 52);
		contentPane.add(comboBox_1);
		
		JLabel label_1 = new JLabel("个性签名：");
		label_1.setBounds(30, 360, 70, 20);
		contentPane.add(label_1);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(110, 360, 140, 90);
		contentPane.add(textArea);
		
		btnNewButton = new JButton("提交");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.提取用户信息
				String zhanghao=textField_2.getText().trim();
				String mima=passwordField.getText();
				String mimaconfirm=passwordField_1.getText();
				String sex=rdbtnNewRadioButton.isSelected()?"男":"女";
				int nianling=Integer.parseInt(comboBox.getSelectedItem().toString());
				String nicheng=textField.getText().trim();
				String touxiang="images/1.jpg";
				String qianming=textArea.getText();
				//2.表单验证
				if(zhanghao.length()<3){
					JOptionPane.showConfirmDialog(RegisterFrame.this, "账号长度不够！", "温馨提示", JOptionPane.ERROR_MESSAGE);
					textField_2.requestFocus();
					return;
				}else if(mima.length()<3){
					JOptionPane.showConfirmDialog(RegisterFrame.this, "密码长度不够！", "温馨提示", JOptionPane.ERROR_MESSAGE);
					passwordField.requestFocus();
					return;
				}else if(!mimaconfirm.equals(mima)){
					JOptionPane.showConfirmDialog(RegisterFrame.this, "两次密码输入不一致！", "温馨提示", JOptionPane.ERROR_MESSAGE);
					passwordField_1.requestFocus();
					return;
				}else if(nicheng==null){
					JOptionPane.showConfirmDialog(RegisterFrame.this, "昵称输入不得为空！", "温馨提示", JOptionPane.ERROR_MESSAGE);
					textField.requestFocus();
					return;
				}
				//3.封装成MessageBox
				User u=new User(zhanghao, mima, sex, nianling, nicheng, qianming, touxiang);
				MessageBox registerMessage=new MessageBox();
				registerMessage.setFrom(u);
				registerMessage.setType("register");
				//4.使用序列化写给服务器，让服务器注册
				try {
					//在loginFrame界面里面的注册按钮已经与服务器建立了连接，这里不需要再次连接
					RegisterFrame.this.out.writeObject(registerMessage);
					RegisterFrame.this.out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("注册内容已发送");
				//5.根据服务器给我的回复的注册消息进一步跳转界面
				try {
					MessageBox result=(MessageBox)RegisterFrame.this.in.readObject();
					JOptionPane.showConfirmDialog(RegisterFrame.this, "注册"+(result.getContent().equals("true")?"成功":"失败"+"!"), "温馨提示", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(30, 480, 93, 40);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("去登录");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) { 
				RegisterFrame.this.login.setVisible(true);
				RegisterFrame.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(157, 480, 93, 40);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
		
		
		
		
		
		
	}
}
