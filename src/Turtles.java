import org.newdawn.slick.SlickException;

public class Turtles extends Rideable {
	
	private static final float SPEED = 0.085f;
	private static final String LOGFILE = "assets/log.png";

	public Turtles(float x, float y, boolean goRight) throws SlickException {
		super(LOGFILE, x, y, SPEED, goRight);
		// TODO Auto-generated constructor stub
	}

}
