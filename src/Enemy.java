import org.newdawn.slick.SlickException;

public class Enemy extends Sprite {

	public Enemy(String imageSrc, float x, float y) throws SlickException {
		super(imageSrc, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void contactSprite(Sprite other) {
		if (other instanceof Player) {
			System.exit(0);
		}
	}

}
