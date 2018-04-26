package com.pessetto.lazymouse.settings;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SettingsThread extends Application implements Runnable{

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

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
