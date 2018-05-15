
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean isAlive;
	Rectangle collisionBox;

	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collisionBox = new Rectangle(x, y, width, height);
		this.isAlive = true;
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);

	}

	void draw(Graphics g) {

	}
}
