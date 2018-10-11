import org.newdawn.slick.SlickException;

public class Water extends Sprite {
	private static final String WATERFILE = "assets/water.png";

	public Water(float x, float y) throws SlickException {
		super(WATERFILE, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void contactSprite(Sprite other, int delta) {
		boolean isOnOther = false;
		
		if (other instanceof Player) {
			for (Sprite other2:World.getInstance().getSprites()) {
				if (other2 instanceof Rideable) {
					if (other2.isContactWith(other)) {
						isOnOther = true;
					}
					
				}
			}
			if (!isOnOther) {
//				((Player) other).loseLife();
			}
			
		}
	}

}
