import org.newdawn.slick.SlickException;

public class Rideable extends MovingObjects {

	public Rideable(String imageSrc, float x, float y, float speed, boolean goLeft) throws SlickException {
		super(imageSrc, x, y, speed, goLeft);
		// TODO Auto-generated constructor stub
	}

	public void contactSprite(Sprite other) {
		if (other instanceof Player) {
			System.exit(0);
		}
	}
}
