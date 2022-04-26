package breakout;

import java.awt.Color;

//No documentation required for this class
public class BreakoutFacade {
	public PaddleState createNormalPaddleState(Point center) {
		return new NormalPaddleState(center);
	}
	
	public PaddleState createReplicatorPaddleState(Point center, int hits) {
		return new ReplicatorPaddleState(center, hits);
	}

	public Ball createNormalBall(Point center, int diameter, Vector initBallVelocity) {
		return new NormalBall(new Circle(center, diameter), initBallVelocity);
	}

	public Ball createSuperchargedBall(Point center, int diameter, Vector initBallVelocity, int lifetime) {
		return new SuperchargedBall(new Circle(center, diameter), initBallVelocity, lifetime);
	}

	public BreakoutState createBreakoutState(Ball[] balls, BlockState[] blocks, Point bottomRight,
			PaddleState paddle) {
		return new BreakoutState(balls, blocks, bottomRight, paddle);
	}

	public BlockState createNormalBlockState(Point topLeft, Point bottomRight) {
		return new NormalBlockState(new Rect(topLeft, bottomRight));
	}

	public BlockState createSturdyBlockState(Point topLeft, Point bottomRight, int i) {
		return new SturdyBlockState(new Rect(topLeft, bottomRight), i);
	}

	public BlockState createReplicatorBlockState(Point topLeft, Point bottomRight) {
		return new ReplicatorBlockState(new Rect(topLeft, bottomRight));
	}

	public BlockState createPowerupBallBlockState(Point topLeft, Point bottomRight) {
		return new PowerupBallBlockState(new Rect(topLeft, bottomRight));
	}

	public Color getColor(PaddleState paddle) {
		return paddle.getColor();
	}

	public Color getColor(Ball ball) {
		return ball.getColor();
	}

	public Rect getLocation(PaddleState paddle) {
		return paddle.getLocation();
	}

	public Point getCenter(Ball ball) {
		return ball.getCenter();
	}

	public int getDiameter(Ball ball) {
		return ball.getLocation().getDiameter();
	}

	public Ball[] getBalls(BreakoutState breakoutState) {
		return breakoutState.getBalls();
	}

	public Color getColor(BlockState block) {
		return block.getColor();
	}

	public Rect getLocation(BlockState block) {
		return block.getLocation();
	}
}
