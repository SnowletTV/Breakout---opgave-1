package breakout;

import java.util.ArrayList;

import breakout.BlockState;
import breakout.BreakoutState;

public class GameMap {
	
	private static final int INIT_BALL_DIAMETER = 700;
	private static final int HEIGHT = 30000;
	private static final int WIDTH = 50000;
	private static int BLOCK_LINES = 9;
	private static int BLOCK_COLUMNS = 10;
	private static final Vector INIT_BALL_VELOCITY = new Vector(5,7);

	private static BlockState createBlock(Point topLeft) {
		Vector marginBL = new Vector(20,20);
		Vector size = new Vector(WIDTH/BLOCK_COLUMNS-70,HEIGHT/BLOCK_LINES-70);
		Point blockTL = topLeft.plus(marginBL);
		Point blockBR = blockTL.plus(size);
		// TODO: return a block with given top left (`blockTL`) and bottom right (`blockBR`) Point 
		BlockState block = new BlockState(blockTL, blockBR);
		return block;
	}
	private static PaddleState createPaddle(Point topLeft) {
		Vector size = new Vector(WIDTH/BLOCK_COLUMNS/2,HEIGHT/BLOCK_LINES/2);
		Point center = topLeft.plus(size);
		// TODO: return a paddle with given center
		PaddleState paddle = new PaddleState(center, size);
		return paddle;
	}
	private static BallState createBall(Point topLeft) {
		Vector centerD = new Vector(WIDTH/BLOCK_COLUMNS/2,HEIGHT/BLOCK_LINES/2);
		Point center = topLeft.plus(centerD);
		int diameter = INIT_BALL_DIAMETER;
		// TODO: return a ball with given `center`, `diameter` and initial velocity `INIT_BALL_VELOCITY` 
		Vector velocity = INIT_BALL_VELOCITY;
		BallState ball = new BallState(center, velocity, diameter);
		return ball;
	}
		
	/**
	 * Return the initial breakout state represented by string `description`.
	 * @pre | description != null
	 * @post | result != null
	 */
	public static BreakoutState createStateFromDescription(String description) {
		String[] lines = description.split("\n", -1);
		
		Vector unitVecRight = new Vector(WIDTH/BLOCK_COLUMNS,0);
		Vector unitVecDown = new Vector(0,HEIGHT/BLOCK_LINES);
		ArrayList<BlockState> blocks = new ArrayList<BlockState>();
		ArrayList<BallState> balls = new ArrayList<BallState>();
		PaddleState paddle = null;
		
		Point topLeft = new Point(0,0);
		assert lines.length < BLOCK_LINES;
		for(String line : lines) {
			assert line.length() < BLOCK_COLUMNS;
			Point cursor = topLeft;
			for(char c : line.toCharArray()) {
				switch(c) {
				case '#': blocks.add(createBlock(cursor)); break;
				case 'o': balls.add(createBall(cursor)); break;
				case '=': paddle = createPaddle(cursor); break;
				}
				cursor = cursor.plus(unitVecRight);
			}
			topLeft = topLeft.plus(unitVecDown);
		}
		Point bottomRight = new Point(WIDTH, HEIGHT);
		
		return new BreakoutState(balls.toArray(new BallState[] {}), blocks.toArray(new BlockState[] {}), bottomRight, paddle);
	}
}
