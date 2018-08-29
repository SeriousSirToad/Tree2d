package kingery.game.islands.tiles;

public class BasicSolidTile extends BasicTile{

	public BasicSolidTile(int id, int SX, int SY, int levelColor) {
		super(id, SX, SY, levelColor);
		
		this.isSolid = true;
	}

}
