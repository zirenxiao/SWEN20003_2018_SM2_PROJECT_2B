import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @author Jiayin Cai
 *
 */
public class World {
	public static final float TILESIZE = 48;
	
    private static final String LVZEROFILE = "assets/levels/0.lvl";
    private static final String LVONEFILE = "assets/levels/1.lvl";
    private static final String SPLITER = ",";
    private static final int RANDOMMIN = 2500;
	private static final int RANDOMMAX = 3500;
	private int overallTimePass = 0;
	private int randomNum;
    private int logNum = 0;
    
    private boolean stageZeroClear = false;
    private boolean stageOneClear = false;
	
	private static World world = null;
	
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>(); 
	private ArrayList<Integer> treePosition = new ArrayList<Integer>();
	
	
	/**
	 * @throws SlickException
	 */
	public World(){
		Random random = new Random();
		this.randomNum = 
				random.nextInt((RANDOMMAX - RANDOMMIN) + 1) + RANDOMMIN;
//		System.out.println(this.randomNum);
		
		init(LVZEROFILE);
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
	
	private void init(String fileName) {
		logNum = 0;
		try {
			readFile(fileName);
			// Player
			sprites.add(new Player());
			initGoal();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** Update all of the sprites in the game
	 * @param input
	 * @param delta
	 * @throws SlickException 
	 */
	public void update(Input input, int delta) throws SlickException {
		if (stageOneClear) {
			return;
		}
		
		int goalCount = 0;
		overallTimePass += delta;

		// add a new extra life to a random log or long log
		if (overallTimePass > randomNum) {
			Random random = new Random();
			int randomLog = random.nextInt(this.logNum) + 1;
			int i = 0;
			ExtraLife newSprite = null;
			for (Sprite sprite:sprites) {
				if (sprite instanceof LifeRideable) {
					LifeRideable lifeRideable = (LifeRideable) sprite;
					i++;
					if (i == randomLog) {
						newSprite = new ExtraLife(lifeRideable.getX(), 
								lifeRideable.getY());
						lifeRideable.addExtraLife(newSprite);
					}
				}
			}
			sprites.add(newSprite);
			overallTimePass = 0;
		}
		
		for (int spriteNum = 0; spriteNum < sprites.size(); spriteNum++) {
			Sprite sprite = sprites.get(spriteNum);

			//remove dead extra lives
			if (sprite instanceof LifeRideable) {
				LifeRideable lifeRideable = (LifeRideable)sprite;
				ArrayList<ExtraLife> extraLives = 
						lifeRideable.getExtraLives();
				for (int lifeNum = 0; lifeNum < extraLives.size(); 
						lifeNum++) {
					ExtraLife extraLife = extraLives.get(lifeNum);
					if (extraLife.isDead()) {
						lifeRideable.removeExtraLife(extraLife);
						sprites.remove(extraLife);
					}
				}
			}
			
			sprite.update(input, delta);
			for (Sprite other:sprites) {
				if (sprite.isContactWith(other)) {
					sprite.contactSprite(other, delta);
				}
			}
			if (sprite instanceof Goal) {
				if (!((Goal) sprite).isFound()) {
					goalCount++;
					
				}
				
			}
		}
		
		if (goalCount == 0 && !stageZeroClear) {
			sprites.clear();
			init(LVONEFILE);
			stageZeroClear = true;
		}else if (goalCount == 0 && stageZeroClear) {
			sprites.clear();
			stageOneClear = true;
		}
	}
	
	/** Draw all of the sprites in the game
	 * @param g
	 */
	public void render(Graphics g) {
		for (Sprite sprite:sprites) {
			sprite.render();
		}
		if (stageOneClear) {
			g.drawString("YOU WIN!", App.SCREEN_HEIGHT / 2, 
					App.SCREEN_WIDTH / 2);
		}
	}
	
	
	/** Add a sprite into list
	 * @param sprite
	 */
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}
	
	/** Read level file from a path
	 * @param filePath
	 */
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
			    case "tree":
			    	updateTreePos(x, y);
			    	sprites.add(new Tree(x, y));
			    	break;
			    case "bus":
			    	isMovingRight = Boolean.parseBoolean(splits[3]);
			    	sprites.add(new Obstacles(x, y, isMovingRight));
			    	break;
			    case "log":
			    	isMovingRight = Boolean.parseBoolean(splits[3]);
			    	sprites.add(new Log(x, y, isMovingRight));
			    	logNum++;
			    	break;
			    case "longLog":
			    	isMovingRight = Boolean.parseBoolean(splits[3]);
			    	sprites.add(new LongLog(x, y, isMovingRight));
			    	logNum++;
			    	break;
			    case "turtle":
			    	isMovingRight = Boolean.parseBoolean(splits[3]);
			    	sprites.add(new Turtles(x, y, isMovingRight));
			    	break;
			    case "racecar":
			    	isMovingRight = Boolean.parseBoolean(splits[3]);
			    	sprites.add(new Racecar(x, y, isMovingRight));
			    	break;
			    case "bike":
			    	isMovingRight = Boolean.parseBoolean(splits[3]);
			    	sprites.add(new Bike(x, y, isMovingRight));
			    	break;
			    case "bulldozer":
			    	isMovingRight = Boolean.parseBoolean(splits[3]);
			    	sprites.add(new Bulldozer(x, y, isMovingRight));
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

	/** The initialise the goal state
	 * @throws SlickException
	 */
	private void initGoal() throws SlickException {
		for (int i=0; i<=App.SCREEN_WIDTH; i+=TILESIZE) {
			boolean found = false;
			for (int temp:treePosition) {
				if (i == temp) {
					found = true;
					break;
				}
			}
			if (!found) {
				sprites.add(new Goal(i, TILESIZE));
			}
				
		}
	}
	
	/** Get all sprites 
	 * @return
	 */
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}


	/** Update the position of the tree,
	 * so we can generate the position of 
	 * goals
	 * @param x
	 * @param y
	 */
	private void updateTreePos(float x, float y) {
		if (Math.round(y) == TILESIZE) {
			treePosition.add(Math.round(x));
		}
	}
	
}
