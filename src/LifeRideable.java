import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class LifeRideable extends Rideable{

	private ArrayList<ExtraLife> extraLives = new ArrayList<ExtraLife>();
	
	public LifeRideable(String imageSrc, float x, float y, float speed, boolean goRight) throws SlickException {
		super(imageSrc, x, y, speed, goRight);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Input input, int delta) {
		super.update(input, delta);
		if (!extraLives.isEmpty()) {
			for (ExtraLife extraLife : extraLives) {
				extraLife.moveOnLog(this.getBoundBox());
			}
		}
	}
	
	public void addExtraLife(ExtraLife extraLife) {
		this.extraLives.add(extraLife);
	}
	
	public void removeExtraLife(ExtraLife extraLife) {
		this.extraLives.remove(extraLife);
	}
	
	public void adjustPosition() {
		boolean haveNewPosition = false;
		float newPosition = this.getX();
		
		if (this.getX() > App.SCREEN_WIDTH + this.getImage().getWidth()/2) {
			newPosition = -this.getImage().getWidth()/2;
			haveNewPosition = true;
		}
		if (this.getX() < -this.getImage().getWidth()/2) {
			newPosition = App.SCREEN_WIDTH + this.getImage().getWidth()/2;
			haveNewPosition = true;
		}
		
		if(haveNewPosition) {
			if (!extraLives.isEmpty()) {
				float distance = newPosition - this.getX();
				for (ExtraLife extraLife : extraLives)
					extraLife.resetPosition(distance);
			}
			this.setX(newPosition);
		}
	}
	
	public void contactSprite(Sprite other, int delta) {
		super.contactSprite(other, delta);
		if (other instanceof ExtraLife) {
			if (this.isGoRight()) {
				other.setPlusX(delta * this.getSpeed());
			}else {
				other.setPlusX(-delta * this.getSpeed());
			}
		}
	}

	public ArrayList<ExtraLife> getExtraLives() {
		return extraLives;
	}

}