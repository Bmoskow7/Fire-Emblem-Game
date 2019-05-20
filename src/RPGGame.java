import java.awt.Color;
import java.awt.Font;
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
	
	//x and y coordinates for selector
	int x = 0;
	int y = 1;
	
	//Camera moves
	boolean lMove=true;
	boolean rMove=true;
	boolean uMove=true;
	boolean dMove=true;
	
	//Tile sizes
	int xSize;
	int ySize;
	
	//tile board object
	int xOff = 0;
	int yOff=0;
	Board b;
	
	//Character objects
	Font times = new Font("TimesRoman", Font.PLAIN, xSize/3);
	ArrayList<Creature> friends = new ArrayList<Creature>();
	ArrayList<Creature> enemies = new ArrayList<Creature>();
	Creature alice,enemy,tim;
	Creature current;
	
	//Shows what gamestate we're in
	int gameState = 1;
	
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
	ImageReader butn = new ImageReader("buttonLong_beige.png");
	ImageReader hand = new ImageReader("cursorGauntlet_bronze.png");
	ImageReader t = new ImageReader("Tim.png");
	

	//This is the what the board reads to make it
	int[][] arr = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,},
			
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
		
		
			if (input.isKeyDown(KeyEvent.VK_RIGHT)&&x==15&&x+xOff<arr.length-1&&rMove)
			{
				xOff++;
				b.setxOffset(xOff);
				alice.setxOff(xOff*-1);
				enemy.setxOff(xOff*-1);
			}
			if (input.isKeyDown(KeyEvent.VK_DOWN)&&y==15&&y+yOff<arr.length-1&&dMove)
			{
				yOff++;
				b.setyOffset(yOff);
				alice.setyOff(yOff*-1);
				enemy.setyOff(yOff*-1);
			}
			if (input.isKeyDown(KeyEvent.VK_LEFT)&&x==0&&lMove)
			{
				if(xOff>0)
				{
					xOff--;
					b.setxOffset(xOff);
					alice.setxOff(xOff*-1);
					enemy.setxOff(xOff*-1);
				}
			}
			if (input.isKeyDown(KeyEvent.VK_UP)&&y==1&&uMove)
			{
				if(yOff>0)
				{
					yOff--;
					b.setyOffset(yOff);
					alice.setyOff(yOff*-1);
					enemy.setyOff(yOff*-1);
				}
			}
			if (input.isKeyDown(KeyEvent.VK_RIGHT)&&x<15&&rMove) {
					x++;
			}
			if (input.isKeyDown(KeyEvent.VK_LEFT)&&x>0&&lMove) {
					x--;
			}
			if (input.isKeyDown(KeyEvent.VK_DOWN)&&y<15&&dMove) {
					y++;
			}
			if (input.isKeyDown(KeyEvent.VK_UP)&&y>1&&uMove) {
					y--;
			}
			int index=0;
			while(index<friends.size())
			{
				if(input.isKeyDown(KeyEvent.VK_SPACE) && x==friends.get(index).getX()&&y==friends.get(index).getY())
				{
					friends.get(index).setSelect(true);
				}
				if(friends.get(index).isSelect())
				{
					if(x==friends.get(index).getX()+4)
					{
						rMove=false;
					}
					else
					{
						rMove=true;
					}
					if(y==friends.get(index).getY()+4)
					{
						dMove=false;
					}
					else
					{
						dMove=true;
					}
					if(x==friends.get(index).getX()-4)
					{
						lMove=false;
					}
					else
					{
						lMove=true;
					}
					if(y==friends.get(index).getY()-4)
					{
						uMove=false;
					}
					else
					{
						uMove=true;
					}
					move(friends.get(index));
				}
				index++;
			}
		
	}
	
	//Plugging values into variables
	void init()
	{
		windowWidth = 1040;
		windowHeight = 1040;
		xSize = windowWidth/16;
		ySize = windowHeight/16;
		b = new Board(arr,windowWidth,windowHeight,xOff,yOff);
		alice = new Creature(a.getImage(),"Alice",0,1,xSize,ySize,100,15,4);
		enemy = new Creature(s.getImage(),"Skeleton",3,3,xSize,ySize,100,7,2);
		tim = new Creature(t.getImage(),"Tim",6,5,xSize,ySize,100,10,5);
		friends.add(alice);
		friends.add(tim);
		enemies.add(enemy);
	}
	
	void offset(Creature c)
	{
		
	}
	
	void select(Creature c)
	{
		if(input.isKeyDown(KeyEvent.VK_SPACE)&&x==c.getX()&&y==c.getY())
		{
			
		}
	}
	
	void move(Creature c)
	{
		
		if(input.isKeyDown(KeyEvent.VK_ENTER))
		{
			c.setX(x);
			c.setY(y);
			c.setSelect(false);
			dMove=true;
			uMove=true;
			lMove=true;
			rMove=true;
		}
		else if(input.isKeyDown(KeyEvent.VK_ESCAPE))
		{
			c.setSelect(false);
		}
	}
	
	//Battle handler
	void battle(Creature a, Creature b)
	{
		b.setTempHP(b.getTempHP()-a.getAtk());
		if(b.getTempHP()>0)
		{
			a.setTempHP(a.getTempHP()-b.getAtk());
		}
		a.setY(y-1);
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
			
			//draws the cursor, alice, and skeleton
			for(int i=0;i<friends.size();i++)
			{
				friends.get(i).draw(g);
				if(x==friends.get(i).getX()&&y==friends.get(i).getY())
				{
					g.setFont(new Font("TimesRoman", Font.PLAIN, xSize/3));
					g.setColor(Color.BLACK);
					g.drawString(alice.toString(), xSize/2, ySize/2);
				}
			}
			//alice.draw(g);
			//enemy.draw(g);
			g.drawImage(hand.getImage(),x*xSize+(xSize/4),y*ySize+(ySize/4),xSize/2,ySize/2,null);
			g.setColor(Color.BLUE);
			g.drawRect(x*xSize, y*ySize, xSize, ySize);
			g.drawImage(butn.getImage(),0,0,xSize*16,ySize,null);
			/*if(x==alice.getX()&&y==alice.getY())
			{
				g.setFont(new Font("TimesRoman", Font.PLAIN, xSize/3));
				g.setColor(Color.BLACK);
				g.drawString(alice.toString(), xSize/2, ySize/2);
			}
			if(x==enemy.getX()&&y==enemy.getY())
			{
				g.setFont(new Font("TimesRoman", Font.PLAIN, xSize/3));
				g.setColor(Color.BLACK);
				g.drawString(enemy.toString(), xSize/2, ySize/2);
			}*/
		}
		
		//gamestate for battles once implemented
		else if(gameState==2)
		{
			int p1x=100;
			int p1y=650;
			int p2x=650;
			int p2y=100;
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