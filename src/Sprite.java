import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

public class Sprite {
	private Image image;
	private float x;
	private float y;
	private BoundingBox bb;
	
	/** Construct a sprite
	 * @param imageSrc The source file of an image
	 * @param x The initial coordinate of X
	 * @param y The initial coordinate of Y
	 * @throws SlickException
	 */
	public Sprite(String imageSrc, float x, float y) throws SlickException {
		// Why would the constructor need a path to an image, and a coordinate?
		image = new Image(imageSrc);
		bb = new BoundingBox(image, x, y);
		this.x = x;
		this.y = y;
	}
	
	public void update(Input input, int delta) {
		// How can this one method deal with different types of sprites?
	}
	
	/** Default render a sprite
	 * 
	 */
	public void render() {
		// This should be pretty simple.
		image.drawCentered(x, y);
	}
	
	public void contactSprite(Sprite other, int delta) {
		// Should be called when one sprite makes contact with another. 
	}

	/** Get current X coordinate
	 * @return
	 */
	public float getX() {
		return x;
	}

	/** Set current X plus a value
	 * @param x
	 */
	public void setPlusX(float x) {
		this.x += x;
		this.updateBoundBox();
	}

	/** Get current Y coordinate
	 * @return
	 */
	public float getY() {
		return y;
	}

	/** Set current Y plus a value
	 * @param y
	 */
	public void setPlusY(float y) {
		this.y += y;
		this.updateBoundBox();
	}

	/** Set current X
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
		this.updateBoundBox();
	}

	/** Set current Y
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
		this.updateBoundBox();
	}
	
	/** Update bounding box status
	 * 
	 */
	private void updateBoundBox() {
		bb.setX(this.x);
		bb.setY(this.y);
	}
	
	/** Get current bouding box
	 * @return
	 */
	public BoundingBox getBoundBox() {
		return bb;
	}

	/** Whether this object contacts with another object
	 * @param other
	 * @return
	 */
	public boolean isContactWith(Sprite other) {
		BoundingBox otherBoundBox = other.getBoundBox();
		return bb.intersects(otherBoundBox);
	}

	public Image getImage() {
		return image;
	}
	
	
	
}
