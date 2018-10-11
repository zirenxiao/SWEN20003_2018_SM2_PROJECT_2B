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

	private static final float WATERINITY = 336;
	private static final float WATERFINALY = 48;
	private static final float GRASSINITY = 672;
	private static final float GRASSFINALY = 384;
	private static final float PLAYERINITX = 512;
	private static final float PLAYERINITY = 720;
	private static final float BUSINITY = 432;
	// Bus lines offsets and distances
	private static final float LINE1OFFSET = 48;
	private static final float LINE1DIS = 6.5f;
	private static final float LINE2OFFSET = 0;
	private static final float LINE2DIS = 5;
	private static final float LINE3OFFSET = 64;
	private static final float LINE3DIS = 12;
	private static final float LINE4OFFSET = 128;
	private static final float LINE4DIS = 5;
	private static final float LINE5OFFSET = 250;
	private static final float LINE5DIS = 6.5f;
	// Current bus line
	private int currentLine = 0;
	// Player instance
	private Player player;
	
	private static World world = null;
	
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>(); 
	
	/**
	 * @throws SlickException
	 */
	public World(){
		try {
			// Grass and water
			for (float y=WATERFINALY; y<WATERINITY; y+=TILESIZE) {
				for (int x=0; x<App.SCREEN_WIDTH; x+=TILESIZE) {
					sprites.add(new Water(x, y));
				}
			}
			for (int x=0; x<App.SCREEN_WIDTH; x+=TILESIZE) {
				sprites.add(new Grass(x, GRASSINITY));
				sprites.add(new Grass(x, GRASSFINALY));
			}
			// Player
			player = new Player(PLAYERINITX, PLAYERINITY);
			sprites.add(player);
			// Bus lines
			this.constructLine(LINE1OFFSET, LINE1DIS);
			this.constructLine(LINE2OFFSET, LINE2DIS);
			this.constructLine(LINE3OFFSET, LINE3DIS);
			this.constructLine(LINE4OFFSET, LINE4DIS);
			this.constructLine(LINE5OFFSET, LINE5DIS);
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
	
	/** Construct a bus line
	 * @param offset
	 * @param distance
	 * @throws SlickException
	 */
	private void constructLine(float offset, float distance)
			throws SlickException {
		// Only even line moves from left to right
		boolean right = (currentLine % 2) != 0;
		for (float x=offset; x<App.SCREEN_WIDTH; x+=distance*TILESIZE) {
			sprites.add(new Obstacles(x, 
					BUSINITY + TILESIZE * currentLine, right));
		}
		currentLine++;
	}
	
	/** Add a sprite into list
	 * @param sprite
	 */
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}
}
