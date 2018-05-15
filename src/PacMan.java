
import java.awt.Graphics;

public class PacMan extends GameObject {

	public PacMan(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	void update() {
		super.update();
		y += 2;
		if (this.y > 800) {
			this.isAlive = false;
		}

	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.alienImg, x, y, width, height, null);
	}
}
