package com.pessetto.lazymouse;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MouseTrayIcon implements ActionListener
{
	private TrayIcon mTrayIcon;
	private PopupMenu mTrayMenu;
	private AtomicBoolean mExit;
	private SystemTray mSystemTray;
	
	public MouseTrayIcon() throws IOException, AWTException
	{
		createTrayMenu();
		addIconToSystemTray();
		mExit = new AtomicBoolean(false);
	}
	
	public AtomicBoolean exit()
	{
		return mExit;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		if(actionEvent.getActionCommand().equals("Exit"))
		{;
		    mSystemTray.remove(mTrayIcon);
			mExit.set(true);
		}
		
	}
	
	private void addIconToSystemTray() throws IOException, AWTException
	{
		Image icon = ImageIO.read(MouseTrayIcon.class.getClassLoader().getResourceAsStream("images/mouse.png"));
		mTrayIcon = new TrayIcon(icon);
		mTrayIcon.setImageAutoSize(true);
		mTrayIcon.setToolTip("LazyMouse Mouse Jiggler");
		mTrayIcon.setPopupMenu(mTrayMenu);
		mSystemTray = null;
		if(!java.awt.SystemTray.isSupported())
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("System tray minimization not supported.");
			alert.showAndWait();
		}
		else
		{
			mSystemTray = java.awt.SystemTray.getSystemTray();
			mSystemTray.add(mTrayIcon);
		}
	}
	
	private void createTrayMenu()
	{
		mTrayMenu = new PopupMenu();
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(this);
		mTrayMenu.add(exitItem);
	}

}
