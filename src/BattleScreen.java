import java.awt.Graphics;

public class BattleScreen {
	Creature a;
	Creature b;
	ImageReader startHealth = new ImageReader("barGreen_horizontalLeft.png");
	ImageReader endHealth = new ImageReader("barGreen_horizontalRight.png");
	ImageReader middleHealth = new ImageReader("barGreen_horizontalMid.png");
	ImageReader startDam = new ImageReader("barRed_horizontalLeft.png");
	ImageReader midDam = new ImageReader("barRed_horizontalLeft.png");
	ImageReader endDam = new ImageReader("barRed_horizontalLeft.png");

	public BattleScreen(Creature one, Creature two) {
		a = one;
		b = two;
	}

	public void draw(Graphics g) {

	}

}
