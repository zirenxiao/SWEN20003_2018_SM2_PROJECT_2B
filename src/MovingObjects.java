import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MovingObjects extends Sprite{
	
	private float speed;
	private boolean goRight;
	
	public MovingObjects(String imageSrc, float x, float y, float speed, boolean goRight) throws SlickException {
		super(imageSrc, x, y);
		// TODO Auto-generated constructor stub
		this.speed = speed;
		this.goRight = goRight;
	}
	
	public void setGoLeft(boolean goLeft) {
		this.goRight = goRight;
	}

	public void update(Input input, int delta) {
		if (goRight) {
			this.setPlusX(delta * speed);
		}else {
			this.setPlusX(-delta * speed);
		}
		if (this.getX() > App.SCREEN_WIDTH) {
			this.setX(0);
		}
		if (this.getX() < 0) {
			this.setX(App.SCREEN_WIDTH);
		}
	}
}