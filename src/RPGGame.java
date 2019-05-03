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

	//Not used, used to change directions
	static final int UP = 0;
	static final int RIGHT = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	int dir = -1;
	
	//x and y coordinates for selector
	int x = 0;
	int y = 0;
	
	//Tile sizes
	int xSize;
	int ySize;
	
	//tile board object
	Board b;
	
	//health elements for battles
	int pHealth;
	int eHealth;
	
	//Character objects
	Creature alice;
	Creature enemy;
	
	//Shows what gamestate we're in
	int gameState = 1;
	
	//tool to select with space
	boolean select=false;
	boolean move=false;
	boolean moveable=true;
	
	Stack moves = new Stack();
	
	//Reads all images to be used
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
	

	//This is the what the board reads to make it
	int[][] arr = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,}
	};

	//main
	public static void main(String[] args) {
		RPGGame g = new RPGGame();
		g.init();
		g.run();
		System.exit(0);
	}

	//updates the game
	void update() {
		//IAN Trying to keep it from going off the screen here
		/*if(select)
		{
			
		}*/
		
		{
			//All of these only move if alice is selected
			if (input.isKeyDown(KeyEvent.VK_RIGHT)&&x<arr.length-1) {
				x++;
				if(move)
					alice.setX(x*xSize);
			}
			if (input.isKeyDown(KeyEvent.VK_LEFT)&&x>0) {
				x--;
				if(move)
					alice.setX(x*xSize);
			}
			if (input.isKeyDown(KeyEvent.VK_DOWN)&&y<arr.length-1) {
				y++;
				if(move)
					alice.setY(y*ySize);
			}
			if (input.isKeyDown(KeyEvent.VK_UP)&&y>0) {
				y--;
				if(move)
					alice.setY(y*ySize);
			}
		
			//selecting alice by pressing space, need to make menu
			if(input.isKeyDown(KeyEvent.VK_SPACE) && x*xSize==alice.getX()&&y*ySize==alice.getY())
			{
				alice.setSelect(true);
			}
			
			if(alice.isSelect())
			{
				if(input.isKeyDown(KeyEvent.VK_SPACE))
				{
					alice.setX(x*xSize);
					alice.setY(y*ySize);
				}
			}
			
			/*else if(!input.isKeyDown(KeyEvent.VK_SPACE))
			{
				select=false;
			}*/
		}
		
	}
	
	//Plugging values into variables
	void init()
	{
		windowWidth = 1000;
		windowHeight = 1000;
		xSize = windowWidth/arr.length;
		ySize = windowHeight/arr[0].length;
		b = new Board(arr,windowWidth,windowHeight);
		alice = new Creature(a.getImage(),x,y,xSize,ySize,100,15,4,43);
		enemy = new Creature(s.getImage(),3*xSize,3*ySize,xSize,ySize,100,7,2,35);
		
	}
	
	//Battle handler
	void battle(Creature a, Creature b)
	{
		pHealth=a.getHp();
		eHealth=b.getHp();
		eHealth=eHealth-a.getAtk();
		pHealth=pHealth-b.getAtk();
		y--;
		a.setY(y*ySize);
		gameState=1;
	}

	//draws everything to the screen
	void draw(Graphics g) {
		g = (Graphics2D) g;
		
		//Draws the box
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, windowWidth, windowHeight);
		
		
		if(gameState==1)
		{
			//Draws the board
			b.draw(g);

			//Draws where she can move with blue squares
			if(alice.isSelect())
			{
				for(int i=1;i<4;i++)
				{
						g.drawImage(water.getImage(),(alice.getX()-i)*xSize,alice.getY()*ySize,xSize,ySize,null);
						g.drawImage(water.getImage(),(alice.getX()+i)*xSize,alice.getY()*ySize,xSize,ySize,null);
						g.drawImage(water.getImage(),alice.getX()*xSize,(alice.getY()-i)*ySize,xSize,ySize,null);
						g.drawImage(water.getImage(),alice.getX()*xSize,(alice.getY()+i)*ySize,xSize,ySize,null);
				}
			}
			//draws the cursor, alice, and skeleton
			g.drawImage(hand.getImage(),x*xSize,y*ySize,xSize/2,ySize/2,null);
			alice.draw(g);
			enemy.draw(g);
		}
		
		//gamestate for battles once implemented
		else if(gameState==2)
		{
			int p1x=200;
			int p1y=500;
			int p2x=500;
			int p2y=200;
			int size = alice.getWidth()*5;
			
			
			g.drawImage(grass.getImage(),0,0,windowWidth,windowHeight,null);
			g.drawImage(alice.getImage(),p1x,p1y,size,size,null);
			g.drawImage(enemy.getImage(),p2x,p2y,size,size,null);
			g.drawImage(startHealth.getImage(),p1x,p1y,20,20,null);
			g.drawImage(middleHealth.getImage(),p1x+20,p1y,20,20,null);
			//g.drawImage(middleHealth.getImage(),p1x,p1y,20,20,null);
			g.drawImage(endHealth.getImage(),p1x+size,p1y,20,20,null);
			g.drawImage(butn.getImage(),100,400,400,100,null);
		}
		
		//game over gamestate
		else if(gameState==3)
		{
			g.drawImage(grass.getImage(),0,0,1200,1200,null);
		}

	}

}