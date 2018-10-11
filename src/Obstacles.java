import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Obstacles extends Enemy {
	private static final float SPEED = 0.15f;
	private static final String BUSFILE = "assets/bus.png";
	private boolean isRight;

	/** Obstacle constructor
	 * @param imageSrc
	 * @param x
	 * @param y
	 * @param right Whether the object is moving from left to right
	 * @throws SlickException
	 */
	public Obstacles(float x, float y, boolean right) 
			throws SlickException {
		super(BUSFILE, x, y);
		this.isRight = right;
	}
	
	/* Update the bus moving
	 * @see Sprite#update(org.newdawn.slick.Input, int)
	 */
	public void update(Input input, int delta) {
		if (isRight) {
			this.setPlusX(delta * SPEED);
		}else {
			this.setPlusX(-delta * SPEED);
		}
		if (this.getX() > App.SCREEN_WIDTH) {
			this.setX(0);
		}
		if (this.getX() < 0) {
			this.setX(App.SCREEN_WIDTH);
		}
	}

}
