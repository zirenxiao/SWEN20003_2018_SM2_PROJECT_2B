import org.newdawn.slick.SlickException;

public class Bulldozer extends Enemy{

	private static final String BULLDOZERFILE = "assets/bulldozer.png";
	private static final float SPEED = 0.05f;
	
	public Bulldozer(float x, float y, boolean goRight) throws SlickException {
		super(BULLDOZERFILE, x, y, SPEED, goRight);
		// TODO Auto-generated constructor stub
	}
}
