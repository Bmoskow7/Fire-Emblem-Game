
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * Backbone for your games to run on
 */
public class GameEngine extends JFrame {
	boolean isRunning = true;
	int fps = 30;
	static int windowWidth = 500;
	static int windowHeight = 500;

	BufferedImage backBuffer;
	Insets insets;
	InputHandler input;
	MouseHandler mouse;

	/**
	 * This method starts the game and runs it in a loop
	 */
	public void run() {
		initialize();

		while (isRunning) {
			long time = System.currentTimeMillis();

			update();

			Graphics g = getGraphics();
			draw(backBuffer.getGraphics());
			g.drawImage(backBuffer, insets.left, insets.top, this);

			// delay for each frame - time it took for one frame
			time = (1000 / fps) - (System.currentTimeMillis() - time);

			if (time > 0) {
				try {
					Thread.sleep(time);
				} catch (Exception e) {
				}
			}
		}
		setVisible(false);
	}

	/**
	 * This method will set up everything you need for the game to run
	 */
	void initialize() {
		setTitle("Game Engine");
		setSize(windowWidth, windowHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		insets = getInsets();
		setSize(insets.left + windowWidth + insets.right, insets.top + windowHeight + insets.bottom);

		backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
		input = new InputHandler(this);
		mouse = new MouseHandler(this);
	}

	/**
	 * This method will check for input, move things around and check for win
	 * conditions, etc
	 */
	void update() {

	}

	/**
	 * This method will draw everything
	 */
	void draw(Graphics g) {

	}

}