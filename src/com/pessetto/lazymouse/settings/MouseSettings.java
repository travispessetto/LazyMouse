package com.pessetto.lazymouse.settings;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class MouseSettings 
{
	@FXML
	private TextField timeOutMinutes;
	@FXML
	private CheckBox lockScreenTimeout;
	@FXML
	private CheckBox networkDisable;
	
	@FXML
	public void ApplySettings(Event e)
	{
		
	}
}
