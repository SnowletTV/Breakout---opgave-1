package breakout.radioactivity;

import java.awt.Color;

import breakout.utils.Circle;
import breakout.utils.Point;
import breakout.utils.Rect;
import breakout.utils.Vector;

/**
 * Represents the alpha particles and ball in the breakout game.
 * 
 * @mutable
 * @invar | getLocation() != null
 * @invar | getVelocity() != null
 * @invar | getECharge() != 0
 */
public abstract class AlphaBall {
	
	/**
	 * @invar | location != null
	 */
	private Circle location;
	
	/**
	 * @invar | velocity != null
	 */
	private Vector velocity;
	
	/**
	 * @invar | eCharge != 0
	 */
	private int eCharge = 1;
	
	/**
	 * Construct a new normal Alpha/Ball at a given location, with a given velocity.
	 * @pre | location != null
	 * @pre | velocity != null
	 * @post | getLocation().equals(location)
	 * @post | getVelocity().equals(velocity) 
	 */
	public AlphaBall(Circle location, Vector velocity) {
		this.location = location;
		this.velocity = velocity;
	}
	
	/**
	 * Return this alpha/ball's location.
	 * @post | result != null
	 */
	public Circle getLocation() {
		return location;
	}
	
	/**
	 * Return this alpha/ball's electric charge.
	 * @post | result != 0
	 */
	public int getECharge() {
		return eCharge;
	}
	
	/**
	 * Sets this alpha/ball's electric charge.
	 * @pre | eCharge != 0
	 * @mutates | this
	 */
	public void setECharge(int eCharge) {
		this.eCharge = eCharge;
	}
	
	/**
	 * Sets this alpha/ball's dynamically calculated electric charge.
	 * @pre | getECharge() != 0
	 * @mutates | this
	 * @post | getECharge() != 0
	 */
	abstract public void EChargeCheckAll();
	
	/**
	 * Sets this alpha/ball's location.
	 * @pre | location != null
	 * @mutates | this
	 */
	public void setLocation(Circle location) {
		this.location = location;
	}

	/**
	 * Return this alpha/ball's velocity.
	 * @post | result != null
	 */
	public Vector getVelocity() {
		return velocity;
	}
	
	/**
	 * Return this object's colour.
	 * @post | result != null
	 */
	public abstract Color getColor();
	
	/**
	 * Sets this alpha/ball's velocity.
	 * @pre | velocity != null
	 * @mutates | this
	 */
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Return this alpha/ball's center.
	 * @post | result != null
	 * @post | getLocation().getCenter().equals(result)
	 */
	public Point getCenter() {
		return getLocation().getCenter();
	}
	
	/**
	 * Sets this ball's center.
	 * @pre | center != null
	 * @mutates | this
	 */
	public void setCenter(Point center) {
		Circle newLocation = new Circle(center, location.getDiameter());
		this.location = newLocation;
	}
	
	/**
	 * Sets this alpha/ball's center.
	 * @pre | diameter != 0
	 * @mutates | this
	 */
	public void setDiameter(int diameter) {
		Circle newLocation = new Circle(location.getCenter(), diameter);
		this.location = newLocation;
	}
	
	/**
	 * Check whether this alpha/ball collides with a given `rect` and if so, return the 
	 * new velocity this alpha/ball will have after bouncing on the given rect all the while accounting for the conditions of SuperchargedBall.
	 * 
	 * @pre | rect != null
	 * @mutates this
	 * @post | ((rect.collideWith(getLocation()) != null && this.getVelocity().equals(old(this.getVelocity()).mirrorOver(rect.collideWith(old(getLocation()))))) ||
	 * | (this.getVelocity() == old(this.getVelocity()) && (rect.collideWith(getLocation()) == null || destroyed || getVelocity().product(rect.collideWith(getLocation())) <= 0))) 
	 */
	public void hitBlock(Rect rect, boolean destroyed) {
		Vector coldir = rect.collideWith(getLocation());
		if(coldir != null && getVelocity().product(coldir) > 0) {
			this.velocity = getVelocity().mirrorOver(coldir);
		}
	}
	
	
	/**
	 * Return this alpha/ball's Diameter.
	 * @post | result > 0
	 * @post | getLocation().getDiameter() == result
	 */
	public int getDiameter() {
		return getLocation().getDiameter();
	}
}
