package com.thanhdo.game;

import javax.swing.*;

public class Window extends JFrame
{
	public Window(){
		setTitle("Project U  version 0.0.1-super_alpha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GamePanel(1600,900));
		setIgnoreRepaint(true);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
