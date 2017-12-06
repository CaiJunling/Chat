package com.oracle.control;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;



public class TaskIcon {
	
	public TaskIcon(String u){
        SystemTray sysTray=SystemTray.getSystemTray();
		
		final TrayIcon icon=new TrayIcon(Toolkit.getDefaultToolkit().createImage("resourses/images/logo.png"),u+ "在线中");
		icon.setImageAutoSize(true);
		try {
			sysTray.add(icon);
//			icon.setImage(Toolkit.getDefaultToolkit().createImage("resourses/images/logo.png"));
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	/*public static void main(String[] args) {
		//任务栏
		SystemTray sysTray=SystemTray.getSystemTray();
		
		final TrayIcon icon=new TrayIcon(Toolkit.getDefaultToolkit().createImage("resourses/images/logo.png"), "蔡蔡在线中");
		icon.setImageAutoSize(true);
		try {
			sysTray.add(icon);
//			icon.setImage(Toolkit.getDefaultToolkit().createImage("resourses/images/logo.png"));
		} catch (AWTException e) {
			e.printStackTrace();
		}
		

		

	
	}
*/
}
