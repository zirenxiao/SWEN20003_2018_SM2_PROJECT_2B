import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Sprite {
	private static final float SPEED = 48;
	private static final String PLAYERFILE = "assets/frog.png";
	private ArrayList<Life> lives = new ArrayList<Life>();
	private static final float LIFEINITIALX = 24;
	private static final float LIFEINITIALY = 744;
	private static final float LIFEDIST = 32;
	private static final float PLAYERINITX = 512;
	private static final float PLAYERINITY = 720;
	
	private int lifeLeft = 10;

	public Player() throws SlickException {
		super(PLAYERFILE, PLAYERINITX, PLAYERINITY);
		for (int i = 0; i < lifeLeft; i++) {
			Life life = new Life(LIFEINITIALX + i * LIFEDIST, LIFEINITIALY);
			lives.add(life);
		}
	}

	/* Update a player action, including moves to up,
	 * down, left and right.
	 * @see Sprite#update(org.newdawn.slick.Input, int)
	 */
	public void update(Input input, int delta) {
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			this.setPlusY(-SPEED);
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			this.setPlusX(-SPEED);
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			this.setPlusY(SPEED);
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			this.setPlusX(SPEED);
		}
		if (this.getY() <= 0) {
			this.setY(0);
		}
		if (this.getX() <= 0) {
			this.setX(0);
		}
		if (this.getY() >= App.SCREEN_HEIGHT) {
			this.setY(App.SCREEN_HEIGHT);
		}
		if (this.getX() >= App.SCREEN_WIDTH) {
			this.setX(App.SCREEN_WIDTH);
		}
	}
	
	/* Render a player, including all lives
	 * @see Sprite#render()
	 */
	public void render() {
		super.render();
		for (Life life : lives) {
			life.render();
		}
	}
	
	/** When add a life, a new life will be shown
	 * 
	 */
	public void addLife() throws SlickException {
		lifeLeft ++;
		Life life = new Life(LIFEINITIALX + lives.size() * LIFEDIST, LIFEINITIALY);
		lives.add(life);
	}
	
	/** When lose a life, the player will reset
	 * to its initial position
	 */
	public void loseLife() {
		if (lifeLeft > 1) {
			this.lives.remove(lifeLeft - 1);
			lifeLeft--;
			this.resetPosition();
		}else {
			System.exit(0);
		}
		
	}
	
	/** Reset the player into initial position
	 * 
	 */
	public void resetPosition() {
		this.setX(PLAYERINITX);
		this.setY(PLAYERINITY);
		
	}
}
