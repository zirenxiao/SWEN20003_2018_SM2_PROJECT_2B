import org.newdawn.slick.SlickException;

public class Log extends Rideable {
	private static final float SPEED = 0.15f;
	private static final String LOGFILE = "assets/longlog.png";

	public Log(float x, float y, boolean goRight) throws SlickException {
		super(LOGFILE, x, y, SPEED, goRight);
		// TODO Auto-generated constructor stub
	}

}
