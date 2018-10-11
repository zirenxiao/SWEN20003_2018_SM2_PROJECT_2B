import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

public class Bulldozer extends Enemy{

	private static final String BULLDOZERFILE = "assets/bulldozer.png";
	private static final float SPEED = 0.05f;
	
	public Bulldozer(float x, float y, boolean goRight) throws SlickException {
		super(BULLDOZERFILE, x, y, SPEED, goRight);
		// TODO Auto-generated constructor stub
	}
	
	public void contactSprite(Sprite other, int delta) {
		if (other instanceof Player) {
			if (this.isGoRight() && other.getBoundBox().getLeft() + World.TILESIZE >= this.getBoundBox().getRight()) {
				other.setPlusX(delta * this.getSpeed());
			}else if (other.getBoundBox().getRight() - World.TILESIZE < this.getBoundBox().getLeft()) {
				other.setPlusX(-delta * this.getSpeed());
			}else {
				System.out.println("other");
				other.setY(this.getY());
			}
		}
	}
	
	public boolean isContactWith(Sprite other) {
//		System.out.println("this:"+this.getY()); 
//		System.out.println("other:"+other.getY()); 
		if (this.getY() != other.getY())
			return false;
		BoundingBox otherBoundBox = other.getBoundBox();
		return this.getBoundBox().intersects(otherBoundBox);
	}
}
