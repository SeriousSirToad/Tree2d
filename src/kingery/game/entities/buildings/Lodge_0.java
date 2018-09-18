package kingery.game.entities.buildings;

import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.islands.Island;

public class Lodge_0 extends Building {

	public Lodge_0(int id, int x, int y, BufferedImage entityImage, Engine e,
			Island island) {
		super(id, x, y, entityImage, e, island);
		this.bldg_name = "Lodge";
		this.bldg_desc = "Welcome to the lodge. You want anything?";
		String[] buttons = {"Burger", "Burger", "Burger"};
	}

}
