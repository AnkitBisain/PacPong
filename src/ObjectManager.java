
import java.awt.Graphics;

public class ObjectManager {
	Bar bar;

	int score;

	public ObjectManager(Bar ship) {
		this.bar = ship;
		score = 0;
	}

	void update() {
		bar.update();
	}

	void draw(Graphics g) {
		bar.draw(g);

	}

	public int getScore() {
		return score;
	}

	void reset() {

	}
}