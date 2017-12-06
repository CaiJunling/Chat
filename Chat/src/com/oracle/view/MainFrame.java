package com.oracle.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.oracle.control.TaskIcon;
import com.oracle.model.MessageBox;
import com.oracle.model.User;
import com.oracle.view.ServerFrame.AllButtonListener;

import sun.awt.image.ToolkitImage;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
	//为了能记录所有和我聊过天的好友信息(打开过聊天界面的好友信息)，我们定义一个集合存储这些资料
	private Map<String,ChatFrame> allFrames=new HashMap<>();
	//因为本项目要求一个用户在多个界面中共享一个socket建立的流(降低服务器的链接压力)
	//所以，我们需要在本类定义一个ObjectIn,ObjectOut来接受登录界面已经建立好的两个流
	private ObjectInputStream  in;
	private ObjectOutputStream  out;
	private User user;
	private JPanel contentPane;
	private JLabel lblNewLabel,lblNewLabel_1;
	private JTabbedPane tabbedPane;
	private JTree tree;

	private TaskIcon icon;
	private JScrollPane scrollPane;
	private JTree tree_1;
	
	

	public MainFrame(User user,ObjectInputStream  in,ObjectOutputStream  out) {
		this.user=user;
		this.in=in;
		this.out=out;
		
		
		icon=new TaskIcon(user.getNickname());
		setSize(300, 600);
		setLocation(50, 50);
		setVisible(true);
		setResizable(false);
		setTitle("主界面");
		setIconImage(Toolkit.getDefaultToolkit().createImage("resourses/images/头像1.jpg"));
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage(user.getImagePath()).getScaledInstance(128, 128, Image.SCALE_DEFAULT)));
		
		lblNewLabel.setBounds(0, 0, 114, 125);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(user.getNickname(),JLabel.CENTER);//昵称,昵称居中
		lblNewLabel_1.setBounds(124, 0, 160, 26);
		contentPane.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea(user.getSignature());//个性签名
		textArea.setEditable(false);
		textArea.setBounds(124, 27, 160, 73);
		contentPane.add(textArea);
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(10, 142, 274, 416);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("好友", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
//		tree_1 = new JTree();
//		scrollPane.setViewportView(tree_1);
		
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("root");
		Map<String,HashSet<User>> allFriends=user.getFriends();
		Set<String> allGroupNames=allFriends.keySet();
		for(String groupName:allGroupNames){
			DefaultMutableTreeNode group=new DefaultMutableTreeNode(groupName);
			HashSet<User> friendsOfGroup=allFriends.get(groupName);
			for(User u:friendsOfGroup){
				DefaultMutableTreeNode friend=new DefaultMutableTreeNode(u.getNickname()+"["+u.getUsername()+"]");
			    group.add(friend);
			}
			root.add(group);
		}
		tree = new JTree(root);
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1&&e.getClickCount()==2){
					TreePath path=tree.getSelectionPath();//获取树结点的路径
					DefaultMutableTreeNode lastNode=(DefaultMutableTreeNode)path.getLastPathComponent();//获取最后一个结点的内容
				    if(lastNode.isLeaf()){//如果最后一个结点是叶子
				    	String username=lastNode.toString();
				    	String num=username.substring(username.lastIndexOf("[")+1,username.length()-1);
				    	
				    	User your=new User();
				    	your.setUsername(num);
				    	for(String friendNum:allFrames.keySet()){
				    		if(friendNum.equals(num)){
				    			allFrames.get(friendNum).setVisible(true);
				    			return;
				    		}
				    	}
				    	
				    	try {
							ObjectInputStream inChat=new ObjectInputStream(new FileInputStream("databases/"+num+".qq"));
							User you=(User)inChat.readObject();
							ChatFrame chat=new ChatFrame(MainFrame.this.user,you,MainFrame.this.in,MainFrame.this.out);
				    	    chat.setVisible(true);
				    	    allFrames.put(num, chat);
						} catch (Exception e1) {
							e1.printStackTrace();
						} 
				    	/*//封装一个MessageBox,用于传输打开聊天界面的对方的信息
				    	
				    	MessageBox openChatUser=new MessageBox();
				    	openChatUser.setFrom(your);
				    	openChatUser.setType("openChat");
				    	try {
//				    		System.out.println(openChatUser);
							MainFrame.this.out.writeObject(openChatUser);
							MainFrame.this.out.flush();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				    	//接收消息
				    	try {
							MessageBox result=(MessageBox)MainFrame.this.in.readObject();
							User u=result.getTo();
							
							ChatFrame chat=new ChatFrame(MainFrame.this.user,u,MainFrame.this.in,MainFrame.this.out);
				    	    chat.setVisible(true);
				    	    allFrames.put(num, chat);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						} 
				    	*/
						
				    	
				   
				    	
				    	
				    }
				}
			}
		});
		tree.setRootVisible(false);
		scrollPane.setViewportView(tree);
		
		JButton btnNewButton = new JButton("修改信息");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateFrame up=new UpdateFrame(MainFrame.this.user,MainFrame.this.in,MainFrame.this.out);
				up.setVisible(true);
				MainFrame.this.setVisible(false);
				
			}
		});
		btnNewButton.setBounds(124, 101, 160, 20);
		contentPane.add(btnNewButton);
		
		MessageReceiverThread t=new MessageReceiverThread();
		t.start();
	}
	
	//主界面这个类应该定义一个线程，该线程运行时创建一个线程实例，在主界面单独运行，用来时时刻刻接受"服务器"给我的消息
	class MessageReceiverThread extends Thread{
		@Override
		public void run() {
			MessageBox receivedMessage=null;
			try {
				A:while((receivedMessage=(MessageBox)in.readObject())!=null){
					System.out.println(receivedMessage);
					for(String friendNum:allFrames.keySet()){
						if(friendNum.equals(receivedMessage.getFrom().getUsername())){
							allFrames.get(friendNum).getTextArea().append(receivedMessage.getFrom().getNickname()+":  "+receivedMessage.getTime()+"\r\n"+receivedMessage.getContent()+"\r\n");
							allFrames.get(friendNum).setVisible(true);
						}
						continue A;
					}
					ChatFrame c=new ChatFrame(user, receivedMessage.getFrom(), in, out);
					
					c.getTextArea().append(receivedMessage.getFrom().getNickname()+":  "+receivedMessage.getTime()+"\r\n"+receivedMessage.getContent()+"\r\n");
					c.setVisible(true);
					
					allFrames.put(receivedMessage.getFrom().getUsername(), c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
}
