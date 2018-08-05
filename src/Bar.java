
import java.awt.Graphics;

public class Bar extends GameObject {
	int speed;

	public Bar(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 2;
		// TODO Auto-generated constructor stub
	}

	void update() {
		super.update();
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.rocketImg, x, y, width, height, null);
	}

	int gety() {
		return y;
	}

}
