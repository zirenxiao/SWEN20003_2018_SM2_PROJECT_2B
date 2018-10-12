import org.newdawn.slick.SlickException;

public class Goal extends Sprite {

	private static final String PLAYERFILE = "assets/frog.png";
	private boolean found = false;
	
	public Goal(float x, float y) throws SlickException {
		super(PLAYERFILE, x, y);
		// TODO Auto-generated constructor stub
	}
	
	/* Only if we found the goal, this would be rendered
	 * @see Sprite#render()
	 */
	public void render() {
		if (found) {
			super.render();
		}
	}
	
	/* When the player contact the goal, it will set to
	 * founded
	 * @see Sprite#contactSprite(Sprite, int)
	 */
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

	/** Whether the goal has been founded
	 * @return
	 */
	public boolean isFound() {
		return found;
	}
	
	

}
