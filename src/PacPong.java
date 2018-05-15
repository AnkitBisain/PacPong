
import javax.swing.JFrame;

public class PacPong {

	JFrame frame;
	GamePanel panel;
	final static int width = 1000;
	final static int height = 800;

	public PacPong() {
		panel = new GamePanel();
		frame = new JFrame();
		setup();
	}

	void setup() {
		frame.addKeyListener(panel);
		frame.add(panel);
		frame.pack();
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.startGame();
	}

	public static void main(String[] args) {
		PacPong game = new PacPong();
	}

}
