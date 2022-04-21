package breakout;

import java.awt.Color;
import java.util.ArrayList;

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
	
	private final Color color = new Color(0,204,0);

	/**
	 * Construct a block occupying a given rectangle in the field.
	 * @pre | location != null
	 * @post | getLocation().equals(location)
	 */
	public BlockState(Rect location) {
		this.location = location;
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
	
	public BlockState[] removeBlock(BlockState[] blocks) {
		ArrayList<BlockState> nblocks = new ArrayList<BlockState>();
		for( BlockState b : blocks ) {
			if(b != this) {
				nblocks.add(b);
			}
		}
		return nblocks.toArray(new BlockState[] {});
	}
	
}

class NormalBlockState extends BlockState {

	public NormalBlockState(Rect location) {
		super(location);
	}
	
}

class SturdyBlockState extends BlockState {
	private int health;
	private final Color color = new Color(0,0,153);
	
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
	
	public Color getColor() {
		return color;
	}
	
	public BlockState[] removeBlock(BlockState[] blocks) {
		ArrayList<BlockState> nblocks = new ArrayList<BlockState>();
		for( BlockState b : blocks ) {
			this.setHealth(health-1);
			if(b != this) {
				nblocks.add(b);
			}else {
				if( health != 1) {
					nblocks.add(b);
				}		
			}
		}
		return nblocks.toArray(new BlockState[] {});
	}
		
}

class ReplicatorBlockState extends BlockState {
	private final Color color = new Color(102,255,102);

	public ReplicatorBlockState(Rect location) {
		super(location);
	}
	
	public Color getColor() {
		return color;
	}
	
}

class PowerupBallBlockState extends BlockState {
	private final Color color = new Color(255,0,0);

	public PowerupBallBlockState(Rect location) {
		super(location);
	}
	
	public Color getColor() {
		return color;
	}
	
}