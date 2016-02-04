package res;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Texture {
	
	public static Image loadTexture(String resource){
		
		try {
			return new ImageIcon(Texture.class.getResource("/res/images/" + resource)).getImage();
		} catch (Exception e) {
			try {
				return new ImageIcon(Texture.class.getResource("/res/images/unknown.png")).getImage();
			} catch (Exception e1) {
				return null;
			}
		}
	}
	
	public static File loadFont(String resource){
		
		try {
			return new File(Texture.class.getResource("/res/font/" + resource).getFile());
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
}
