package com.oracle.view;

import java.awt.Color;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.oracle.control.ServerFrameUIConfig;


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
	private JButton button_3;
	private JButton button_2;
	private JButton button_1;
	private JButton btnNewButton;
	private JButton button_4;
	
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
		lblNewLabel_1.setBounds(298, 21, 675, 15);
		contentPane.add(lblNewLabel_1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1 = new JScrollPane(textArea);
		scrollPane_1.setBounds(298, 48, 675, 296);
		contentPane.add(scrollPane_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(18, 392, 955, 170);
		contentPane.add(tabbedPane);
		
		panel_1 = new Panel();
		tabbedPane.addTab("基本控制", null, panel_1, null);
		panel_1.setLayout(null);
		
		button = new JButton("启动服务器");
		button.addActionListener(listener);
		button.setBounds(72, 21, 93, 23);
		panel_1.add(button);
		
		button_1 = new JButton("停止服务器");
		button_1.addActionListener(listener);
		button_1.setBounds(196, 21, 93, 23);
		panel_1.add(button_1);
		
		button_2 = new JButton("断开所有用户");
		button_2.addActionListener(listener);
		button_2.setBounds(325, 21, 116, 23);
		panel_1.add(button_2);
		
		button_3 = new JButton("修改服务端端口");
		button_3.addActionListener(listener);
		button_3.setBounds(481, 21, 145, 23);
		panel_1.add(button_3);
		
		btnNewButton = new JButton("移除指定用户");
		btnNewButton.addActionListener(listener);
		btnNewButton.setBounds(636, 21, 116, 23);
		panel_1.add(btnNewButton);
		
		button_4 = new JButton("中断所有消息传输");
		button_4.setBounds(783, 21, 157, 23);
		button_4.addActionListener(listener);
		panel_1.add(button_4);
		
		panel = new Panel();
		tabbedPane.addTab("高级控制", null, panel, null);
		
		
		setLocationRelativeTo(null);
	}
	
	//监听事件
	class AllButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button){
				try {
					server=new ServerSocket(ServerFrameUIConfig.serverPort);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
	}
		
		
	}

	

}
