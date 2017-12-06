package com.oracle.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oracle.control.TaskIcon;
import com.oracle.model.MessageBox;
import com.oracle.model.User;

import sun.awt.image.ToolkitImage;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class ChatFrame extends JFrame {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private User my,your;

	private JPanel contentPane;
	private JTextArea textArea,textArea_1;
	private JButton btnNewButton_2,button,btnNewButton,btnNewButton_1;
	private JLabel lblNewLabel,lblNewLabel_1;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ChatFrame(User my,User your,ObjectInputStream in,ObjectOutputStream out) {
		this.in=in;
		this.out=out;
		this.my=my;
		this.your=your;
	
		setTitle("和"+your.getNickname()+"热聊中");
		setIconImage(Toolkit.getDefaultToolkit().createImage(your.getImagePath()));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(0, 0, 387, 197);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		scrollPane_1.setBounds(0, 235, 387, 87);
		contentPane.add(scrollPane_1);
		
		btnNewButton_2 = new JButton("抖动");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_2.setBounds(10, 207, 82, 23);
		contentPane.add(btnNewButton_2);
		
		button = new JButton("截图");
		button.setBounds(114, 207, 93, 23);
		contentPane.add(button);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		btnNewButton = new JButton("发送");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//这里面是处理当用户点击发送按钮式要执行的业务代码
				//1.先获取编辑文本框里面的输入的要发送的文本
				String willSendMessage=textArea_1.getText();
				
				//2.先将消息封装成MessageBox发送给服务器
				MessageBox sendMessage=new MessageBox();
				sendMessage.setContent(willSendMessage);
				sendMessage.setFrom(ChatFrame.this.my);
				sendMessage.setType("textMessage");
				sendMessage.setTo(ChatFrame.this.your);
				
				//3.发送MessageBox给服务器
				try {
					ChatFrame.this.out.writeObject(sendMessage);
					ChatFrame.this.out.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				//4.接受服务器给我回发的时间
				try {
//					MessageBox time=(MessageBox)ChatFrame.this.in.readObject();
					String nowTime=new Date().toLocaleString();
					textArea.append(ChatFrame.this.my.getNickname()+":  "+nowTime+"\r\n"+willSendMessage+"\r\n");
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				textArea_1.setText("");
				
			}
		});
		btnNewButton.setBounds(68, 330, 93, 30);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("关闭");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(226, 330, 93, 30);
		contentPane.add(btnNewButton_1);
		
		//对方头像
		lblNewLabel = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage(your.getImagePath()).getScaledInstance(135, 200, Image.SCALE_DEFAULT)));
		lblNewLabel.setBounds(387, 0, 157, 197);
		contentPane.add(lblNewLabel);
		
		//我的头像
		lblNewLabel_1 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage(my.getImagePath()).getScaledInstance(125, 151, Image.SCALE_DEFAULT)));
		lblNewLabel_1.setBounds(387, 196, 157, 164);
		contentPane.add(lblNewLabel_1);
		
		
	}
	
/*	public User open(){
		MessageBox openChatUser=new MessageBox();
    	openChatUser.setFrom(your);
    	openChatUser.setType("openChat");
    	try {
//    		System.out.println(openChatUser);
			ChatFrame.this.out.writeObject(openChatUser);
			ChatFrame.this.out.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	//接收消息
    	try {
			MessageBox result=(MessageBox)ChatFrame.this.in.readObject();
			User u=result.getTo();
			return u;
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		} 
    	
	}
*/
	public JTextArea getTextArea() {
		return textArea;
	}
	
	
}
