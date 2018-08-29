package kingery.game.objects;

import java.awt.Rectangle;

public abstract class SolidGameObject extends GameObject{

	public Rectangle collider;
	
	public SolidGameObject(String objectID, String path, int x, int y, int width, int height) {
		super(objectID, path);
		
		collider = new Rectangle(x, y, width, height);
	}

	@Override
	public abstract void update();

}
