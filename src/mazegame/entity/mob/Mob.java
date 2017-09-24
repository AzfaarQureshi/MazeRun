package mazegame.entity.mob;

import mazegame.entity.Entity;
import mazegame.graphics.Sprite;
import mazegame.level.tile.*;

public abstract class Mob extends Entity{

	protected Sprite sprite;
	protected int dir = 0; // 0 is north, 1 is east, 2 is south, 3 is west
	protected boolean moving = false;
	private RockTile rtile;
	
	public void move (int xa, int ya){
		//the parameters take -1, 0, 1. values go into x
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
			
		if(!collision(xa, ya)){ // if collision is false
		x += xa;
		y += ya;
		}
	}
	
	public void update () {
	}
	
	
	//level.gettile allows you to use any method from the tile class.
	//in game.java add levels 1, 2, 3. when u reach exit tile then u go to next level. if timer runs out u go back into menu
	private boolean collision(int xa, int ya){
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x+xa) + c % 2 * 23)/ 16; // incase i forget in the future, c=corner, multiply by width of collision area
			int yt  = ((y+ya) + c / 2 * 20) /16; // its y axis so I have to divide instead of mod
			if (level.getTile(xt, yt).solid()) {
				solid = true;
			}
		}
		
		
		return solid; //stops movement
	}
	public void render(){
	}
}
