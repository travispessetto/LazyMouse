package com.pessetto.lazymouse.settings;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SettingsFXMLThread implements Runnable{

	@Override
	public void run() {
		try
		{
			Stage stage = new Stage();
			GridPane settingsPane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Settings.fxml"));
			Scene scene = new Scene(settingsPane);
			stage.setTitle("LazyMouse Settings");
			stage.setScene(scene);
			stage.show();
		}
		catch(IOException ex)
		{
			ex.printStackTrace(System.err);
			System.err.println("Failed to open FXML file");
		}
		
	}

}
