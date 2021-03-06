package mazegame.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mazegame.level.tile.Tile;

public class SpawnLevel extends Level{

	
	
	public SpawnLevel(String path) {
		super(path);
	}
	
	protected void loadLevel (String path){
		try{
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int [w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e){
			e.printStackTrace();
			System.out.println("Exception! Could not load Level file!");
		}
	}
	//ground is 0xffff0000
	//Grass = 0xFF0000 first two are alpha support 
	//Flower = 0xFFFF00
	//ROCK = 0x7F7F00
	protected void generateLevel(){
		}
		
	}

