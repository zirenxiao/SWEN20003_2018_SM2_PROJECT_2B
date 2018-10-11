import org.newdawn.slick.SlickException;

public class Racecar extends Enemy{

	private static final String RACECARFILE = "assets/bus.png";
	private static final float SPEED = 0.5f;
	
	public Racecar(float x, float y, boolean right) throws SlickException {
		// TODO Auto-generated constructor stub
		super(RACECARFILE, x, y, SPEED, right);
	}

}
