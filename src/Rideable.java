import org.newdawn.slick.SlickException;

public class Rideable extends MovingObjects {

	public Rideable(String imageSrc, float x, float y, float speed, boolean goRight) throws SlickException {
		super(imageSrc, x, y, speed, goRight);
		// TODO Auto-generated constructor stub
	}

	public void contactSprite(Sprite other, int delta) {
		if (other instanceof Player) {
			if (this.isGoRight()) {
				other.setPlusX(delta * this.getSpeed());
			}else {
				other.setPlusX(-delta * this.getSpeed());
			}
		}
	}
}
