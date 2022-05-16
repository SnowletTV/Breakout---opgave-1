package breakout;
import java.util.Arrays;

import radioactivity.Alpha;
import radioactivity.Ball;
import radioactivity.NormalBall;
import utils.Circle;
import utils.Point;
import utils.Rect;
import utils.Vector;
 
/**
 * Represents the current state of a breakout game.
 *
 * @invar | getBalls() != null
 * @invar | getBlocks() != null
 * @invar | getPaddle() != null
 * @invar | getBottomRight() != null
 * @invar | Point.ORIGIN.isUpAndLeftFrom(getBottomRight())
 * @invar | Arrays.stream(getBlocks()).allMatch(b -> getField().contains(b.getLocation()))
 * @invar | getField().contains(getPaddle().getLocation())
 */
public class BreakoutState {

	private static final Vector PADDLE_VEL = new Vector(20, 0);
	public static final int MAX_BALL_REPLICATE = 5;
	public static int MAX_ELAPSED_TIME = 50;

	/**
	 * @invar | bottomRight != null
	 * @invar | Point.ORIGIN.isUpAndLeftFrom(bottomRight)
	 */
	private final Point bottomRight;
	
	
	/**
	 * TODO documentation
	 */
	private Alpha[] alphas;
	/**
	 * @invar | balls != null
	 * @representationObject
	 */
	private Ball[] balls;

	/**
	 * @invar | blocks != null
	 * @invar | Arrays.stream(blocks).allMatch(b -> getFieldInternal().contains(b.getLocation()))
	 * @representationObject
	 */
	private BlockState[] blocks;

	/**
	 * @invar | paddle != null
	 * @invar | getFieldInternal().contains(paddle.getLocation())
	 */
	private PaddleState paddle;

	private final Rect topWall;
	private final Rect rightWall;
	private final Rect leftWall;
	private final Rect[] walls;

