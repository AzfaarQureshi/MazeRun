package mazegame.level.tile;

import mazegame.graphics.Screen;
import mazegame.graphics.Sprite;

public class exitTile extends Tile {

	public exitTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4,this);
		}
	
	public boolean exit() {
		return true;
	}
	

}
