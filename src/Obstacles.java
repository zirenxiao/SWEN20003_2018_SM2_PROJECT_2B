import org.newdawn.slick.SlickException;

public class Obstacles extends Enemy {
	private static final float SPEED = 0.15f;
	private static final String BUSFILE = "assets/bus.png";

	/** Obstacle constructor
	 * @param imageSrc
	 * @param x
	 * @param y
	 * @param right Whether the object is moving from left to right
	 * @throws SlickException
	 */
	public Obstacles(float x, float y, boolean right) throws SlickException {
		super(BUSFILE, x, y, SPEED, right);
	}

}
