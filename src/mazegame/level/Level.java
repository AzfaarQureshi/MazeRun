package mazegame.level;

import mazegame.Menu;
import mazegame.graphics.Screen;
import mazegame.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	public static Level one = new SpawnLevel("/textures/mazelevel.png");
		public Level(int width, int height){
		this.width = width ;
		this.height = height;
		tilesInt = new int [width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}
	
	protected void generateLevel() {
	}
	
	protected void loadLevel (String path){
	}
	
	
	public void update(){
	}
	
	private void time(){
	}
	
	public void render(int xScroll,int yScroll, Screen screen){
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 64) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 64) >> 4;
		
		for(int y = y0; y < y1; y++){
			for(int x = x0; x<x1; x++){
				getTile(x,y).render(x, y, screen);
			}
		}
	}
	public Tile getTile(int x, int y){
		//this is where u convert pixel map to fullscale map. each color corresponds to a different tile
		if(x < 0|| y < 0 || x >= width || y >= height)return Tile.voidtile;
		if(tiles[x + y * width] == 0xFF00FF00)return Tile.grass;
		if(tiles[x + y * width] == 0xFFFF0000) return Tile.ground;
		if(tiles[x+y*width] == 0xffffff00) return Tile.flower; 
		if(tiles[x+y*width] == 0xff7f7f00) return Tile.rock;
		if(tiles[x+y*width] == 0xff0000ff) return Tile.cloud;
		if(tiles[x+y*width] == 0xffff0000) return Tile.play1;
		if(tiles[x+y*width] == 0xffff1000) return Tile.play2;
		if(tiles[x+y*width] == 0xffff3000) return Tile.play3;
		if(tiles[x+y*width] == 0xffff4000) return Tile.play4;
		if(tiles[x+y*width]== 0xffaa00aa) return Tile.cloud1;
		if(tiles[x+y*width]== 0xff0094ff) return Tile.enter;
		if(tiles[x+y*width]== 0xffbbbb00)return Tile.sky;
		if(tiles[x+y*width]== 0xff992DFF)return Tile.exit;
		return Tile.voidtile; 
		
	}
	
	public String name(int x, int y) {
		if(tiles[x + y * width] == 0xFF00FF00)return "grass";
		if(tiles[x + y * width] == 0xFFFF0000) return "ground";
		if(tiles[x+y*width] == 0xffffff00) return "flower"; 
		if(tiles[x+y*width] == 0xff7f7f00) return "rock";
		if(tiles[x+y*width] == 0xff0000ff) return "cloud";
		if(tiles[x+y*width] == 0xffff0000) return "play1";
		if(tiles[x+y*width] == 0xffff1000) return "play2";
		if(tiles[x+y*width] == 0xffff3000) return "play3";
		if(tiles[x+y*width] == 0xffff4000) return "play4";
		if(tiles[x+y*width]== 0xffaa00aa) return "cloud1";
		if(tiles[x+y*width]== 0xff0094ff) return "enter";
		if(tiles[x+y*width]== 0xffbbbb00)return "sky";
		if(tiles[x+y*width]== 0xff992DFF)return "exit";
		return "voidtile";
		}
}