	/**
	 * Construct a new BreakoutState with the given alphas, balls, blocks, paddle.
	 * @throws IllegalArgumentException | alphas == null
	 * @throws IllegalArgumentException | balls == null
	 * @throws IllegalArgumentException | blocks == null
	 * @throws IllegalArgumentException | bottomRight == null
	 * @throws IllegalArgumentException | paddle == null
	 * @throws IllegalArgumentException | !Point.ORIGIN.isUpAndLeftFrom(bottomRight)
	 * @throws IllegalArgumentException | !(new Rect(Point.ORIGIN,bottomRight)).contains(paddle.getLocation())
	 * @throws IllegalArgumentException | !Arrays.stream(blocks).allMatch(b -> (new Rect(Point.ORIGIN,bottomRight)).contains(b.getLocation()))
	 * @throws IllegalArgumentException | !Arrays.stream(balls).allMatch(b -> (new Rect(Point.ORIGIN,bottomRight)).contains(b.getLocation()))
	 * @post | Arrays.equals(getBalls(),balls)
	 * @post | Arrays.equals(getBlocks(),blocks)
	 * @post | getBottomRight().equals(bottomRight)
	 * @post | getPaddle().equals(paddle)
	 * TODO check for alphas = getalphas
	 */
	public BreakoutState(Alpha[] alphas, Ball[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		if( alphas == null) throw new IllegalArgumentException();
		if( balls == null) throw new IllegalArgumentException();
		if( blocks == null) throw new IllegalArgumentException();
		if( bottomRight == null) throw new IllegalArgumentException();
		if( paddle == null) throw new IllegalArgumentException();

		if(!Point.ORIGIN.isUpAndLeftFrom(bottomRight)) throw new IllegalArgumentException();
		this.bottomRight = bottomRight;
		if(!getFieldInternal().contains(paddle.getLocation())) throw new IllegalArgumentException();
		if(!Arrays.stream(blocks).allMatch(b -> getFieldInternal().contains(b.getLocation()))) throw new IllegalArgumentException();
		if(!Arrays.stream(balls).allMatch(b -> getFieldInternal().contains(b.getLocation()))) throw new IllegalArgumentException();
		
		if( alphas != null) {
			this.alphas = new Alpha[alphas.length];
			for(int i = 0; i < alphas.length; ++i) {
				this.alphas[i] = alphas[i];
			}
		}
		this.balls = new Ball[balls.length];
		for(int i = 0; i < balls.length; ++i) {
			this.balls[i] = balls[i];
		}
		this.blocks = blocks.clone();
		this.paddle = paddle;

		this.topWall = new Rect( new Point(0,-1000), new Point(bottomRight.getX(),0));
		this.rightWall = new Rect( new Point(bottomRight.getX(),0), new Point(bottomRight.getX()+1000,bottomRight.getY()));
		this.leftWall = new Rect( new Point(-1000,0), new Point(0,bottomRight.getY()));
		this.walls = new Rect[] {topWall,rightWall, leftWall };
	}
	
	/**
	 * Return the Alphas of this BreakoutState.
	 *
	 * @creates result
	 */
	public Alpha[] getAlphas() {
		Alpha[] res = new Alpha[alphas.length];
		for (int i = 0 ; i < alphas.length ; ++i) {
			res[i] = alphas[i];
		}
		return res;
	}

	/**
	 * Return the balls of this BreakoutState.
	 *
	 * @creates result
	 */
	public Ball[] getBalls() {
		Ball[] res = new Ball[balls.length];
		for (int i = 0 ; i < balls.length ; ++i) {
			res[i] = balls[i];
		}
		return res;
	}

	/**
	 * Return the blocks of this BreakoutState.
	 *
	 * @creates result
	 */
	public BlockState[] getBlocks() {
		return blocks.clone();
	}

	/**
	 * Return the paddle of this BreakoutState.
	 */
	public PaddleState getPaddle() {
		return paddle;
	}

	/**
	 * Return the point representing the bottom right corner of this BreakoutState.
	 * The top-left corner is always at Coordinate(0,0).
	 */
	public Point getBottomRight() {
		return bottomRight;
	}

	// internal version of getField which can be invoked in partially inconsistent states
	private Rect getFieldInternal() {
		return new Rect(Point.ORIGIN, bottomRight);
	}

	/**
	 * Return a rectangle representing the game field.
	 * @post | result != null
	 * @post | result.getTopLeft().equals(Point.ORIGIN)
	 * @post | result.getBottomRight().equals(getBottomRight())
	 */
	public Rect getField() {
		return getFieldInternal();
	}

	private void bounceWalls(Ball ball) {
		for( Rect wall : walls) {
			ball.hitBlock(wall, false);
		}
	}
	
	private void bounceWallsAlphas(Alpha alpha) {
		for( Rect wall : walls) {
			alpha.hitBlock(wall, isDead());
		}
	}

	private Ball removeDead(Ball ball) {
		if( ball.getLocation().getBottommostPoint().getY() > bottomRight.getY()) { return null; }
		else { return ball; }
	}

	private void clampBall(Ball b) {
		Circle loc = new Circle(getFieldInternal().constrain(b.getCenter()), b.getDiameter());
		b.setLocation(loc);
	}

	private Ball collideBallBlocks(Ball ball) {
		for(BlockState block : blocks) {
			Ball testBall = new NormalBall(ball.getLocation(), ball.getVelocity());
			testBall.hitBlock(block.getLocation(), false);
			if(!testBall.getVelocity().equals(ball.getVelocity())) {
				if(blocks.length != block.removeBlock(blocks).length) {
					ball.hitBlock(block.getLocation(), true);
				}
				else {
					ball.hitBlock(block.getLocation(), false);
				}
				ball = block.ballChange(ball);
				blocks = block.removeBlock(blocks);
				paddle = block.paddleChange(paddle);
			}
		}
		return ball;
	}

	private Ball[] collideBallPaddle(Ball[] balls, Vector paddleVel) {
		for(int i = 0; i < balls.length; ++i) {
			if(balls[i] != null) {
				Ball testBall = new NormalBall(balls[i].getLocation(), balls[i].getVelocity());
				testBall.hitBlock(paddle.getLocation(), false);
				if(!testBall.getVelocity().equals(balls[i].getVelocity())) {
					balls[i].hitBlock(paddle.getLocation(), false);
					balls = paddle.ballChange(balls, balls[i]);
					paddle = paddle.samePaddle(paddle.getCenter());				
					Alpha newAlpha = new Alpha(balls[i].getLocation(), balls[i].getVelocity().plus(new Vector(-2,-2)));
					newAlpha.getLinkedBalls().add(balls[i]);
					balls[i].getLinkedAlphas().add(newAlpha);
					alphas = newAlpha.alphaChange(alphas);
				}
			}
		}
		return balls;
	}

	/**
	 * Move all moving objects one step forward.
	 *
	 * @mutates this
	 */
	public void tick(int paddleDir, int elapsedTime) {
		for(int i = elapsedTime; i >= 0; --i) {
			stepObjects();
			bounceBallsOnWalls();
			bounceAlphasOnWalls();
			removeDeadBalls();
			bounceBallsOnBlocks();
			bounceBallsOnPaddle(paddleDir);
			clampBalls();
			balls = Arrays.stream(balls).filter(x -> x != null).toArray(Ball[]::new);
		}
	}

	private void clampBalls() {
		for(int i = 0; i < balls.length; ++i) {
			if(balls[i] != null) {
				clampBall(balls[i]);
			}
		}
	}

	private void bounceBallsOnPaddle(int paddleDir) {
		Vector paddleVel = PADDLE_VEL.scaled(paddleDir);
		balls = collideBallPaddle(balls, paddleVel);
	}

	private void bounceBallsOnBlocks() {
		for(int i = 0; i < balls.length; ++i) {
			if(balls[i] != null) {
				balls[i] = collideBallBlocks(balls[i]);
			}
		}
	}

	private void removeDeadBalls() {
		for(int i = 0; i < balls.length; ++i) {
			balls[i] = removeDead(balls[i]);
		}
	}

	private void bounceBallsOnWalls() {
		for(int i = 0; i < balls.length; ++i) {
			bounceWalls(balls[i]);
		}
	}
	
	private void bounceAlphasOnWalls() {
		for(int i = 0; i < alphas.length; ++i) {
			bounceWallsAlphas(alphas[i]);
		}
	}

	private void stepObjects() {
		for(int i = 0; i < balls.length; ++i) {
			Point newcenter = balls[i].getLocation().getCenter().plus(balls[i].getVelocity());
			balls[i].setLocation(balls[i].getLocation().withCenter(newcenter));
			balls[i] = balls[i].checkLife();
		}		
		for(int i = 0; i < alphas.length; ++i) {
			Point newcenter = alphas[i].getLocation().getCenter().plus(alphas[i].getVelocity());
			alphas[i].setLocation(alphas[i].getLocation().withCenter(newcenter));
		}
	}

	/**
	 * Move the paddle right.
	 *
	 * @pre | elapsedTime >= 0
	 * @mutates this
	 */
	public void movePaddleRight(int elapsedTime) {
		for(int i = elapsedTime; i > 0; i -= 1) {
			Point ncenter = paddle.getCenter().plus(PADDLE_VEL.scaled(1));
			paddle = paddle.samePaddle(getField().minusMargin(PaddleState.WIDTH/2,0).constrain(ncenter));
		}
	}

	/**
	 * Move the paddle left.
	 * @pre | elapsedTime >= 0
	 * @mutates this
	 */
	public void movePaddleLeft(int elapsedTime) {
		for(int i = elapsedTime; i > 0; i -= 1) {
			Point ncenter = paddle.getCenter().plus(PADDLE_VEL.scaled(-1));
			paddle = paddle.samePaddle(getField().minusMargin(PaddleState.WIDTH/2,0).constrain(ncenter));
		}
	}

	/**
	 * Return whether this BreakoutState represents a game where the player has won.
	 *
	 * @post | result == (getBlocks().length == 0 && !isDead())
	 * @inspects this
	 */
	public boolean isWon() {
		return getBlocks().length == 0 && !isDead();
	}

	/**
	 * Return whether this BreakoutState represents a game where the player is dead.
	 *
	 * @post | result == (getBalls().length == 0)
	 * @inspects this
	 */
	public boolean isDead() {
		return getBalls().length == 0;
	}
}