import java.awt.Component;
import java.awt.event.*;

/**
 * Makes handling input a lot simpler
 */
public class InputHandler implements KeyListener {
	private boolean keys[] = new boolean[256];

	public InputHandler(Component c) {
		c.addKeyListener(this);
	}

	public boolean isKeyDown(int keyCode) {
		if (keyCode > 0 && keyCode < 256) {
			return keys[keyCode];
		}

		return false;
	}
 
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys[e.getKeyCode()] = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys[e.getKeyCode()] = false;
		}
	}

	/**
	 * Not used
	 */
	public void keyTyped(KeyEvent e) {
	}
}