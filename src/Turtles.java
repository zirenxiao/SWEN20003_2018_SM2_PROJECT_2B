import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Turtles extends Rideable {
	
	private static final float SPEED = 0.085f;
	private static final int SHOWTIME = 7000;
	private static final int INVISTIME = 2000;
	private static final String LOGFILE = "assets/turtles.png";
	private int showTimer = 0;
	private int invisTimer = 0;
	private boolean vis = true;

	public Turtles(float x, float y, boolean goRight) throws SlickException {
		super(LOGFILE, x, y, SPEED, goRight);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Input input, int delta) {
		// How can this one method deal with different types of sprites?
		super.update(input, delta);
		if (showTimer >= SHOWTIME) {
			showTimer = 0;
			vis = false;
		}else if (showTimer < SHOWTIME && vis){
			showTimer = showTimer + delta;
		}
		
		if (!vis && invisTimer >= INVISTIME) {
			invisTimer = 0;
			vis = true;
		}else if (!vis){
			invisTimer = invisTimer + delta;
		}
	}
	
	public void render() {
		if (vis) {
			super.render();
		}
	}
	
	public void contactSprite(Sprite other, int delta) {
		if (vis) {
			if (other instanceof Player) {
				if (this.isGoRight()) {
					other.setPlusX(delta * this.getSpeed());
				}else {
					other.setPlusX(-delta * this.getSpeed());
				}
			}
		}else {
			if (other instanceof Player) {
				((Player) other).loseLife();
			}
		}
		
	}

}
