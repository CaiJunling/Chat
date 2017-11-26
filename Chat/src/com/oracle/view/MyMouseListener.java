package com.oracle.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
	private LoginFrame l;

	public MyMouseListener(LoginFrame l) {
		super();
		this.l = l;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");
		l.getLogin().setBackground(Color.red);

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		System.out.println("mouse exited");
		l.getLogin().setBackground(Color.gray);

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
