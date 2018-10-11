import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class LifeRideable extends Rideable{

	private ExtraLife extraLife = null;
	
	public LifeRideable(String imageSrc, float x, float y, float speed, boolean goRight) throws SlickException {
		super(imageSrc, x, y, speed, goRight);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Input input, int delta) {
		super.update(input, delta);
		if (extraLife != null) {
			extraLife.moveOnLog(this.getBoundBox());
		}
	}

}