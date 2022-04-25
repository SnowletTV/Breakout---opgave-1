package breakout;

import java.awt.Color;
import java.util.ArrayList;

public class SturdyBlockState extends BlockState {
	private final Rect location;
	
	private int health;
	private Color color;
	
	public SturdyBlockState(Rect location, int health) {
		this.location = location;
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
		return setDynamicColor(this);
	}
	
	private Color setDynamicColor(SturdyBlockState block) {
		if(block.getHealth() == 3) {
			return new Color(0,0,153);
		}
		if(block.getHealth() == 2) {
			return new Color(0,0,204);
		}
		return new Color(0,0,255);
	}
	
	/**
	 * Return the rectangle occupied by this block in the field.
	 */
	public Rect getLocation() {
		return location;
	}
	
	
	public BlockState[] removeBlock(BlockState[] blocks) {
		ArrayList<BlockState> nblocks = new ArrayList<BlockState>();
		for( BlockState b : blocks ) {
			if(b != this) {
				nblocks.add(b);
			}else {
				if( health != 1) {
					b = new SturdyBlockState(b.getLocation(), b.getHealth()-1);
					nblocks.add(b);
				}		
			}
		}
		return nblocks.toArray(new BlockState[] {});
	}

	public Ball ballChange(Ball ball) {
		return ball;
	}
	
	public PaddleState paddleChange(PaddleState paddle) {
		return paddle;
	}

}
