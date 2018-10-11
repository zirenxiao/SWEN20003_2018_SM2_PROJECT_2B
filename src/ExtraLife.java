import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

public class ExtraLife extends Sprite{

	private static final String EXTRALIFE = "assets/extralife.png";
	private static final int LIFESPAN = 14000;
	private static final int MOVEINTERVAL = 2000;
	private boolean moveRightOnLog = true;
	private int moveTimePass = 0;
	private int life = 0;
	
	public ExtraLife(float x, float y) throws SlickException {
		super(EXTRALIFE, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Input input, int delta) {
		super.update(input, delta);
		moveTimePass += delta;
		life += delta;
	}
	
	public void contactSprite(Sprite other, int delta) {
		if (other instanceof Player) {
			((Player) other).loseLife();
		}
	}
	
	public void moveOnLog(BoundingBox logBoundingBox) {
		if (this.moveTimePass > MOVEINTERVAL) {
			if (this.moveRightOnLog && (this.getBoundBox().getRight() + World.TILESIZE > logBoundingBox.getRight()) ) {
				reverseDirection();
			}
			if (!this.moveRightOnLog && (this.getBoundBox().getLeft() - World.TILESIZE < logBoundingBox.getLeft()) ) {
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