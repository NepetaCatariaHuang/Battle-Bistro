package Mygame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameStarter extends Application {

	public static Stage primaryStage;
	@Override
	public void start(Stage tmp) throws Exception {
		primaryStage=tmp;
		Parent root=FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		Scene HomeScreen=new Scene(root);
		primaryStage.setScene(HomeScreen);
		primaryStage.setResizable(false);
		HomeScreen.getRoot().requestFocus();
		primaryStage.show();

	
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
