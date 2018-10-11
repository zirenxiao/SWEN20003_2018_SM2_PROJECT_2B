import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Sprite {
	private static final float SPEED = 48;
	private static final String PLAYERFILE = "assets/frog.png";

	public Player(float x, float y) throws SlickException {
		super(PLAYERFILE, x, y);
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
		if (this.getY() >= App.SCREEN_HEIGHT - World.TILESIZE) {
			this.setY(App.SCREEN_HEIGHT - World.TILESIZE);
		}
		if (this.getX() >= App.SCREEN_WIDTH - World.TILESIZE) {
			this.setX(App.SCREEN_WIDTH - World.TILESIZE);
		}
	}
}
