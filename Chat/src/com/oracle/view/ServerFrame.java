package com.oracle.view;

import java.awt.Color;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.oracle.control.ServerFrameUIConfig;
import com.oracle.model.MessageBox;


public class ServerFrame extends JFrame{
	private ServerSocket  server;
	private AllButtonListener  listener;//内部类监听对象
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JTable table;
	private TableModel  model;
	private JScrollPane scrollPane_1;
	private JTextArea textArea;
	private JLabel lblNewLabel_1;
	private Panel panel_1;
	private Panel panel;
	private JButton button;
	private JButton button_1;
	
    {
		
		listener=new AllButtonListener();
	}
    
    public static void main(String[] args) {
		ServerFrame frame=new ServerFrame();
		frame.setVisible(true);
	}
	
	public ServerFrame() {//异常
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ServerFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	setTitle(ServerFrameUIConfig.serverFrameTitle);
	 	setSize(ServerFrameUIConfig.serverFrameWidth, ServerFrameUIConfig.serverFrameHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("在线用户列表");
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
		lblNewLabel.setBounds(18, 21, 241, 15);
		contentPane.add(lblNewLabel);
		
		model=new DefaultTableModel(new Object[]{"登陆IP","用户昵称"},0) ;
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(18, 48, 241, 296);
		contentPane.add(scrollPane);
		
		lblNewLabel_1 = new JLabel("所有用户发送的消息列表");
		lblNewLabel_1.setBorder(BorderFactory.createLineBorder(Color.gray));
		lblNewLabel_1.setBounds(298, 21, 350, 15);
		contentPane.add(lblNewLabel_1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1 = new JScrollPane(textArea);
		scrollPane_1.setBounds(298, 48, 350, 296);
		contentPane.add(scrollPane_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(18, 392, 630, 170);
		contentPane.add(tabbedPane);
		
		panel_1 = new Panel();
		tabbedPane.addTab("基本控制", null, panel_1, null);
		panel_1.setLayout(null);
		
		button = new JButton("启动服务器");
		button.addActionListener(listener);
		button.setBounds(72, 45, 139, 39);
		panel_1.add(button);
		
		button_1 = new JButton("停止服务器");
		button_1.addActionListener(listener);
		button_1.setBounds(283, 45, 139, 39);
		panel_1.add(button_1);
		
		setLocationRelativeTo(null);
	}
	
	//监听事件
	class AllButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button){
				try {
					server=new ServerSocket(ServerFrameUIConfig.serverPort);
					button.setEnabled(false);//设置启动键启动一次后就不能用了
					//小窗口提示服务器启动成功
					JOptionPane.showMessageDialog(ServerFrame.this, "服务器启动成功！", "温馨提示",JOptionPane.INFORMATION_MESSAGE);
					//服务器连接用多线程
					new Thread(){
						public void run(){
							while(true){
								try {
									Socket c=server.accept();
									System.out.println(c.getInetAddress());//显示连进来的客户端IP
									ObjectOutputStream out=new ObjectOutputStream(c.getOutputStream());
									ObjectInputStream in=new ObjectInputStream(c.getInputStream());
									
									//有一个客户端连接进来，开启一个线程，针对他单独和服务器通讯
									
								} catch (IOException e) {
									e.printStackTrace();
								}
								
							}
						}
					}.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
	}
		
		
	}

class ClientMessageReceiveThread extends Thread{
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientMessageReceiveThread(ObjectOutputStream out, ObjectInputStream in) {
		super();
		this.out = out;
		this.in = in;
	}
	
	public void run(){
		while(true){
			try {
				MessageBox m=(MessageBox)in.readObject();
				
				if(m.getType().equals("login")){
					processLoginMessage(m);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 这是处理登录消息的方法
	 * @param m
	 */
	public void processLoginMessage(MessageBox m){
		
	}
	
}
	
}

