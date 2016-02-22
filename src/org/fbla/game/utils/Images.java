package org.fbla.game.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

import org.fbla.game.sprites.tools.Key;

public class Images {
	
	public static Image toImage(BufferedImage bimage){
        // Casting is enough to convert from BufferedImage to Image
        Image img = (Image) bimage;
        return img;
    }
	
	public static Image getEmptyImage(int width, int height){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return toImage(img);
    }

	public static Image rotate(Image img, double angle) {
		double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));

		int w = img.getWidth(null), h = img.getHeight(null);

		int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);

		BufferedImage bimg = toBufferedImage(getEmptyImage(16, 4));
		Graphics2D g = bimg.createGraphics();

		g.translate((neww - w) / 2, (newh - h) / 2);
		g.rotate(Math.toRadians(angle), w / 2, h / 2);
		g.drawRenderedImage(toBufferedImage(img), null);
		g.dispose();

		return toImage(bimg);
	}

	public static Image makeColorTransparent(Image im, final Color color) {
		ImageFilter filter = new RGBImageFilter() {
			// the color we are looking for... Alpha bits are set to opaque
			public int markerRGB = color.getRGB() | 0xFF000000;

			@Override
			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					// Mark the alpha bits as zero - transparent
					return 0x00FFFFFF & rgb;
				} else {
					// nothing to do
					return rgb;
				}
			}
		};

		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}

	public static Image makeImageTranslucent(BufferedImage source, double alpha) {
		BufferedImage target = new BufferedImage(source.getWidth(), source.getHeight(), Transparency.TRANSLUCENT);
		Graphics2D g = target.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
		g.drawImage(source, null, 0, 0);
		g.dispose();
		return target;
	}

	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}

	public static void colorKey(Image image, Key key) {
		
		colorKey(toBufferedImage(image), key);
		
	}
	
	public static void colorKey(BufferedImage image, Key key) {
		
		int r=0;
		int g=0;
		int b=0;
		switch(key.getID()){
		case 1:
			r=255;
			g=0;
			b=0;
			break;
		default:
			break;
		}
		
		float[] h = Color.RGBtoHSB(r, g, b, null);
		int color = Color.HSBtoRGB(h[0], h[1], h[2]);
		int dcolor = Color.HSBtoRGB(h[0], h[1], h[2]-50);
		int lcolor = Color.HSBtoRGB(h[0], h[1], h[2]-20);
		
		for(int x=0;x!=image.getWidth();x++){
			for(int y=0;y!=image.getHeight();y++){
				if(image.getRGB(x, y) == Color.decode("#C4C400").getRGB())
					image.setRGB(x, y, color);
				if(image.getRGB(x, y) == Color.decode("#484800").getRGB())
					image.setRGB(x, y, dcolor);
				if(image.getRGB(x, y) == Color.decode("#939300").getRGB())
					image.setRGB(x, y, lcolor);
				
			}
		}
		
		key.loadImage(image);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}