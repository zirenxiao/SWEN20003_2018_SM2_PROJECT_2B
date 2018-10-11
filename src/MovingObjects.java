import org.newdawn.slick.SlickException;

public class MovingObjects extends Sprite{
	
	private float speed;
	private boolean goLeft;
	
	public MovingObjects(String imageSrc, float x, float y, float speed, boolean goLeft) throws SlickException {
		super(imageSrc, x, y);
		// TODO Auto-generated constructor stub
		this.speed = speed;
		this.goLeft = goLeft;
	}
	
	public void setGoLeft(boolean goLeft) {
		this.goLeft = goLeft;
	}

}
