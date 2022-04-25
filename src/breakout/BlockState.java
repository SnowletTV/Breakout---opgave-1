package breakout;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents the state of a block in the breakout game.
 *
 * @immutable
 */
abstract public class BlockState {

	/**
	 * Return the rectangle occupied by this block in the field.
	 */
	abstract Rect getLocation();
	
	abstract Color getColor();
	
	abstract BlockState[] removeBlock(BlockState[] blocks);

	abstract int getHealth();
	
	abstract Ball ballChange(Ball ball);
	
	abstract PaddleState paddleChange(PaddleState paddle);
	
}