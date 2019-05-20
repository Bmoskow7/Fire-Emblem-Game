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
	int yOffset;
	int xOffset;
	int temp;
	
	ImageReader grass = new ImageReader("grass2.png");
	ImageReader stone = new ImageReader("rock.png");
	ImageReader tree = new ImageReader("TreeClear.png");
	
	public Board(int wid, int hei,int windowW, int windowH)
	{
		level = new int[wid][hei];
		windowWidth = windowW;
		windowHeight = windowH;
		xSize = windowW/level.length;
		ySize = windowH/level[0].length;
	}
	
	public Board(int[][] arr, int windowW, int windowH, int x, int y)
	{
		level = arr;
		windowWidth = windowW;
		windowHeight = windowH;
		xSize = windowW/16;
		ySize = windowH/16;
		xOffset=x;
		yOffset=y;
	}
	
	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
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
		for(int i=0;i<16;i++)
		{
			for (int j=0;j<16;j++)
			{
				if(level[i+xOffset][j+yOffset]==1)
				{
					g.drawImage(grass.getImage(),i*xSize,(j+1)*ySize,xSize,ySize,null);				
				}
				if(level[i+xOffset][j+yOffset]==2)
				{
					g.drawImage(grass.getImage(),i*xSize,(j+1)*ySize,xSize,ySize,null);
					g.drawImage(tree.getImage(),i*xSize,(j+1)*ySize,xSize,ySize,null);
				}
				if(level[i+xOffset][j+yOffset]==3)
				{
					g.drawImage(stone.getImage(),i*xSize,(j+1)*ySize,xSize,ySize,null);				
				}
		}
	}
	}
}