package Mygame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HomeScreenController implements Initializable, EventHandler<KeyEvent> {

	@FXML
	Pane field;
	@FXML
	ImageView start;
	@FXML
	ImageView synopisis;
	@FXML
	ImageView quit;
	@FXML
	ImageView title;
	@FXML
	ImageView beer;
	@FXML
	ImageView bouble;
	@FXML
	Text Tstart;
	@FXML
	Text Tsynopisis;
	@FXML
	Text Tquit;
	public static Scene Game, Synopsis;

	boolean anime1 = true, anime2 = true, anime3 = true;

	@Override
	public void handle(KeyEvent e) {

	}

	public void QuitDetected() {
		if (anime3)
			ImageChange(quit, Tquit);
		anime3 = false;
	}

	public void SynopisisDetected() {
		if (anime1)
			ImageChange(synopisis, Tsynopisis);
		anime1 = false;

	}

	public void StartDetected() {
		if (anime2)
			ImageChange(start, Tstart);
		anime2 = false;
	}

	public void ImageChange(ImageView a, Text b) {
		Timeline timeline = new Timeline();

		Image img2 = new Image("file:src/MenuButton/menuButton%20(2).png");
		Image img3 = new Image("file:src/MenuButton/menuButton%20(3).png");
		Image img4 = new Image("file:src/MenuButton/menuButton%20(4).png");
		Image img5 = new Image("file:src/MenuButton/menuButton%20(5).png");
		Image img6 = new Image("file:src/MenuButton/menuButton%20(6).png");
		Image img7 = new Image("file:src/MenuButton/menuButton%20(7).png");
		Image img8 = new Image("file:src/MenuButton/menuButton%20(8).png");
		Image img9 = new Image("file:src/MenuButton/menuButton%20(9).png");
		Image img10 = new Image("file:src/MenuButton/menuButton%20(10).png");
		Image img11 = new Image("file:src/MenuButton/menuButton%20(11).png");
		Image img12 = new Image("file:src/MenuButton/menuButton%20(12).png");
		Image img13 = new Image("file:src/MenuButton/menuButton%20(13).png");
		Image img14 = new Image("file:src/MenuButton/menuButton%20(14).png");
		Image img15 = new Image("file:src/MenuButton/menuButton%20(15).png");
		Image img16 = new Image("file:src/MenuButton/menuButton%20(16).png");
		KeyFrame p2 = new KeyFrame(Duration.millis(100), (e) -> {

			a.setImage(img2);
		});
		KeyFrame p3 = new KeyFrame(Duration.millis(200), (e) -> {

			a.setImage(img3);
		});
		KeyFrame p4 = new KeyFrame(Duration.millis(300), (e) -> {

			a.setImage(img4);
		});
		KeyFrame p5 = new KeyFrame(Duration.millis(400), (e) -> {

			a.setImage(img5);
		});
		KeyFrame p6 = new KeyFrame(Duration.millis(500), (e) -> {

			a.setImage(img6);
		});
		KeyFrame p7 = new KeyFrame(Duration.millis(600), (e) -> {

			a.setImage(img7);
		});
		KeyFrame p8 = new KeyFrame(Duration.millis(700), (e) -> {

			a.setImage(img8);
		});
		KeyFrame p9 = new KeyFrame(Duration.millis(800), (e) -> {

			a.setImage(img9);
		});
		KeyFrame p10 = new KeyFrame(Duration.millis(900), (e) -> {

			a.setImage(img10);
		});
		KeyFrame p11 = new KeyFrame(Duration.millis(1000), (e) -> {

			a.setImage(img11);
		});
		KeyFrame p12 = new KeyFrame(Duration.millis(1100), (e) -> {

			a.setImage(img12);
		});
		KeyFrame p13 = new KeyFrame(Duration.millis(1200), (e) -> {

			a.setImage(img13);
		});
		KeyFrame p14 = new KeyFrame(Duration.millis(1300), (e) -> {

			a.setImage(img14);
		});
		KeyFrame p15 = new KeyFrame(Duration.millis(1400), (e) -> {

			a.setImage(img15);

		});
		KeyFrame p16 = new KeyFrame(Duration.millis(1500), (e) -> {

			a.setImage(img16);
			b.setOpacity(1);
		});
		timeline.getKeyFrames().addAll(p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16);
		timeline.play();
	}

	public void StartPressed() throws IOException {

		Parent root2 = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Game = new Scene(root2);
		GameStarter.primaryStage.setScene(Game);
		Game.getRoot().requestFocus();
		GameStarter.primaryStage.show();
		GameStarter.primaryStage.setMaximized(true);
	}

	public void SynopsisPressed() throws IOException {

		Parent root3 = FXMLLoader.load(getClass().getResource("rules.fxml"));
		Synopsis = new Scene(root3);
		GameStarter.primaryStage.setScene(Synopsis);
		Synopsis.getRoot().requestFocus();
		GameStarter.primaryStage.show();
	}

	public void UnHightlight(ImageView a) {
		Image img = new Image("file:src/MenuButton/menuButton%20(16).png");
		a.setImage(img);
		a.setScaleX(1);
		a.setScaleY(1);
	}

	public void Hightlight(ImageView a) {
		Image img = new Image("file:src/MenuButton/menuButton%20(17).png");
		a.setImage(img);
		a.setScaleX(1.1);
		a.setScaleY(1.1);
	}

	public void HiStart() {

		Hightlight(start);

	}

	public void HiQuit() {
		Hightlight(quit);
	}

	public void HiSynopisis() {
		Hightlight(synopisis);
	}

	public void UnStart() {

		UnHightlight(start);

	}

	public void UnQuit() {
		UnHightlight(quit);
	}

	public void UnSynopisis() {
		UnHightlight(synopisis);
	}

	public void ExitPressed() {
		GameStarter.primaryStage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Timeline timeline = new Timeline();

		Image b2 = new Image("file:src/Opening/opening%20(2).png");
		Image b3 = new Image("file:src/Opening/opening%20(3).png");
		Image b4 = new Image("file:src/Opening/opening%20(4).png");
		Image b5 = new Image("file:src/Opening/opening%20(5).png");
		Image b6 = new Image("file:src/Opening/opening%20(6).png");
		Image b7 = new Image("file:src/Opening/opening%20(7).png");
		Image b8 = new Image("file:src/Opening/opening%20(8).png");
		Image b9 = new Image("file:src/Opening/opening%20(9).png");
		Image a2 = new Image("file:src/Opening/bouble%20(2).png");
		Image a3 = new Image("file:src/Opening/bouble%20(3).png");
		Image a4 = new Image("file:src/Opening/bouble%20(4).png");
		Image a5 = new Image("file:src/Opening/bouble%20(5).png");
		Image a6 = new Image("file:src/Opening/bouble%20(6).png");
		Image a7 = new Image("file:src/Opening/bouble%20(7).png");
		Image a8 = new Image("file:src/Opening/bouble%20(8).png");
		KeyFrame p2 = new KeyFrame(Duration.millis(200), (e) -> {

			beer.setImage(b2);
		});
		KeyFrame p3 = new KeyFrame(Duration.millis(400), (e) -> {

			beer.setImage(b3);
		});
		KeyFrame p4 = new KeyFrame(Duration.millis(600), (e) -> {

			beer.setImage(b4);
		});
		KeyFrame p5 = new KeyFrame(Duration.millis(800), (e) -> {

			beer.setImage(b5);
		});
		KeyFrame p6 = new KeyFrame(Duration.millis(1000), (e) -> {

			beer.setImage(b6);
		});
		KeyFrame p7 = new KeyFrame(Duration.millis(1200), (e) -> {

			beer.setImage(b7);
		});
		KeyFrame p8 = new KeyFrame(Duration.millis(1400), (e) -> {

			beer.setImage(b8);
		});
		KeyFrame p9 = new KeyFrame(Duration.millis(1600), (e) -> {

			beer.setImage(b9);

		});
		KeyFrame p10 = new KeyFrame(Duration.millis(1800), (e) -> {

			bouble.setVisible(true);

		});
		KeyFrame p11 = new KeyFrame(Duration.millis(2000), (e) -> {

			bouble.setImage(a2);

		});
		KeyFrame p12 = new KeyFrame(Duration.millis(2200), (e) -> {

			bouble.setImage(a3);

		});
		KeyFrame p13 = new KeyFrame(Duration.millis(2400), (e) -> {

			bouble.setImage(a4);

		});
		KeyFrame p14 = new KeyFrame(Duration.millis(2600), (e) -> {

			bouble.setImage(a5);
			beer.setVisible(false);
			title.setVisible(true);
			start.setVisible(true);
			quit.setVisible(true);
			synopisis.setVisible(true);

		});
		KeyFrame p15 = new KeyFrame(Duration.millis(2800), (e) -> {

			bouble.setImage(a6);

		});
		KeyFrame p16 = new KeyFrame(Duration.millis(3000), (e) -> {

			bouble.setImage(a7);

		});
		KeyFrame p17 = new KeyFrame(Duration.millis(3200), (e) -> {

			bouble.setImage(a8);

		});
		KeyFrame p18 = new KeyFrame(Duration.millis(3400), (e) -> {

			bouble.setVisible(false);

		});

		timeline.getKeyFrames().addAll(p2, p3, p4, p5, p6, p7, p8, p9, p10, p11,
				p12, p13, p14,p15,p16,p17,p18);
		timeline.play();

	}

}
