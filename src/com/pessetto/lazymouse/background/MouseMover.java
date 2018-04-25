package com.pessetto.lazymouse.background;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;

public class MouseMover implements Runnable, GlobalKeyListener {

	private Point mPosition;
	private Robot mRobot;
	private AtomicInteger mNotMoveCount;
	
	public MouseMover()
	{
		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);
		keyboardHook.addKeyListener(this);
	}
	
	@Override
	public void run() 
	{
		try
		{
			
			mNotMoveCount = new AtomicInteger(0);
			mPosition = MouseInfo.getPointerInfo().getLocation();
			mRobot = new Robot();
			while(!Thread.interrupted())
			{
				Thread.sleep(10000);
				MouseOS mouseOS = new MouseOS();
				Point newPosition = MouseInfo.getPointerInfo().getLocation();
				if(mPosition.getX() == newPosition.getX() && mPosition.getY() == newPosition.getY())
				{
					int oldX = (int)mPosition.getX();
					int oldY = (int)mPosition.getY();
					int newX = oldX + 1;
					int newY = oldY + 1;
					mRobot.mouseMove(newX, newY);
					mRobot.mouseMove(oldX, oldY);
					mNotMoveCount.incrementAndGet();
				}
				else
				{
					mNotMoveCount.set(0);
				}
				// Timeout in minutes
				if(mNotMoveCount.get() >= 5)
				{
					mouseOS.disableNetwork();
					mouseOS.lockComputer();
					//Thread.currentThread().interrupt();
				}
				mPosition = newPosition;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
		}
	}

	@Override
	public void keyPressed(GlobalKeyEvent arg0) {
		mNotMoveCount.set(0);
	}

	@Override
	public void keyReleased(GlobalKeyEvent arg0) {
		mNotMoveCount.set(0);
	}

}
