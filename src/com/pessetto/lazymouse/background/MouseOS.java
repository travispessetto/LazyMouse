package com.pessetto.lazymouse.background;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Scanner;

public class MouseOS
{

	private String mOS;
	public MouseOS()
	{
		mOS = System.getProperty("os.name").toLowerCase();
	}
	
	public void disableNetwork()
	{
		try
		{
			System.out.println("Disable network");
			File file = null;
			String resourceFile = null;
			
			if(isWindows())
			{ 
				resourceFile = "bat/networkRelease.txt";
				file = Files.createTempFile("LazyMouse",".bat").toFile();
			}
			if(file != null && resourceFile != null)
			{
				performAction(resourceFile,file);
			}
		}
		catch(IOException ex)
		{
			System.err.println("Could not use OS to disable network");
		}
	}
	
	public void enableNetwork()
	{
		try
		{
			System.out.println("Enable network");
			File file = null;
			String resourceFile = null;
			
			if(isWindows())
			{ 
				resourceFile = "bat/networkRenew.txt";
				file = Files.createTempFile("LazyMouse",".bat").toFile();
			}
			if(file != null && resourceFile != null)
			{
				performAction(resourceFile,file);
			}
		}
		catch(IOException ex)
		{
			System.err.println("Could not use OS to enable network");
		}
	}
	
	public void lockComputer()
	{
		try
		{
			System.out.println("Lock Computer");
			File file = null;
			String resourceFile = null;
			if(isWindows())
			{
				resourceFile = "bat/lockComputer.txt";
				file = Files.createTempFile("LazyMouse", ".bat").toFile();
			}
			performAction(resourceFile,file);
		}
		catch(IOException ex)
		{
			System.err.println("Could not use OS to lock computer");
		}
	}
	
	private void executeTempFile(File tempFile)
	{
		try
		{
			tempFile.deleteOnExit();
			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec(tempFile.getAbsolutePath());
			pr.waitFor();
			System.out.println("Process returned: " + pr.exitValue());
		}
		catch(IOException | InterruptedException ex)
		{
			ex.printStackTrace(System.err);
			System.err.println("Could not run temp file");
		}
	}
	
	private boolean isWindows()
	{
		return mOS.toLowerCase().indexOf("win") >= 0;
	}
	
	private void performAction(String resourceFile, File tempFile)
	{
		String resourceContents = loadResourceFileIntoString(resourceFile);
		System.out.println("Contents are:");
		System.out.println(resourceContents);
		loadResourceFileIntoTempFile(tempFile,resourceContents);
		executeTempFile(tempFile);
	}
	
	private void loadResourceFileIntoTempFile(File tempFile, String resourceString)
	{
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile)))
		{
			bw.write(resourceString);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			System.err.println("Failed to write to temp file");
			e.printStackTrace(System.err);
		}
}
	
	private String loadResourceFileIntoString(String resourceFile)
	{
		System.out.println("Resouce: " + resourceFile);
		String contents = null;
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceFile);
		try(Scanner scanner = new Scanner(in))
		{
			scanner.useDelimiter("\\Z");
			contents = scanner.next();
			scanner.close();
		}
		return contents;
	}
}
