package mazegame.graphics;

import java.util.Random;

import mazegame.entity.mob.Player;
import mazegame.level.tile.Tile;


public class Screen {
public double offset = 0;// edward herbet for world gen
public double offset1 = 0.000005; // make this depend on what level is


	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE -1;
	public int xOffset, yOffset;
	
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];//4096 tiles 
	
	private Random random = new Random();
	
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int [width * height];//50400 pixels
		
		for (int g = 0; g < MAP_SIZE*MAP_SIZE; g++){
			tiles[g] = random.nextInt(0xffffff);
		}
		
	}
	public void clear(){
		for(int i = 0; i < pixels.length; i++){
		pixels[i] = 0;}
	}
	//RENDERS THE TILES	
	public void renderTile(int xp, int yp, Tile tile){
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y<tile.sprite.SIZE; y++){
			int ya = y + yp; 
			for(int x = 0; x<tile.sprite.SIZE; x++){
				int xa = x + xp; 
				if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya>= height )break;
				if (xa < 0) xa = 0;
				pixels[xa + ya *  width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
		}
		}
		}
	public void renderTile32(int xp, int yp, Tile tile){
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y<32; y++){
			int ya = y + yp; 
			for(int x = 0; x<32; x++){
				int xa = x + xp; 
				if(xa < -32 || xa >= width || ya < 0 || ya>= height )break;
				if (xa < 0) xa = 0;
				pixels[xa + ya *  width] = tile.sprite.pixels[x + y * 32];
		}
		}
		}
	
	public void renderPlayer(int xp, int yp, Sprite sprite, int flip ){
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y< 32; y++){
			int ys = y;
			int ya = y + yp;
			if (flip == 2 || flip== 3) ys = 31 - y;
			for(int x = 0; x<32; x++){
				int xa = x + xp; 
				int xs = x;
				if(flip == 1 || flip == 3) 	 xs =  31 - x;
				if(xa < -32 || xa >= width || ya < 0 || ya>= height )break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[xs + ys * 32];
				if (col != 0xffff00ff) pixels[xa + ya *  width] = col;
		}
	}
}
	/*public void renderPlayer2(int xp, int yp, Sprite sprite ){
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y< 32; y++){
			int ya = y + yp; 
			for(int x = 0; x<32; x++){
				int xa = x + xp; 
				if(xa < -32 || xa >= width || ya < 0 || ya>= height )break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * 32];
				if (col != 0xffff00ff) pixels[xa + ya *  width] = col;
		}
	}
}*/
	
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
