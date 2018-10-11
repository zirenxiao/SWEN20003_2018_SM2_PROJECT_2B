import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Bike extends Enemy {

	private static final String BIKEFILE = "assets/bike.png";
	private static final float SPEED = 0.2f;
	private static final float XBOUND1 = 20;
	private static final float XBOUND2 = 1000;

	public Bike(float x, float y, boolean goRight) throws SlickException {
		super(BIKEFILE, x, y, SPEED, goRight);
		// TODO Auto-generated constructor stub
	}

	/**
	 * The bike reverse its direction when it is going left and is less than the
	 * left bound, or is going right and is more than the right bound
	 */
	public void update(Input input, int delta) {
		super.update(input, delta);
		if ((this.getX() <= XBOUND1 && !this.isGoRight()) || 
				(this.getX() >= XBOUND2 && this.isGoRight())) {
			this.setGoRight(!this.isGoRight());
		}
	}

}
