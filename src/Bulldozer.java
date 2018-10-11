import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

public class Bulldozer extends Enemy{

	private static final String BULLDOZERFILE = "assets/bulldozer.png";
	private static final float SPEED = 0.05f;
	
	public Bulldozer(float x, float y, boolean goRight) throws SlickException {
		super(BULLDOZERFILE, x, y, SPEED, goRight);
		// TODO Auto-generated constructor stub
	}
	
	/* Bulldozer does not kill player directly, it will
	 * push player until out of screen, the player should then
	 * lose a life.
	 * @see Enemy#contactSprite(Sprite, int)
	 */
	public void contactSprite(Sprite other, int delta) {
		if (other instanceof Player) {
			if (this.isGoRight()) {
				if (other.getBoundBox().getLeft() + World.TILESIZE >= this.getBoundBox().getRight()) {
					other.setX(this.getBoundBox().getRight() + World.TILESIZE / 2);
				}
				if (other.getBoundBox().getLeft() + World.TILESIZE >= App.SCREEN_WIDTH) {
					((Player) other).loseLife();
				}
			}else {
				if (other.getBoundBox().getRight() - World.TILESIZE < this.getBoundBox().getLeft()) {
					other.setX(this.getBoundBox().getLeft() - World.TILESIZE / 2);
				}
				if (other.getBoundBox().getLeft() - World.TILESIZE <= 0) {
					((Player) other).loseLife();
				}
			}
			
		}
	}
	
	public boolean isContactWith(Sprite other) {
		BoundingBox otherBoundBox = other.getBoundBox();
		return this.getBoundBox().intersects(otherBoundBox);
	}
	
}
