package kingery.game.gfx.lighting;

import java.awt.Color;

import kingery.game.islands.Island;

public class BigLight extends Light{

	public BigLight(Island i) {
		super(i.width / 2, i.height / 2, (i.width * i.height) * 2, new Color(0, 0, 0, 0));
		
	}
	
}
