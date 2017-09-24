package mazegame; 

import java.awt.Canvas;
//import java.awt.Color;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import mazegame.entity.Entity;
import mazegame.entity.mob.Player;
import mazegame.graphics.Screen;
import mazegame.input.Keyboard;
import mazegame.level.Level;
import mazegame.level.RandomLevel;
import mazegame.level.SpawnLevel;
import mazegame.level.TileCoordinate;
import mazegame.level.menu;




public class Game extends Canvas implements Runnable { 
	private static final long serialVersionUID = 1L;

	public static int width = 400;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	public static String title = "Rain";
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	protected Level level;
	private Player player;
	public String menu = "/textures/newmenu.png";
	public String spawnlvl = "/textures/Level.png";
	public String htp = "/textures/howtoplay.png";
	
	
	
	private boolean running = false;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game() {
	    Dimension size = new Dimension(width * scale, height * scale);
	    setPreferredSize(size);
	
	    screen = new Screen(width, height);	
	    frame = new JFrame();
	    key = new Keyboard();
	    level = Level.one;
	   TileCoordinate playerSpawn = new TileCoordinate(13,10); //40,13
	    player = new Player(playerSpawn.x(), playerSpawn.y(), key);
	    player.init(level);
	    addKeyListener(key);
	  
	}
	
	public synchronized void start() {
	    running = true;
	    thread = new Thread(this, "Display");
	    thread.start();
	}
	
	public synchronized void stop() {
	    running = false;
	    try {
	        thread.join();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public void run() {
		
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		
		int frames = 0;
		int updates = 0;
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		
		requestFocus();
		
	    while (running) {
	    	long now = System.nanoTime();
	    	
	    	delta += (now - lastTime) / ns;
	    	lastTime = now;
	    	
	    	while(delta >= 1) {
	    		update();
	    		updates++;
	    		delta--;
	    	}
	    	
	        render();
	        frames++;
	        
	        if(System.currentTimeMillis() - timer >= 1000) {
	        	timer += 1000;
	        	frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
	        	frames = 0;
	        	updates = 0;
	        }
	    }
	    
	    stop();
	}
	
	//chooses which functions to run every update cycle
	public void update() {
		key.update();
		player.update();
		testswag();
		if (player.levelcomplete(player.getx(), player.gety())) {
			menustate = true;
		}
	}
	public static boolean menustate = true;
	public static boolean howtoplay = false;
	
	
	public void testswag(){
		
		if(menustate == true){
			level = new menu(menu);
		}
		if(key.keys[KeyEvent.VK_ENTER]){
				 level = Level.one;
				 menustate = false;
				 howtoplay = false;
		}
		if(key.keys[KeyEvent.VK_H]){
			howtoplay = true;
			menustate = false;
		}
		if(howtoplay==true){
			level = new menu(htp);
		} 
	/*	if (player.levelcomplete(player.getx(), player.gety())) {
			menustate = true;
			
		} */
		
		//if player is on exit tile, then change level to menue.
	}
	
	//updates game 
	public void render() {
	    BufferStrategy bs = getBufferStrategy();
	    if (bs == null) {
	        createBufferStrategy(3);
	        return;
	       
	    } 
	    testswag();
	    screen.clear();
	    int xScroll = player.x -screen.width /2;
	    int yScroll = player.y - screen.height /2;
	    level.render(xScroll, yScroll, screen);
	    player.render(screen);
	    
	    for(int i = 0; i < pixels.length; i++) {
	    	pixels[i] = screen.pixels[i];
	    }
	    if(menustate == true){
	    	
			BufferStrategy bs1 = getBufferStrategy();
			Graphics g = bs1.getDrawGraphics();
		    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		    g.setColor(Color.black);
		    g.setFont(new Font("Jokerman", Font.BOLD, 50));
		    g.drawString("MazeRun!", 400, 100);
		    g.setFont(new Font("Verdana", 0, 40));
		    g.drawString("use ARROW keys to move!", 350, 200);
		    g.drawString("Reach the portal at the end of the maze to exit!", 200, 300);
		    
		    bs1.show();
	}
	    if(menustate == false){
	    	Graphics g = bs.getDrawGraphics();
		    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		    g.dispose();
		    bs.show();
	    }
	  
	}
	
	public static void main(String[] args) {
	    Game game = new Game();
	    game.frame.setResizable(false);
		  game.frame.setTitle(Game.title);
		  game.frame.add(game);
		  game.frame.pack();
		  game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  game.frame.setLocationRelativeTo(null);
		  game.frame.setVisible(true);
	    
	    game.start();
	}

}