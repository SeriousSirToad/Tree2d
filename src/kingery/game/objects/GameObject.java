package kingery.game.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class GameObject {
	public BufferedImage objectImage;
	private String objectID;

	public GameObject(String objectID, String path) {
		this.objectID = objectID;
		try {
			objectImage = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract void update();

	public String getID() {
		return objectID;
	}

}
