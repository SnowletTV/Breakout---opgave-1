package breakout;

import java.awt.Color;

/**
 * Represents the state of a block in the breakout game.
 *
 * @immutable
 * @invar | getLocation() != null
 */
public class BlockState {

	/**
	 * @invar | location != null
	 */
	private final Rect location;
	
	private final Color color;

	/**
	 * Construct a block occupying a given rectangle in the field.
	 * @pre | location != null
	 * @post | getLocation().equals(location)
	 */
	public BlockState(Rect location) {
		this.location = location;
		this.color = new Color(255,102,102);
	}

	/**
	 * Return the rectangle occupied by this block in the field.
	 */
	public Rect getLocation() {
		return location;
	}
	
	public Color getColor() {
		return color;
	}
	
}

class NormalBlockState extends BlockState {

	public NormalBlockState(Rect location) {
		super(location);
	}
	
}

class SturdyBlockState extends BlockState {
	private int health;
	
	public SturdyBlockState(Rect location, int health) {
		super(location);
		this.setHealth(health);
	}

	/**
	 * Return this blocks health.
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets this blocks health.
	 */
	public void setHealth(int health) {
		this.health = health;
	}
		
}

class ReplicatorBlockState extends BlockState {

	public ReplicatorBlockState(Rect location) {
		super(location);
	}
	
}

class PowerupBallBlockState extends BlockState {

	public PowerupBallBlockState(Rect location) {
		super(location);
	}
	
}