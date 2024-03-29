import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MovingObjects extends Sprite {

	private float speed;
	private boolean goRight;

	public MovingObjects(String imageSrc, float x, float y, 
			float speed, boolean goRight) throws SlickException {
		super(imageSrc, x, y);
		// TODO Auto-generated constructor stub
		this.speed = speed;
		this.goRight = goRight;
	}

	/*
	 * Update the current location of a moving object
	 * 
	 * @see Sprite#update(org.newdawn.slick.Input, int)
	 */
	public void update(Input input, int delta) {
		super.update(input, delta);
		if (goRight) {
			this.setPlusX(delta * speed);
		} else {
			this.setPlusX(-delta * speed);
		}
		this.adjustPosition();
	}

	/**
	 * Get speed of a moving object
	 * 
	 * @return
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * Whether the moving object moves from left to right
	 * 
	 * @return
	 */
	public boolean isGoRight() {
		return goRight;
	}

	public void setGoRight(boolean goRight) {
		this.goRight = goRight;
	}

	/**
	 * Adjust the position of the moving object when it goes out 
	 * of the screen
	 */
	public void adjustPosition() {
		if (this.getX() > App.SCREEN_WIDTH 
				+ this.getImage().getWidth() / 2) {
			this.setX(-this.getImage().getWidth());
		}
		if (this.getX() < -this.getImage().getWidth()) {
			this.setX(App.SCREEN_WIDTH 
					+ this.getImage().getWidth() / 2);
		}
	}
}
