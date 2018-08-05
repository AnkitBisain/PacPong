
import java.awt.Graphics;

public class Ball extends GameObject {
	int speed;

	public Ball(int x, int y, int width, int height) {

		super(x, y, width, height);
		speed = 3;
	}

	void update() {
		super.update();
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.bulletImg, x, y, width, height, null);
	}

}
