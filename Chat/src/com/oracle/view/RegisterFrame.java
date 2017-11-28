package com.oracle.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_2;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterFrame() {
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
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("男");
		rdbtnNewRadioButton.setBounds(120, 160, 60, 20);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("女");
		rdbtnNewRadioButton_1.setBounds(190, 160, 60, 20);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("年龄：");
		lblNewLabel_3.setBounds(30, 200, 70, 20);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
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
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(110, 280, 140, 60);
		contentPane.add(comboBox_1);
		
		JLabel label_1 = new JLabel("个性签名：");
		label_1.setBounds(30, 360, 70, 20);
		contentPane.add(label_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(110, 360, 140, 90);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("提交");
		btnNewButton.setBounds(30, 480, 93, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("去登录");
		btnNewButton_1.setBounds(157, 480, 93, 40);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
		
		
		
		
		
		
	}
}
