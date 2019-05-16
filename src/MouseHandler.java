import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
	public MouseHandler(Component c) {
		c.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		System.out.print(e.getX());
		System.out.print(e.getY());
	}

	public int mouseX(MouseEvent e) {
		return e.getX();
	}

	public int mouseY(MouseEvent e) {
		return e.getY();
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
