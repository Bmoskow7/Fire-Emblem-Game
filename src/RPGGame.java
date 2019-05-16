import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class RPGGame extends GameEngine {

	// Represents gamestates
	static final int MAP_STATE = 1;
	static final int BATTLE_STATE = 2;
	static final int GAMEOVER_STATE = 3;

	// Not used, used to change directions
	static final int UP = 0;
	static final int RIGHT = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	int dir = -1;
	int moveDis = 4;

	// x and y coordinates for selector
	int x = 0;
	int y = 0;

	// Camera moves
	boolean lMove = true;
	boolean rMove = true;
	boolean uMove = true;
	boolean dMove = true;

	// Tile sizes
	int xSize;
	int ySize;

	// tile board object
	int xOff = 0;
	int yOff = 0;
	Board b;

	// health elements for battles
	int pHealth;
	int eHealth;

	// Character objects
	Creature[] creatures;
	Creature alice;
	Creature enemy;

	// Shows what gamestate we're in
	int gameState = MAP_STATE;

	// Reads all images to be used
	ImageReader startHealth = new ImageReader("barGreen_horizontalLeft.png");
	ImageReader endHealth = new ImageReader("barGreen_horizontalRight.png");
	ImageReader middleHealth = new ImageReader("barGreen_horizontalMid.png");
	ImageReader startDam = new ImageReader("barRed_horizontalLeft.png");
	ImageReader midDam = new ImageReader("barRed_horizontalLeft.png");
	ImageReader endDam = new ImageReader("barRed_horizontalLeft.png");
	ImageReader a = new ImageReader("alice.png");
	ImageReader s = new ImageReader("skeleton.png");
	ImageReader grass = new ImageReader("grass.png");
	ImageReader water = new ImageReader("Water.png");
	ImageReader lava = new ImageReader("Lava.png");
	ImageReader butn = new ImageReader("buttonLong_blue.png");
	ImageReader hand = new ImageReader("cursorGauntlet_bronze.png");

	// This is the what the board reads to make it
	int[][] arr = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },

	};

	// main
	public static void main(String[] args) {
		RPGGame g = new RPGGame();
		g.init();
		g.run();
		System.exit(0);
	}

	// updates the game
	void update() {

		/*
		 * if(alice.isSelect()) { if(x>=alice.getX()+4) x=alice.getX()+4;
		 * if(x<=alice.getX()-4) x=alice.getX()-4; if(y>=alice.getY()+4)
		 * y=alice.getY()+4; if(y<=alice.getY()-4) y=alice.getY()-4; }
		 */
		// All of these only move if alice is selected

		if (input.isKeyDown(KeyEvent.VK_RIGHT) && x == 15 && x + xOff < arr.length - 1 && rMove) {
			xOff++;
			b.setxOffset(xOff);
			alice.setxOff(xOff * -1);
			enemy.setxOff(xOff * -1);
		}
		if (input.isKeyDown(KeyEvent.VK_DOWN) && y == 15 && y + yOff < arr.length - 1 && dMove) {
			yOff++;
			b.setyOffset(yOff);
			alice.setyOff(yOff * -1);
			enemy.setyOff(yOff * -1);
		}
		if (input.isKeyDown(KeyEvent.VK_LEFT) && x == 0 && lMove) {
			if (xOff > 0) {
				xOff--;
				b.setxOffset(xOff);
				alice.setxOff(xOff * -1);
				enemy.setxOff(xOff * -1);
			}
		}
		if (input.isKeyDown(KeyEvent.VK_UP) && y == 0 && uMove) {
			if (yOff > 0) {
				yOff--;
				b.setyOffset(yOff);
				alice.setyOff(yOff * -1);
				enemy.setyOff(yOff * -1);
			}
		}
		if (input.isKeyDown(KeyEvent.VK_RIGHT) && x < 15 && rMove) {
			x++;
		}
		if (input.isKeyDown(KeyEvent.VK_LEFT) && x > 0 && lMove) {
			x--;
		}
		if (input.isKeyDown(KeyEvent.VK_DOWN) && y < 15 && dMove) {
			y++;
		}
		if (input.isKeyDown(KeyEvent.VK_UP) && y > 0 && uMove) {
			y--;
		}

		if (alice.isSelect()) {
			rMove = x != alice.getX() + moveDis;
			dMove = y != alice.getY() + moveDis;
			lMove = x != alice.getX() - moveDis;
			uMove = y != alice.getY() - moveDis;

			move();
		}

		// selecting alice by pressing space, need to make menu
		if (input.isKeyDown(KeyEvent.VK_SPACE) && x == alice.getX() && y == alice.getY()) {
			alice.setSelect(true);
		}

	}

	// Plugging values into variables
	void init() {
		windowWidth = 1000;
		windowHeight = 1000;
		xSize = windowWidth / 16;
		ySize = windowHeight / 16;
		b = new Board(arr, windowWidth, windowHeight, xOff, yOff);
		alice = new Creature(a.getImage(), 0, 0, xSize, ySize, 100, 15, 4, 43);
		enemy = new Creature(s.getImage(), 3, 3, xSize, ySize, 100, 7, 2, 35);

	}

	void move() {
		if (input.isKeyDown(KeyEvent.VK_ENTER)) {
			alice.setX(x);
			alice.setY(y);
			alice.setSelect(false);
			if (alice.intersects(enemy)) {
				battle(alice, enemy);
			}
			dMove = true;
			uMove = true;
			lMove = true;
			rMove = true;
		}
	}

	// Battle handler
	void battle(Creature a, Creature b) {
		pHealth = a.getHp();
		eHealth = b.getHp();
		eHealth = eHealth - a.getAtk();
		pHealth = pHealth - b.getAtk();
		a.setY(y - 1);
		gameState = MAP_STATE;
		System.out.println(pHealth);
		System.out.println(eHealth);
	}

	// draws everything to the screen
	void draw(Graphics g) {
		g = (Graphics2D) g;

		// Draws the box
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, windowWidth, windowHeight);

		if (gameState == MAP_STATE) {
			// Draws the board
			b.draw(g);

			// Draws where she can move with blue squares

			// draws the cursor, alice, and skeleton
			alice.draw(g);
			enemy.draw(g);
			g.drawImage(hand.getImage(), x * xSize + (xSize / 4), y * ySize + (ySize / 4), xSize / 2, ySize / 2, null);
		}

		// gamestate for battles once implemented
		else if (gameState == BATTLE_STATE) {
			int p1x = 200;
			int p1y = 500;
			int p2x = 500;
			int p2y = 200;
			int size = alice.getWidth() * 5;

			g.drawImage(grass.getImage(), 0, 0, windowWidth, windowHeight, null);
			g.drawImage(alice.getImage(), p1x, p1y, size, size, null);
			g.drawImage(enemy.getImage(), p2x, p2y, size, size, null);
			g.drawImage(startHealth.getImage(), p1x, p1y, 20, 20, null);
			g.drawImage(middleHealth.getImage(), p1x + 20, p1y, 20, 20, null);
			// g.drawImage(middleHealth.getImage(),p1x,p1y,20,20,null);
			g.drawImage(endHealth.getImage(), p1x + size, p1y, 20, 20, null);
			g.drawImage(butn.getImage(), 100, 400, 400, 100, null);
		}

		// game over gamestate
		else if (gameState == GAMEOVER_STATE) {
			g.drawImage(grass.getImage(), 0, 0, 1200, 1200, null);
		}

	}

}