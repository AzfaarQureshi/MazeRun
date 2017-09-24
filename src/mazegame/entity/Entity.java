package mazegame.entity;

import java.util.Random;

import mazegame.graphics.Screen;
import mazegame.level.Level;

public class Entity {

	public int x, y; //coordinates of player
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update () {
		
	}
	public void render(Screen screen) {	
	}
	
	public void remove() {
		//remove mob after each iteration (animation). currently only have 1 mob - player
		removed = true;
	}
	public boolean isRemoved() {
		return removed;
	}
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public void init(Level level) {
		this.level = level;
	}
 }
