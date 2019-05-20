import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Creature 
{
	String name;
	int x;
	int y;
	int h;
	int w;
	int hp;
	int atk;
	int def;
	int exp=0;
	int xOff=0;
	int yOff=0;
	int tempH;
	BufferedImage i;
	boolean select=false;
	ImageReader water = new ImageReader("Water.png");
	
	public Creature(BufferedImage img, String n, int xCoor, int yCoor, int width, int height, int health, int attack, int defense)
	{
		i = img;
		x = xCoor;
		y = yCoor;
		w = width;
		h = height;
		hp = health;
		atk = attack;
		def = defense;
		tempH=hp;
		name=n;
	}
	
	public int getExp()
	{
		return exp;
	}
	
	public void setExp(int num)
	{
		exp=num;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getTempHP()
	{
		return tempH;
	}
	
	public void setTempHP(int k)
	{
		this.tempH=k;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public int getxOff() {
		return xOff;
	}

	public void setxOff(int xOff) {
		this.xOff = xOff;
	}

	public int getyOff() {
		return yOff;
	}

	public void setyOff(int yOff) {
		this.yOff = yOff;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public BufferedImage getImage() {
		return i;
	}
	public void setImage(BufferedImage i) {
		this.i = i;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHeight() {
		return h;
	}
	public void setHeight(int h) {
		this.h = h;
	}
	public int getWidth() {
		return w;
	}
	public void setWidth(int w) {
		this.w = w;
	}
	public boolean intersects(Creature c)
	{
		if(this.x==c.x&&this.y==c.y)
			return true;
		else
			return false;
	}
	public void setSelect(boolean b)
	{
		select=b;
	}
	public boolean isSelect()
	{
		return select;
	}
	public void draw(Graphics g)
	{
		g.drawImage(i, (x+xOff)*w, (y+yOff)*h, w, h, null);
	}
	
	public String toString()
	{
		return(name+"               Health: "+tempH+"/"+hp);
	}
}
