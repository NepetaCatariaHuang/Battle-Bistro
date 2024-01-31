package Mygame;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class RulesController   {

	
	@FXML Pane rules;
	@FXML Button start;
	@FXML ImageView Left;
	@FXML ImageView Right;
	@FXML Text line1;
	@FXML Text line2;
	@FXML Text line3;
	@FXML Text line4;
	
	public void LeftClicked() {
		
		if(rules.getLayoutX()<0) {				
			line1.setOpacity(0);
			line2.setOpacity(0);
			line3.setOpacity(0);
			line4.setOpacity(0);
					rules.setLayoutX(rules.getLayoutX()+800);
			Timeline fps1=new Timeline(new KeyFrame(Duration.millis(10),(e)-> {
	
				line1.setOpacity(line1.getOpacity()+0.01);
				line2.setOpacity(line2.getOpacity()+0.01);
				line3.setOpacity(line3.getOpacity()+0.01);
				line4.setOpacity(line4.getOpacity()+0.01);
					}));
			fps1.setCycleCount(100);
			fps1.play();
		}
	}
	
	public void RightClicked() {
			if(rules.getLayoutX()>-4000) {
				
				
				line1.setOpacity(0);
				line2.setOpacity(0);
				line3.setOpacity(0);
				line4.setOpacity(0);
				rules.setLayoutX(rules.getLayoutX()-800);
				Timeline fps1=new Timeline(new KeyFrame(Duration.millis(10),(e)-> {
	
					line1.setOpacity(line1.getOpacity()+0.01);
					line2.setOpacity(line2.getOpacity()+0.01);
					line3.setOpacity(line3.getOpacity()+0.01);
					line4.setOpacity(line4.getOpacity()+0.01);
						}));
				fps1.setCycleCount(100);
				fps1.play();
			}
	}
	
	public void GameStart() throws IOException {
		Parent root2=FXMLLoader.load(getClass().getResource("Game.fxml"));
		HomeScreenController.Game=new Scene(root2);
		GameStarter.primaryStage.setScene(HomeScreenController.Game);
		HomeScreenController.Game.getRoot().requestFocus();
		GameStarter.primaryStage.show();
		GameStarter.primaryStage.setMaximized(true);
	}
	
	

}
