import org.newdawn.slick.SlickException;

public class Enemy extends MovingObjects {
	
	public Enemy(String imageSrc, float x, float y, float speed, boolean goRight) throws SlickException {
		super(imageSrc, x, y, speed, goRight);
		// TODO Auto-generated constructor stub
	}

	public void contactSprite(Sprite other) {
		if (other instanceof Player) {
			System.exit(0);
		}
	}

}
