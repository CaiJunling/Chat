package com.oracle.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sun.awt.image.ToolkitImage;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class ChatFrame extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatFrame frame = new ChatFrame();
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
	public ChatFrame() {
		setTitle("和小薛薛热聊中");
		setIconImage(Toolkit.getDefaultToolkit().createImage("resourses/images/头像1.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(0, 0, 387, 209);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		scrollPane_1.setBounds(0, 209, 387, 113);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		JButton btnNewButton = new JButton("发送");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(68, 330, 93, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("关闭");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(226, 330, 93, 30);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resourses/images/头像2.jpg")));
		lblNewLabel.setBounds(387, 0, 135, 209);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("我的头像");
		lblNewLabel_1.setBounds(387, 209, 125, 151);
		contentPane.add(lblNewLabel_1);
	}
}
