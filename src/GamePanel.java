
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont;
	Font stringFont;
	Bar bar;
	Ball ball;
	PacMan pac;
	ObjectManager obj;
	int xSpeedBall;
	int ySpeedBall;
	double pacSpeed;
	double timePassed;
	public static BufferedImage alienImg;
	public static BufferedImage rocketImg;
	public static BufferedImage bulletImg;

	public GamePanel() {
		timer = new Timer(1000 / 30, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		stringFont = new Font("Arial", Font.PLAIN, 24);
		bar = new Bar(25, 300, 25, 150);
		ball = new Ball(50, 350, 50, 50);
		pac = new PacMan(380, 325, 60, 60);
		obj = new ObjectManager(bar);
		xSpeedBall = 4;
		ySpeedBall = 6;
		timePassed = 0;
		pacSpeed = 2.5;
		try {

			alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));

			rocketImg = ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));

			bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	void startGame() {
		timer.start();
	}

	void updateMenuState() {
		obj.reset();
	}

	void updateGameState() {
		pacSpeed += 1 / 900;
		timePassed += 1;
		if (timePassed % 130 == 2) {
			playSound("Pac-Man Waka Waka Seamless Loop.wav");
		}
		obj.update();
		if (obj.bar.isAlive == false) {
			currentState = END_STATE;
		}
		double xChange = 2.5 * (ball.x - pac.x - 15) / (Math
				.sqrt((pac.x - ball.x + 15) * (pac.x - ball.x + 15) + (pac.y - ball.y + 15) * (pac.y - ball.y + 15)));
		double yChange = 2.5 * (ball.y - pac.y - 15) / (Math
				.sqrt((pac.x - ball.x + 15) * (pac.x - ball.x + 15) + (pac.y - ball.y + 15) * (pac.y - ball.y + 15)));
		pac.x += Math.round(xChange);
		pac.y += Math.round(yChange);
		if (ball.x >= 960) {
			xSpeedBall = -xSpeedBall;
		}
		if (ball.y <= 0) {
			ySpeedBall = -ySpeedBall;
		}
		if (ball.y >= 725) {
			ySpeedBall = -ySpeedBall;
		}
		ball.x += xSpeedBall;
		ball.y += ySpeedBall;
		if (ball.x == 50) {
			if ((ball.y - bar.gety() - 50) * (ball.y - bar.gety() - 50) <= 10000) {
				ySpeedBall = (int) Math.round(7 * (ball.y - bar.gety() - 50)
						/ Math.sqrt(1600 + (ball.y - bar.gety() - 50) * (ball.y - bar.gety() - 50)));
				xSpeedBall = (int) Math.round(Math.sqrt(49 - ySpeedBall * ySpeedBall));
				playSound("Pong Sound Effect.wav");
			}
		}
		if ((ball.x - pac.x - 15) * (ball.x - pac.x - 15) + (ball.y - pac.y - 15) * (ball.y - pac.y - 15) <= 2500) {
			currentState = END_STATE;
		}
		if (ball.x <= 0) {
			currentState = END_STATE;
		}
	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
		g.setFont(titleFont);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, PacPong.width, PacPong.height);
		g.setColor(Color.YELLOW);
		g.drawString("PacPong", 400, 200);
		g.setFont(stringFont);
		g.drawString("Like Pong except", 395, 330);
		g.drawString("Keep ball from pacman", 365, 390);
		g.drawString("Up & Down arrows control bar", 335, 450);
		g.drawString("Press ENTER to start", 375, 500);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, PacPong.width, PacPong.height);
		obj.draw(g);
		ball.draw(g);
		pac.draw(g);
		g.setColor(Color.YELLOW);
		g.setFont(stringFont);
		g.drawString("Time Passed: " + (int) Math.round(timePassed / 30), 50, 50);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, PacPong.width, PacPong.height);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("Game Over", 390, 200);
		g.setFont(stringFont);
		g.drawString("You survived for " + (int) Math.round(timePassed / 30) + " seconds", 380, 500);
		g.drawString("Press ENTER to restart", 380, 650);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
		repaint();
	}

	private void playSound(String fileName) {
		AudioClip sound = JApplet.newAudioClip(getClass().getResource(fileName));
		sound.play();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END_STATE) {
				currentState = MENU_STATE;

			} else {
				currentState++;
				if (currentState == GAME_STATE) {

					bar.x = 25;
					bar.y = 300;
					ball.x = 50;
					ball.y = 350;
					pac.x = 380;
					pac.y = 325;
					timePassed = 0;
					xSpeedBall = 5;
					ySpeedBall = 5;
				}
			}
			System.out.println(currentState);

		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (currentState == GAME_STATE) {
				bar.y += -bar.speed;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (currentState == GAME_STATE) {
				bar.y += bar.speed;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
