import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @author Jiayin Cai
 *
 */
public class World {
	public static final float TILESIZE = 48;

	private static final float PLAYERINITX = 512;
	private static final float PLAYERINITY = 720;
	
    private static final String LVZEROFILE = "assets/levels/0.lvl";
    private static final String LVONEFILE = "assets/levels/1.lvl";
    private static final String SPLITER = ",";
	
	// Player instance
	private Player player;
	
	private static World world = null;
	
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>(); 
	
	
	/**
	 * @throws SlickException
	 */
	public World(){
		try {
			readFile(LVZEROFILE);
			// Player
			player = new Player(PLAYERINITX, PLAYERINITY);
			sprites.add(player);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/** Make the World into Singleton
	 * @return
	 */
	public static World getInstance() {
		if (world == null) {
			world = new World();
		}
		return world;
	}
	
	/** Update all of the sprites in the game
	 * @param input
	 * @param delta
	 */
	public void update(Input input, int delta) {
		for (Sprite sprite:sprites) {
			sprite.update(input, delta);
			for (Sprite other:sprites) {
				if (sprite.isContactWith(other)) {
					sprite.contactSprite(other);
				}
			}
		}
	}
	
	/** Draw all of the sprites in the game
	 * @param g
	 */
	public void render(Graphics g) {
		for (Sprite sprite:sprites) {
			sprite.render();
		}
	}
	
	
	/** Add a sprite into list
	 * @param sprite
	 */
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}
	
	public void readFile(String filePath) {
		File file = new File(filePath);
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null){
			    //process the line
				String[] splits = line.split(SPLITER);
				float x = Integer.parseInt(splits[1]);
				float y = Integer.parseInt(splits[2]);
				boolean isMovingRight;
			    switch (splits[0]) {
			    case "water":
			    	sprites.add(new Water(x, y));
			    	break;
			    case "grass":
			    	sprites.add(new Grass(x, y));
			    	break;
			    case "bus":
			    	isMovingRight = Boolean.parseBoolean(splits[3]);
			    	sprites.add(new Obstacles(x, y, isMovingRight));
			    	break;
			    case "log":
			    	isMovingRight = Boolean.parseBoolean(splits[3]);
			    	sprites.add(new Log(x, y, isMovingRight));
			    	break;
			    case "longLog":
			    	isMovingRight = Boolean.parseBoolean(splits[3]);
			    	sprites.add(new LongLog(x, y, isMovingRight));
			    	break;
			    default:
			    	break;
			    }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
