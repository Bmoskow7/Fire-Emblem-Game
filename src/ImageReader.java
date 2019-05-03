import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageReader 
{
	public BufferedImage img;
	public ImageReader(String path)
	{
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getImage()
	{
		return img;
	}
}
