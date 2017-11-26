package com.oracle.view;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class ChatFrame extends JFrame {
	public ChatFrame() {
		setSize(600, 450);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setTitle("正在和zzq聊天");
		setIconImage(Toolkit.getDefaultToolkit().createImage("resourses/images/66.jpg"));

	}

	public static void main(String[] args) {
		new ChatFrame();

	}

}
