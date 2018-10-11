import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

public class ExtraLife extends MovingObjects{

	private static final String EXTRALIFE = "assets/extra_lives.png";
	private static final float SPEED = 0;
	private static final int LIFESPAN = 14000;
	private static final int MOVEINTERVAL = 2000;
	private int moveTimePass = 0;
	private int life = 0;
	
	public ExtraLife(float x, float y, boolean goRight) throws SlickException {
		super(EXTRALIFE, x, y, SPEED, goRight);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Input input, int delta) {
		super.update(input, delta);
		moveTimePass += delta;
		life += delta;
	}
	
	public void moveOnLog(BoundingBox logBoundingBox) {
		if (this.moveTimePass > MOVEINTERVAL) {
			if (this.isGoRight() && (this.getBoundBox().getRight() + World.TILESIZE > logBoundingBox.getRight()) ) {
				reverseDirection();
			}
			if (!this.isGoRight() && (this.getBoundBox().getLeft() - World.TILESIZE < logBoundingBox.getLeft()) ) {
				reverseDirection();
			}
			
			if (this.isGoRight())
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
		this.setGoRight(!this.isGoRight());
	}

}