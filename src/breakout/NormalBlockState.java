package breakout;

import java.awt.Color;
import java.util.ArrayList;

public class NormalBlockState extends BlockState {
	private final Rect location;
	
	private final Color color = new Color(0,204,0);
	
	public NormalBlockState(Rect location) {
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

	public int getHealth() {
		return 1;
	}
	
	public Ball ballChange(Ball ball) {
		return ball;
	}
	
	public PaddleState paddleChange(PaddleState paddle) {
		return paddle;
	}

}