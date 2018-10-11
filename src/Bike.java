import org.newdawn.slick.SlickException;

public class Bike extends Enemy{

	private static final String BIKEFILE = "assets/bike.png";
	private static final float SPEED = 0.2f;
	private static final float XBOUND1 = 20;
	private static final float XBOUND2 = 1000;
	
	public Bike(float x, float y, boolean goRight) throws SlickException {
		super(BIKEFILE, x, y, SPEED, goRight);
		// TODO Auto-generated constructor stub
	}

}
