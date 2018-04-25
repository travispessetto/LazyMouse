package com.pessetto.lazymouse;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;

import com.pessetto.lazymouse.background.MouseMover;
import com.pessetto.lazymouse.background.MouseOS;


public class LazyMouse
{
	Point mPosition;
	Thread mMovingThread;
	
	public LazyMouse() throws InterruptedException
	{
		mPosition = MouseInfo.getPointerInfo().getLocation();
		displayMousePosition();
	}
	
	public static void main(String[] args) throws InterruptedException, IOException, AWTException
	{	
		if(args.length == 1)
		{
			if(args[0].toLowerCase().equals("enable-network"))
			{
				MouseOS mouseOS = new MouseOS();
				mouseOS.enableNetwork();
				return;
			}
			else if(args[0].toLowerCase().equals("disable-network"))
			{
				MouseOS mouseOS = new MouseOS();
				mouseOS.disableNetwork();
				return;
			}
		}
		System.out.println("Starting");
		LazyMouse mouse = new LazyMouse();
		mouse.startMouseMovingThread();
		MouseTrayIcon mouseTrayIcon = new MouseTrayIcon();
		while(!mouseTrayIcon.exit().get())
		{
			Thread.sleep(100);
		}
		System.out.println("Exiting");
		mouse.stopMouseMovingThread();
		System.exit(0);
	}
	
	public boolean isActive()
	{
		return mMovingThread.isAlive();
	}
	
	private void displayMousePosition()
	{
		System.out.println("Mouse is at: " + mPosition.getX() + ", " + mPosition.getY());
	}
	
	public void startMouseMovingThread() throws InterruptedException
	{
		 MouseMover mouseMover = new MouseMover();
		 mMovingThread = new Thread(mouseMover);
		 mMovingThread.start();
	}
	
	public void stopMouseMovingThread() throws InterruptedException
	{
		if(this.isActive())
		{
			mMovingThread.interrupt();
			mMovingThread.join(1000);
		}
	}
}
