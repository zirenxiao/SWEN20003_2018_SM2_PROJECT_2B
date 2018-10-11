import org.newdawn.slick.SlickException;

public class Goal extends Sprite {

	private static final String PLAYERFILE = "assets/frog.png";
	private boolean found = false;
	
	public Goal(float x, float y) throws SlickException {
		super(PLAYERFILE, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void render() {
		if (found) {
			super.render();
		}
	}
	
	public void contactSprite(Sprite other, int delta) {
		if (other instanceof Player) {
			if (found) {
				((Player) other).loseLife();
			}else{
				found = true;
				((Player) other).resetPosition();
			}
			
		}
	}

	public boolean isFound() {
		return found;
	}
	
	

}
