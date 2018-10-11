import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

public class ExtraLife extends Sprite {

	private static final String EXTRALIFE = "assets/extralife.png";
	private static final int LIFESPAN = 14000;
	private static final int MOVEINTERVAL = 2000;
	private boolean moveRightOnLog = true;
	private boolean isDestroyed = false;
	private int moveTimePass = 0;
	private int life = 0;

	public ExtraLife(float x, float y) throws SlickException {
		super(EXTRALIFE, x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Add the move time and life of extra life when updated
	 */
	public void update(Input input, int delta) {
		super.update(input, delta);
		moveTimePass += delta;
		life += delta;
	}

	/**
	 * add a life for player when contacted and extra life is not destroyed, 
	 * destroy the extra life once it has contacted with a player
	 */
	public void contactSprite(Sprite other, int delta) {
		if (!isDestroyed) {
			if (other instanceof Player) {
				try {
					((Player) other).addLife();
					isDestroyed = true;
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * only render extra lives that are not destroyed
	 */
	public void render() {
		if (!isDestroyed)
			super.render();
	}

	public void moveOnLog(BoundingBox logBoundingBox) {
		if (this.moveTimePass > MOVEINTERVAL) {
			if (this.moveRightOnLog && (this.getBoundBox().getRight() + 
					World.TILESIZE > logBoundingBox.getRight())) {
				reverseDirection();
			}
			if (!this.moveRightOnLog && (this.getBoundBox().getLeft() - 
					World.TILESIZE < logBoundingBox.getLeft())) {
				reverseDirection();
			}

			if (this.moveRightOnLog)
				this.setPlusX(World.TILESIZE);
			else
				this.setPlusX(-World.TILESIZE);
			this.moveTimePass = 0;
		}
	}

	public boolean isDead() {
		return (life >= LIFESPAN);
	}

	public void reverseDirection() {
		moveRightOnLog = !moveRightOnLog;
	}

	public void resetPosition(float distance) {
		this.setPlusX(distance);
	}

}