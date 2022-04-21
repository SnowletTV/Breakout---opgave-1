package breakout;

import java.awt.Color;

/**
 * Represents the state of a ball in the breakout game.
 * 
 * @mutable
 * @invar | getLocation() != null
 * @invar | getVelocity() != null
 */
public class Ball {
	
	private Circle location;
	private Vector velocity;
	private final Color color = new Color(255,255,255);
	
	/**
	 * Construct a new ball at a given `location`, with a given `velocity`.
	 * 
	 * @pre | location != null
	 * @pre | velocity != null
	 * @post | getLocation() == location
	 * @post | getVelocity().equals(velocity) 
	 */
	public Ball(Circle location, Vector velocity) {
		this.location = location;
		this.velocity = velocity;
	}
	
	public Color getColor() {
		return color;
	}
	
	/**
	 * Return this ball's location.
	 */
	public Circle getLocation() {
		return location;
	}
	
	/**
	 * Sets this ball's location.
	 */
	public void setLocation(Circle location) {
		this.location = location;
	}

	/**
	 * Return this ball's velocity.
	 */
	public Vector getVelocity() {
		return velocity;
	}
	
	/**
	 * Sets this ball's velocity.
	 */
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	
	public Ball checkLife() {
		return this;
	}

	/**
	 * Check whether this ball collides with a given `rect` and if so, return the 
	 * new velocity this ball will have after bouncing on the given rect.
	 * 
	 * @pre | rect != null
	 * @post | (rect.collideWith(getLocation()) == null && result == null) ||
	 *       | (getVelocity().product(rect.collideWith(getLocation())) <= 0 && result == null) || 
	 *       | (result.equals(getVelocity().mirrorOver(rect.collideWith(getLocation()))))
	 */
	public Vector bounceOn(Rect rect) {
		Vector coldir = rect.collideWith(location);
		if(coldir != null && velocity.product(coldir) > 0) {
			return velocity.mirrorOver(coldir);
		}
		return null;
	}
	
	public Vector hitBlock(Rect rect, boolean destroyed) {
		Vector coldir = rect.collideWith(location);
		if(coldir != null && velocity.product(coldir) > 0) {
			return velocity.mirrorOver(coldir);
		}
		return null;
	}

	/**
	 * Return this ball's center.
	 * 
	 * @post | getLocation().getCenter().equals(result)
	 */
	public Point getCenter() {
		return getLocation().getCenter();
	}
	
	/**
	 * Sets this ball's center.
	 */
	public void setCenter(Point center) {
		Circle newLocation = new Circle(location.getCenter(), location.getDiameter());
		this.location = newLocation;
	}
	
	/**
	 * Return this ball's Diameter.
	 * 
	 * @post | getLocation().getDiameter() == result
	 */
	public int getDiameter() {
		return getLocation().getDiameter();
	}
	
	public Ball changeBall() {		
		return new SuperchargedBall(this.getLocation(), this.getVelocity(), 10000);
	}
}

class NormalBall extends Ball {

	public NormalBall(Circle location, Vector velocity) {
		super(location, velocity);
	}
	
	public Ball changeBall() {		
		return new SuperchargedBall(this.getLocation(), this.getVelocity(), 10000);
	}
	
	
}

class SuperchargedBall extends Ball {
	private int lifetime;
	private final Color color = new Color(100,200,180);

	public SuperchargedBall(Circle location, Vector velocity, int lifetime) {
		super(location, velocity);
		this.setLifetime(lifetime);
	}
	
	public Ball checkLife() {
		setLifetime(lifetime - 1);
		return changeBall();
	}

	public Color getColor() {
		return color;
	}
	
	public Vector hitBlock(Rect rect, boolean destroyed) {
		Vector coldir = rect.collideWith(getLocation());
		if(coldir != null && getVelocity().product(coldir) > 0 && destroyed == false) {
			return getVelocity().mirrorOver(coldir);
		}
		if(coldir != null && destroyed == true) {
			return getVelocity();
		}
		return null;
	}

	/**
	 * Return this ball's lifetime.
	 */
	public int getLifetime() {
		return lifetime;
	}

	/**
	 * Sets this ball's velocity.
	 */
	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}
	
	public Ball changeBall() {
		if(lifetime <= 0) {
			return new NormalBall(this.getLocation(), this.getVelocity());
		}
		return this;
	}	
}