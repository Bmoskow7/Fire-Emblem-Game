import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Board
{
	int windowWidth;
	int windowHeight;
	int[][] level;
	int xSize;
	int ySize;
	ImageReader grass = new ImageReader("grass.png");
	ImageReader stone = new ImageReader("rock.png");
	ImageReader tree = new ImageReader("tree.png");
	
	public Board(int wid, int hei,int windowW, int windowH)
	{
		level = new int[wid][hei];
		windowWidth = windowW;
		windowHeight = windowH;
		xSize = windowW/level.length;
		ySize = windowH/level[0].length;
	}
	
	public Board(int[][] arr, int windowW, int windowH)
	{
		level = arr;
		windowWidth = windowW;
		windowHeight = windowH;
		xSize = windowW/level.length;
		ySize = windowH/level[0].length;
	}
	
	public void setLevel(int x, int y, int value)
	{
		level[x][y] = value;
		
		
	}
	public int[][] getLevel()
	{
		return level;
	}
	
	public int getCoor(int x, int y)
	{
		return level[x][y];
	}
	
	public void draw(Graphics g)
	{
		for(int i=0;i<level.length;i++)
		{
			for (int j=0;j<level[0].length;j++)
			{
				if(level[i][j]==1)
				{
					g.drawImage(grass.getImage(),i*xSize,j*ySize,xSize,ySize,null);				
				}
					
		}
	}
	}
}