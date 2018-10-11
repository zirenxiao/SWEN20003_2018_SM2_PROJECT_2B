import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

public class Rideable extends MovingObjects {

	public Rideable(String imageSrc, float x, float y, float speed, boolean goRight) throws SlickException {
		super(imageSrc, x, y, speed, goRight);
		// TODO Auto-generated constructor stub
	}
	
	/* Whether the other object is on the current object
	 * @see Sprite#isContactWith(Sprite)
	 */
	public boolean isContactWith(Sprite other) {
		BoundingBox otherBoundBox = other.getBoundBox();
		return this.getBoundBox().isOnOther(otherBoundBox);
	}

	/* Action of the player ride on a rideable object
	 * @see Sprite#contactSprite(Sprite, int)
	 */
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
