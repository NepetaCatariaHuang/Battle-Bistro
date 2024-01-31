package Mygame;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Game implements Initializable, EventHandler<KeyEvent> {

	@FXML
	Pane over;
	@FXML
	Pane pane;
	@FXML
	Button out;
	@FXML
	AnchorPane map;
	@FXML
	AnchorPane Russia;
	@FXML
	Text winner;
	@FXML
	Text P1Score;
	@FXML
	Text P2Score;
	@FXML
	ImageView red;
	@FXML
	ImageView orange;
	@FXML
	ImageView yellow;
	@FXML
	ImageView green;
	@FXML
	ImageView blue;
	@FXML
	ImageView lightblue;
	@FXML
	ImageView purple;
	@FXML
	ImageView russianUp;
	@FXML
	ImageView russianDown;
	@FXML
	ImageView russianLeft;
	@FXML
	ImageView russianRight;
	ImageView russian;
	
	
///////////////////////////////////////////////

	
	int center = 0;
	int power = 0;
	int direction = 0;
	int remove = 0;
	boolean gameover = true;
	boolean spinable = true;
	boolean p1 = true;
	
	
///////////////////////////////////////////////	
	
	
	boolean[][] wall = new boolean[24][14];
	ImageView[] moveset, arr = new ImageView[4];
	ImageView[][] store = new ImageView[24][14];	
	Timeline fps = new Timeline(new KeyFrame(Duration.millis(1000 / 2), (e) -> {
		moveDown();
	}));
	Timeline unlock = new Timeline(new KeyFrame(Duration.millis(1), (e) -> {
		gameover = false;
	}));

	
////////////////////////////////////////////////
	@Override
	public void handle(KeyEvent e) {
		if (!gameover) {
			if (e.getCode() == KeyCode.I || e.getCode() == KeyCode.X) {

				if (spinable)
					Spin();
			}

			if (e.getCode() == KeyCode.K) {
				moveDown();
			}

			if (e.getCode() == KeyCode.L) {
				boolean can = true;
				for (int i = 0; i < 4; i++) {
					if (wall[toWallY(moveset[i])][toWallX(moveset[i]) + 1]) {
						can = false;
						break;
					}
				}
				if (can)
					moveRight();
			}

			if (e.getCode() == KeyCode.J) {
				boolean can = true;
				for (int i = 0; i < 4; i++) {
					if (wall[toWallY(moveset[i])][toWallX(moveset[i]) - 1]) {
						can = false;
						break;
					}
				}
				if (can)
					moveLeft();
			}

			if (e.getCode() == KeyCode.SPACE) {

				while (true) {
					moveDown();

					if (isBottom(moveset))
						break;
				}
				moveDown();

			}

			if (e.getCode() == KeyCode.UP) {
				if (direction == 0) {
					if (russian.getLayoutY() > 0 && wall[toWallY(russian) - 1][toWallX(russian)])
						russian.setLayoutY(russian.getLayoutY() - blockY(1));
				} else {
					russian.setImage(russianUp.getImage());
					direction = 0;
				}

			}
			if (e.getCode() == KeyCode.DOWN) {
				if (direction == 1) {
					if (russian.getLayoutY() < blockY(20) && wall[toWallY(russian) + 1][toWallX(russian)])
						russian.setLayoutY(russian.getLayoutY() + blockY(1));
				} else {
					russian.setImage(russianDown.getImage());
					direction = 1;
				}

			}
			if (e.getCode() == KeyCode.LEFT) {
				if (direction == 2) {
					if (russian.getLayoutX() > 0 && wall[toWallY(russian)][toWallX(russian) - 1])
						russian.setLayoutX(russian.getLayoutX() - blockX(1));
				} else {
					russian.setImage(russianLeft.getImage());
					direction = 2;
				}

			}
			if (e.getCode() == KeyCode.RIGHT) {
				if (direction == 3) {
					if (russian.getLayoutX() < blockX(9) && wall[toWallY(russian)][toWallX(russian) + 1])
						russian.setLayoutX(russian.getLayoutX() + blockX(1));
				} else {
					russian.setImage(russianRight.getImage());
					direction = 3;
				}

			}
			if (e.getCode() == KeyCode.NUMPAD3) {
				push();
			}

		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		russian = new ImageView(russianUp.getImage());
		russian.setFitWidth(47);
		russian.setFitHeight(41);
		russian.setLayoutX(blockX(4));
		russian.setLayoutY(blockY(20));

		for (int i = 0; i < wall.length; i++) {
			for (int j = 0; j < wall[1].length; j++) {
				wall[i][j] = true;
			}
		}

		for (int i = 0; i < wall.length - 2; i++) {
			for (int j = 2; j < wall[1].length - 2; j++) {
				wall[i][j] = false;
			}
		}

		Russia.getChildren().add(russian);

		ranShape();
		fps.setCycleCount(Timeline.INDEFINITE);
		fps.setDelay(Duration.seconds(3));
		fps.play();
		unlock.setCycleCount(1);
		unlock.setDelay(Duration.seconds(3));
		unlock.play();

	}

	public void moveDown() {
		if (isBottom(moveset)) {
			setWall(moveset);
			eliminate();
			GameOver();
			if (!p1 && remove == 0) {
				for (int i = 0; i < 4; i++) {
					map.getChildren().remove(moveset[i]);
					remove++;
				}
			}
			ranShape();
		} else {
			for (int i = 0; i < moveset.length; i++) {
				moveset[i].setLayoutY(moveset[i].getLayoutY() + blockY(1));
			}
		}
	}

	public void moveRight() {
		for (int i = 0; i < moveset.length; i++) {
			moveset[i].setLayoutX(moveset[i].getLayoutX() + blockX(1));
		}

	}

	public void moveLeft() {
		for (int i = 0; i < moveset.length; i++) {
			moveset[i].setLayoutX(moveset[i].getLayoutX() - blockX(1));
		}

	}

	public void eliminate() {
		int counter = 0;
		for (int i = wall.length - 3; i > 1; i--) {
			for (int j = 2; j < wall[1].length - 2; j++) {
				if (wall[i][j])
					counter++;

			}
			if (counter == 10) {
				counter = 0;
				for (int j = 2; j < wall[1].length - 2; j++) {
					wall[i][j] = false;
					map.getChildren().remove(store[i][j]);
					store[i][j] = null;
				}
				die();
				fill(i);
				i++;
				if (p1)
					P1Score.setText(String.valueOf((Integer.valueOf(P1Score.getText()) + 100)));
				else
					P2Score.setText(String.valueOf((Integer.valueOf(P2Score.getText()) + 100)));
			} else
				counter = 0;
		}

	}

	public void fill(int a) {
		for (int i = a; i > 1; i--) {
			for (int j = 2; j < wall[1].length - 2; j++) {
				wall[i][j] = wall[i - 1][j];
				store[i][j] = store[i - 1][j];
				if (!(store[i][j] == null)) {
					store[i][j].setLayoutY(store[i][j].getLayoutY() + blockY(1));
				}

			}
		}

		die();
	}

	public void Spin() {
		double[] ax = new double[4];
		double[] ay = new double[4];
		double cenX = moveset[center].getLayoutX(), cenY = moveset[center].getLayoutY(), vecX, vecY;
		boolean can = true;

		for (int i = 0; i < 4; i++) {
			vecX = (moveset[i].getLayoutX() - cenX) / blockX(1);
			vecY = (moveset[i].getLayoutY() - cenY) / blockY(1);
			ax[i] = cenX - blockX(1) * vecY;
			ay[i] = cenY + blockY(1) * vecX;

		}
		for (int i = 0; i < 4; i++) {
			int x = (int) ax[i] / blockX(1) + 2;
			int y = (int) ay[i] / blockY(1) + 2;

			if (x == 0) {
				x++;
				for (int j = 0; j < 4; j++) {
					ax[j] += blockX(1);
				}

			}

			if (x == 1) {
				x++;
				for (int j = 0; j < 4; j++) {
					ax[j] += blockX(1);
				}
			}

			if (x == 13) {
				x--;
				for (int j = 0; j < 4; j++) {
					ax[j] -= blockX(1);
				}
			}

			if (x == 12) {
				x--;
				for (int j = 0; j < 4; j++) {
					ax[j] -= blockX(1);
				}
			}

			if (wall[y][x]) {
				can = false;
				break;
			}

		}
		if (can) {
			for (int i = 0; i < 4; i++) {
				moveset[i].setLayoutX(ax[i]);
				moveset[i].setLayoutY(ay[i]);
			}

		}

	}

	public void GameOver() {
		for (int i = 2; i < 12; i++) {
			if (wall[2][i]) {
				fps.stop();
				if (p1) {
					p1 = false;
					String a = P1Score.getText();
					yesPressed();
					P1Score.setText(a);

				} else {
					if (Integer.valueOf(P2Score.getText()) > Integer.valueOf(P1Score.getText()))
						winner.setText("P2Àò±o³Ó§Q");
					over.setVisible(true);
					gameover = true;
					P2Score.setText(String.valueOf((Integer.valueOf(P2Score.getText()) - 10)));
					break;

				}

			}
		}
	}

	public boolean isBottom(ImageView[] a) {
		int x, y;

		for (int i = 0; i < 4; i++) {
			x = toWallX(a[i]);
			y = toWallY(a[i]);
			if (wall[y + 1][x])
				return true;
		}
		return false;

	}

	public void ranShape() {
		if (p1)
			P1Score.setText(String.valueOf((Integer.valueOf(P1Score.getText()) + 10)));
		else
			P2Score.setText(String.valueOf((Integer.valueOf(P2Score.getText()) + 10)));
		Random random = new Random();
		int index = random.nextInt(7);
		switch (index) {
		case 0: {
			L();
			break;
		}
		case 1: {
			J();
			break;
		}
		case 2: {
			O();
			break;
		}
		case 3: {
			I();
			break;
		}
		case 4: {
			Z();
			break;
		}
		case 5: {
			S();
			break;
		}
		case 6: {
			T();
			break;
		}
		}
	}

	public void setWall(ImageView[] a) {
		for (int i = 0; i < 4; i++) {
			wall[toWallY(a[i])][toWallX(a[i])] = true;
			store[toWallY(a[i])][toWallX(a[i])] = a[i];
		}
	}

	public int toWallX(ImageView a) {
		int x = (int) a.getLayoutX() / blockX(1) + 2;
		return x;
	}

	public int toWallY(ImageView a) {
		int x = (int) a.getLayoutY() / blockY(1) + 2;
		return x;
	}

	public void L() {
		spinable = true;
		for (int i = 0; i < 4; i++) {
			arr[i] = new ImageView(yellow.getImage());
			arr[i].setFitWidth(47);
			arr[i].setFitHeight(60);
		}
		arr[0].setLayoutX(blockX(3));
		arr[0].setLayoutY(blockY(0));
		arr[1].setLayoutX(blockX(4));
		arr[1].setLayoutY(blockY(0));
		arr[2].setLayoutX(blockX(5));
		arr[2].setLayoutY(blockY(0));
		arr[3].setLayoutX(blockX(3));
		arr[3].setLayoutY(blockY(1));

		center = 1;
		for (int i = 0; i < 4; i++) {

			map.getChildren().add(arr[i]);

		}
		moveset = arr;

	}

	public void J() {
		spinable = true;

		for (int i = 0; i < 4; i++) {
			arr[i] = new ImageView(red.getImage());
			arr[i].setFitWidth(47);
			arr[i].setFitHeight(60);
		}
		arr[0].setLayoutX(blockX(3));
		arr[0].setLayoutY(blockY(0));
		arr[1].setLayoutX(blockX(4));
		arr[1].setLayoutY(blockY(0));
		arr[2].setLayoutX(blockX(5));
		arr[2].setLayoutY(blockY(0));
		arr[3].setLayoutX(blockX(5));
		arr[3].setLayoutY(blockY(1));

		center = 1;
		for (int i = 0; i < 4; i++) {

			map.getChildren().add(arr[i]);

		}
		moveset = arr;

	}

	public void O() {
		spinable = false;

		for (int i = 0; i < 4; i++) {
			arr[i] = new ImageView(purple.getImage());
			arr[i].setFitWidth(47);
			arr[i].setFitHeight(60);
		}
		arr[0].setLayoutX(blockX(4));
		arr[0].setLayoutY(blockY(0));
		arr[1].setLayoutX(blockX(5));
		arr[1].setLayoutY(blockY(0));
		arr[2].setLayoutX(blockX(4));
		arr[2].setLayoutY(blockY(1));
		arr[3].setLayoutX(blockX(5));
		arr[3].setLayoutY(blockY(1));

		for (int i = 0; i < 4; i++) {

			map.getChildren().add(arr[i]);

		}
		moveset = arr;

	}

	public void I() {
		spinable = true;

		for (int i = 0; i < 4; i++) {
			arr[i] = new ImageView(orange.getImage());
			arr[i].setFitWidth(47);
			arr[i].setFitHeight(60);
		}
		arr[0].setLayoutX(blockX(3));
		arr[0].setLayoutY(blockY(0));
		arr[1].setLayoutX(blockX(4));
		arr[1].setLayoutY(blockY(0));
		arr[2].setLayoutX(blockX(5));
		arr[2].setLayoutY(blockY(0));
		arr[3].setLayoutX(blockX(6));
		arr[3].setLayoutY(blockY(0));

		center = 2;
		for (int i = 0; i < 4; i++) {

			map.getChildren().add(arr[i]);

		}
		moveset = arr;

	}

	public void Z() {
		spinable = true;

		for (int i = 0; i < 4; i++) {
			arr[i] = new ImageView(lightblue.getImage());
			arr[i].setFitWidth(47);
			arr[i].setFitHeight(60);
		}

		arr[0].setLayoutX(blockX(3));
		arr[0].setLayoutY(blockY(0));
		arr[1].setLayoutX(blockX(4));
		arr[1].setLayoutY(blockY(0));
		arr[2].setLayoutX(blockX(4));
		arr[2].setLayoutY(blockY(1));
		arr[3].setLayoutX(blockX(5));
		arr[3].setLayoutY(blockY(1));

		center = 2;
		for (int i = 0; i < 4; i++) {

			map.getChildren().add(arr[i]);

		}
		moveset = arr;

	}

	public void S() {
		spinable = true;

		for (int i = 0; i < 4; i++) {
			arr[i] = new ImageView(green.getImage());
			arr[i].setFitWidth(47);
			arr[i].setFitHeight(60);
		}

		arr[0].setLayoutX(blockX(4));
		arr[0].setLayoutY(blockY(0));
		arr[1].setLayoutX(blockX(5));
		arr[1].setLayoutY(blockY(0));
		arr[2].setLayoutX(blockX(3));
		arr[2].setLayoutY(blockY(1));
		arr[3].setLayoutX(blockX(4));
		arr[3].setLayoutY(blockY(1));

		center = 3;
		for (int i = 0; i < 4; i++) {

			map.getChildren().add(arr[i]);

		}
		moveset = arr;

	}

	public void T() {
		spinable = true;

		for (int i = 0; i < 4; i++) {
			arr[i] = new ImageView(blue.getImage());
			arr[i].setFitWidth(47);
			arr[i].setFitHeight(60);
		}
		arr[0].setLayoutX(blockX(3));
		arr[0].setLayoutY(blockY(0));
		arr[1].setLayoutX(blockX(4));
		arr[1].setLayoutY(blockY(0));
		arr[2].setLayoutX(blockX(5));
		arr[2].setLayoutY(blockY(0));
		arr[3].setLayoutX(blockX(4));
		arr[3].setLayoutY(blockY(1));

		center = 1;
		for (int i = 0; i < 4; i++) {

			map.getChildren().add(arr[i]);

		}
		moveset = arr;

	}

	public int blockX(int a) {
		return a * 47;
	}

	public int blockY(int a) {
		return a * 41;
	}

	public void push() {
		int x = toWallX(russian), y = toWallY(russian);
		switch (direction) {
		case 1: {
			int i = 2;
			while (wall[y + i][x] == false && wall[y + i - 1][x] == true) {

				store[y + i][x] = store[y + i - 1][x];
				store[y + i - 1][x] = null;
				wall[y + i][x] = true;
				wall[y + i - 1][x] = false;
				moveCubeY(store[y + i][x], 1, blockY(1), false);
				i++;

			}
			break;
		}
		case 2: {
			if (wall[y][x - 2] == false && wall[y][x - 1] == true) {

				store[y][x - 2] = store[y][x - 1];
				store[y][x - 1] = null;
				wall[y][x - 2] = true;
				wall[y][x - 1] = false;
				moveCubeX(store[y][x - 2], -1, blockX(1));

				int i = 0;
				while (wall[y + 1 + i][x - 2] == false) {

					store[y + 1 + i][x - 2] = store[y + i][x - 2];
					store[y + i][x - 2] = null;
					wall[y + 1 + i][x - 2] = true;
					wall[y + i][x - 2] = false;
					moveCubeY(store[y + 1 + i][x - 2], 1, blockY(1), true);
					i++;
				}

			}

			break;
		}
		case 3: {
			if (wall[y][x + 2] == false && wall[y][x + 1] == true) {

				store[y][x + 2] = store[y][x + 1];
				store[y][x + 1] = null;
				wall[y][x + 2] = true;
				wall[y][x + 1] = false;
				moveCubeX(store[y][x + 2], 1, blockX(1));

				int i = 0;
				while (wall[y + 1 + i][x + 2] == false) {

					store[y + 1 + i][x + 2] = store[y + i][x + 2];
					store[y + i][x + 2] = null;
					wall[y + 1 + i][x + 2] = true;
					wall[y + i][x + 2] = false;
					moveCubeY(store[y + 1 + i][x + 2], 1, blockY(1), true);
					i++;
				}
			}
		}
			eliminate();
			break;
		}
	}

	public void die() {
		int x = toWallX(russian), y = toWallY(russian);
		if (!wall[y][x]) {
			Russia.getChildren().remove(russian);
			russian.setLayoutX(blockX(4));
			russian.setLayoutY(blockY(20));
			if (p1)
				P1Score.setText(String.valueOf((Integer.valueOf(P1Score.getText()) + 10000)));
			else
				P2Score.setText(String.valueOf((Integer.valueOf(P2Score.getText()) + 10000)));
		}

	}

	public void moveCubeX(ImageView a, double b, double c) {

		Timeline X = new Timeline(new KeyFrame(Duration.millis(300 / (int) c), (e) -> {
			a.setLayoutX(a.getLayoutX() + b);
		}));
		X.setCycleCount((int) c);
		X.play();

	}

	public void moveCubeY(ImageView a, double b, double c, boolean d) {
		Timeline Y = new Timeline(new KeyFrame(Duration.millis(300 / (int) c), (e) -> {
			a.setLayoutY(a.getLayoutY() + b);
		}));
		if (d)
			Y.setDelay(Duration.millis(320));
		Y.setCycleCount((int) c);
		Y.play();

	}

	public void outPressed() {
		Mygame.GameStarter.primaryStage.close();
	}

	public void restartPressed() {

		fps.stop();
		gameover = true;

	}

	public void yesPressed() {
		map.getChildren().removeAll(map.getChildren());
		Russia.getChildren().remove(russian);
		P1Score.setText("0");
		gameover = true;
		initialize(null, null);
		HomeScreenController.Game.getRoot().requestFocus();
		over.setVisible(false);
		P2Score.setText("0");
	}

}
