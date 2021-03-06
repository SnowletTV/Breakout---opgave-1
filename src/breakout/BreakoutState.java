package breakout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import breakout.radioactivity.Alpha;
import breakout.radioactivity.Ball;
import breakout.radioactivity.NormalBall;
import breakout.radioactivity.SuperchargedBall;
import breakout.utils.Circle;
import breakout.utils.Point;
import breakout.utils.Rect;
import breakout.utils.Vector;
 
/**
 * Represents the current state of a breakout game.
 * @invar | getAlphas() != null
 * @invar | getBalls() != null
 * @invar | getBlocks() != null
 * @invar | getPaddle() != null
 * @invar | getBottomRight() != null
 * @invar | Point.ORIGIN.isUpAndLeftFrom(getBottomRight())
 * @invar | Arrays.stream(getBalls()).allMatch(b -> b.getLinkedAlphas().stream().allMatch(c -> c != null))
 * @invar | Arrays.stream(getAlphas()).allMatch(b -> b.getLinkedBalls().stream().allMatch(c -> c != null))
 * @invar | Arrays.stream(getAlphas()).allMatch(b -> getField().contains(b.getCenter()))
 * @invar | Arrays.stream(getBalls()).allMatch(b -> getField().contains(b.getCenter()))
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
	 * @invar | alphas != null
	 * @invar | Arrays.stream(alphas).allMatch(b -> b.getLinkedBalls().stream().allMatch(c -> c != null))
	 * @invar | Arrays.stream(alphas).allMatch(b -> getFieldInternal().contains(b.getCenter()))
	 * @invar | !Arrays.stream(alphas).anyMatch(b -> Arrays.stream(alphas).anyMatch(a -> a.equalContent(b) && !a.equals(b)))
	 * @invar | Arrays.stream(alphas).allMatch(b -> b.getLinkedBalls().stream().allMatch(c -> Arrays.stream(balls).anyMatch(d -> ((Ball) c).equals(d))))
	 * @representationObject
	 */
	private Alpha[] alphas;
	/**
	 * @invar | balls != null
	 * @invar | Arrays.stream(balls).allMatch(b -> b.getLinkedAlphas().stream().allMatch(c -> c != null))
	 * @invar | Arrays.stream(balls).allMatch(b -> getFieldInternal().contains(b.getCenter()))
	 * @invar | !Arrays.stream(balls).anyMatch(b -> Arrays.stream(balls).anyMatch(a -> a.equalContent(b) && !a.equals(b)))
	 * @invar | Arrays.stream(balls).allMatch(b -> b.getLinkedAlphas().stream().allMatch(c -> Arrays.stream(alphas).anyMatch(d -> ((Alpha) c).equals(d))))
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
	 * @throws IllegalArgumentException | !Arrays.stream(alphas).allMatch(b -> (new Rect(Point.ORIGIN,bottomRight)).contains(b.getLocation()))
	 * @throws IllegalArgumentException | !Arrays.stream(blocks).allMatch(b -> (new Rect(Point.ORIGIN,bottomRight)).contains(b.getLocation()))
	 * @throws IllegalArgumentException | !Arrays.stream(balls).allMatch(b -> (new Rect(Point.ORIGIN,bottomRight)).contains(b.getLocation()))
	 * @post | Arrays.stream(getAlphas()).allMatch(b -> Arrays.stream(alphas).anyMatch(a -> a.equalContent(b)))
	 * @post | Arrays.equals(getBlocks(),blocks)
	 * @post | getBottomRight().equals(bottomRight)
	 * @post | getPaddle().equals(paddle)
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
		if(!Arrays.stream(alphas).allMatch(b -> getFieldInternal().contains(b.getLocation()))) throw new IllegalArgumentException();
		if(!Arrays.stream(blocks).allMatch(b -> getFieldInternal().contains(b.getLocation()))) throw new IllegalArgumentException();
		if(!Arrays.stream(balls).allMatch(b -> getFieldInternal().contains(b.getLocation()))) throw new IllegalArgumentException();		
		this.alphas = new Alpha[alphas.length];
		this.balls = new Ball[balls.length];
		for(int i = 0; i < balls.length; ++i) {
			this.balls[i] = new SuperchargedBall(balls[i].getLocation(), balls[i].getVelocity(), balls[i].getLifetime());
			this.balls[i] = this.balls[i].checkLife();
			this.balls[i].setLifetime(this.balls[i].getLifetime()+1);
			for(int j = 0; j < alphas.length; j++) {
				if(balls[i].getLinkedAlphas().contains(alphas[j])) {
					this.alphas[j] = new Alpha(alphas[j].getLocation(), alphas[j].getVelocity());
					this.alphas[j].linkTo(this.balls[i]);
					this.balls[i].linkTo(this.alphas[j]);
				}
			}
			this.balls[i].EChargeCheckAll();
			
		}
		for(int j = 0; j < alphas.length; j++) {
			boolean isPresent = false;
			for(int i = 0; i < balls.length; ++i) {
				if(balls[i].getLinkedAlphas().contains(alphas[j])) {
					isPresent = true;
				}	
			}
			if(!isPresent) {
				this.alphas[j] = new Alpha(alphas[j].getLocation(), alphas[j].getVelocity());
			}
		}
		this.blocks = blocks.clone();
		this.paddle = paddle;

		this.topWall = new Rect( new Point(0,-1000), new Point(bottomRight.getX(),0));
		this.rightWall = new Rect( new Point(bottomRight.getX(),0), new Point(bottomRight.getX()+1000,bottomRight.getY()));
		this.leftWall = new Rect( new Point(-1000,0), new Point(0,bottomRight.getY()));
		this.walls = new Rect[] {topWall,rightWall, leftWall };
	}
	
	/**
	 * Return the alphas of this BreakoutState.
	 * @creates result
	 */
	public Alpha[] getAlphas() {
		Alpha[] res = new Alpha[alphas.length];
		Ball[] resBalls = new Ball[balls.length];
		for(int i = 0; i < balls.length; ++i) {
			resBalls[i] = new SuperchargedBall(balls[i].getLocation(), balls[i].getVelocity(), balls[i].getLifetime());
			resBalls[i] = this.balls[i].checkLife();
			resBalls[i].setLifetime(this.balls[i].getLifetime()+1);
			for(int j = 0; j < alphas.length; j++) {
				if(balls[i].getLinkedAlphas().contains(alphas[j])) {
					res[j] = new Alpha(alphas[j].getLocation(), alphas[j].getVelocity());
					res[j].linkTo(this.balls[i]);
					resBalls[i].linkTo(this.alphas[j]);
				}
			}
			resBalls[i].EChargeCheckAll();	
		}
		for(int j = 0; j < alphas.length; j++) {
			boolean isPresent = false;
			for(int i = 0; i < balls.length; ++i) {
				if(balls[i].getLinkedAlphas().contains(alphas[j])) {
					isPresent = true;
				}	
			}
			if(!isPresent) {
				res[j] = new Alpha(alphas[j].getLocation(), alphas[j].getVelocity());
			}
		}
		return res;
	}

	/**
	 * Return the balls of this BreakoutState.
	 * @creates result
	 */
	public Ball[] getBalls() {
		Alpha[] res = new Alpha[alphas.length];
		Ball[] resBalls = new Ball[balls.length];
		for(int i = 0; i < balls.length; ++i) {
			resBalls[i] = new SuperchargedBall(balls[i].getLocation(), balls[i].getVelocity(), balls[i].getLifetime());
			resBalls[i] = this.balls[i].checkLife();
			resBalls[i].setLifetime(this.balls[i].getLifetime()+1);
			for(int j = 0; j < alphas.length; j++) {
				if(balls[i].getLinkedAlphas().contains(alphas[j])) {
					res[j] = new Alpha(alphas[j].getLocation(), alphas[j].getVelocity());
					res[j].linkTo(this.balls[i]);
					resBalls[i].linkTo(this.alphas[j]);
				}
			}
			resBalls[i].EChargeCheckAll();
			
		}
		return resBalls;
	}

	/**
	 * Return the blocks of this BreakoutState.
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
			Alpha testAlpha = new Alpha(alpha.getLocation(), alpha.getVelocity());
			testAlpha.hitBlock(wall, isDead());
			if(alpha.getVelocity() != testAlpha.getVelocity()) {
				alpha.hitBlock(wall, isDead());
				for (Ball ball : alpha.getLinkedBalls()) {
					ball.setVelocity(Vector.magnetSpeed(alpha.getLocation().getCenter(), ball.getLocation().getCenter(), ball.getECharge(), ball.getVelocity()));
				}
			}
		}
	}
	
	private boolean checkRemoveDeadBall(Ball ball) {
		if( ball.getLocation().getBottommostPoint().getY() > bottomRight.getY()) { 
			return true; 
		}
		return false;
	}
	
	private boolean checkRemoveDeadAlpha(Alpha alpha) {
		if( alpha.getLocation().getBottommostPoint().getY() > bottomRight.getY()) { 
			return true; 
		}
		return false;
	}

	private void clampAlpha(Alpha a) {
		Circle loca = new Circle(getFieldInternal().constrain(a.getLocation().getCenter()), a.getDiameter());
		a.setLocation(loca);
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
		for(int i = 0; i < balls.length; i++) {
			if(balls[i] != null) {
				Ball testBall = new NormalBall(balls[i].getLocation(), balls[i].getVelocity());
				testBall.hitBlock(paddle.getLocation(), false);
				if(!testBall.getVelocity().equals(balls[i].getVelocity())) {
					balls[i].hitBlock(paddle.getLocation(), false);
					balls = paddle.ballChange(balls, balls[i]);
					paddle = paddle.samePaddle(paddle.getCenter());				
					Alpha newAlpha = new Alpha(balls[i].getLocation(), balls[i].getVelocity().plus(new Vector(-2,-2)));
					newAlpha.linkTo(balls[i]);
					balls[i].linkTo(newAlpha);
					alphas = newAlpha.alphaChange(alphas);
					balls[i].EChargeCheckAll();
				}
			}
		}
		return balls;
	}
	
	private Alpha[] collideAlphaPaddle(Alpha[] alphas, Vector paddleVel) {
		for(int i = 0; i < alphas.length; i++) {
			if(alphas[i] != null) {
				Alpha testAlpha = new Alpha(alphas[i].getLocation(), alphas[i].getVelocity());
				testAlpha.hitBlock(paddle.getLocation(), false);
				if(!testAlpha.getVelocity().equals(alphas[i].getVelocity())) {
					alphas[i].hitBlock(paddle.getLocation(), false);			
					Ball newBall = new NormalBall(alphas[i].getLocation(), alphas[i].getVelocity().plus(new Vector(-2,-2)));
					newBall.linkTo(alphas[i]);
					newBall.EChargeCheckAll();
					alphas[i].linkTo(newBall);
					balls = newBall.ballChange(balls);
				}
			}
		}
		return alphas;
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
			removeDeadAlphas();
			bounceBallsOnBlocks();
			bounceBallsOnPaddle(paddleDir);
			bounceAlphasOnPaddle(paddleDir);
			clampObjects();
			balls = Arrays.stream(balls).filter(x -> x != null).toArray(Ball[]::new);
			alphas = Arrays.stream(alphas).filter(x -> x != null).toArray(Alpha[]::new);
		}
	}

	private void clampObjects() {
		for(int i = 0; i < balls.length; ++i) {
			if(balls[i] != null) {
				clampBall(balls[i]);
			}
		}
		for(int i = 0; i < alphas.length; ++i) {
			if(alphas[i] != null) {
				clampAlpha(alphas[i]);
			}
		}
	}

	private void bounceBallsOnPaddle(int paddleDir) {
		Vector paddleVel = PADDLE_VEL.scaled(paddleDir);
		alphas = collideAlphaPaddle(alphas, paddleVel);
	}
	
	private void bounceAlphasOnPaddle(int paddleDir) {
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
			if(checkRemoveDeadBall(balls[i])) {
				for (Alpha alpha : balls[i].getLinkedAlphas()) {
					alpha.unlinkFrom(balls[i]);
				}
				balls[i].EChargeCheckAll();
				balls[i] = null;
			}
		}
		ArrayList<Ball> nballs = new ArrayList<Ball>();
		for( Ball b : balls ) {
			if(b != null) {
				nballs.add(b);
			}
		}
		balls = nballs.toArray(new Ball[] {});
	}
	
	private void removeDeadAlphas() {
		for(int i = 0; i < alphas.length; ++i) {
			if(checkRemoveDeadAlpha(alphas[i])) {
				for (Ball ball : alphas[i].getLinkedBalls()) {
					ball.unlinkFrom(alphas[i]);
					ball.EChargeCheckAll();
				}
				alphas[i].getLinkedBalls().clear();
				alphas[i] = null;
			}
		}
		ArrayList<Alpha> nalphas = new ArrayList<Alpha>();
		for( Alpha a : alphas ) {
			if(a != null) {
				nalphas.add(a);
			}
		}
		alphas = nalphas.toArray(new Alpha[] {});
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
		return balls.length == 0;
	}
}