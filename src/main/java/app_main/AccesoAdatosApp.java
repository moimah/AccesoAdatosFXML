package app_main;

import controller.AccesoAdatosController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccesoAdatosApp extends Application {
	
	private AccesoAdatosController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller = new AccesoAdatosController();
		
		Scene scene = new Scene(controller.getViewAccesoAdatos(), 800,600);
		primaryStage.setTitle("Acceso a datos");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		
		launch(args);

	}

}
