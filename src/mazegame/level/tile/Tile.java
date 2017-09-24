package mazegame.level.tile;

import mazegame.graphics.Screen;
import mazegame.graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public Tile(Sprite sprite){
		this.sprite = sprite;	
	}
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile exit = new exitTile(Sprite.exit);
	public static Tile flower = new FlowerTile(Sprite.flower);//refer to level, sprite and tile class
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile ground = new RockTile(Sprite.ground);
	public static Tile cloud = new FlowerTile(Sprite.cloud);
	public static Tile play1 = new FlowerTile(Sprite.play1);
	public static Tile play2 = new FlowerTile(Sprite.play2);
	public static Tile play3 = new FlowerTile(Sprite.play3);
	public static Tile play4 = new FlowerTile(Sprite.play4);
	public static Tile enter = new FlowerTile(Sprite.enter);
	
	public static Tile voidtile = new VoidTile(Sprite.voidSprite);
	public static Tile cloud1= new FlowerTile(Sprite.cloud1);
	public static Tile sky = new FlowerTile(Sprite.sky);
	
	public void render(int x, int y, Screen screen){	
	}
	
	
	public boolean solid(){
		return false;
	}
	
	public boolean exit() {
		return false;
	} //return false for exit by default, unless ur on the exit tile but then we'll rewrite then in the subclass
	
	
}
