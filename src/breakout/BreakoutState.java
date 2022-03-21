package breakout;

// TODO: implement, document
public class BreakoutState {
	BallState[] balls;
	BlockState[] blocks;
    PaddleState paddle;
    Point bottomRight;

	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		this.balls = balls;
        this.blocks = blocks;
        this.paddle = paddle;
        this.bottomRight = bottomRight;
	}

	public BallState[] getBalls() {
		return balls;
	}

	public BlockState[] getBlocks() {
		return blocks;
	}

	public PaddleState getPaddle() {
		return paddle;
	}

	public Point getBottomRight() {
		return bottomRight;
	}

	public void tick(int paddleDir) {
		if(paddleDir < 0) {
			Vector movement = new Vector(-10, 0);
			paddle.center = paddle.center.plus(movement);
		}
		if(paddleDir > 0) {
			Vector movement = new Vector(10, 0);
			paddle.center = paddle.center.plus(movement);
		}
		int toppaddle = paddle.center.getY() - paddle.size.getY()/2;
		int paddleleft = paddle.center.getX() - paddle.size.getX()/2;
		int paddleright = paddle.center.getX() + paddle.size.getX()/2;
		for(int i = 0; i < balls.length; i++) {
			Vector velocity = balls[i].getVelocity();
			balls[i].center = balls[i].center.plus(velocity);
			int bottomedge = balls[i].center.getY() + balls[i].diameter/2;
			int leftedge = balls[i].center.getX() - balls[i].diameter/2;
			int topedge = balls[i].center.getY() - balls[i].diameter/2;
			int rightedge = balls[i].center.getX() + balls[i].diameter/2;
			if(leftedge <= 0) {
				Vector newvelocity = new Vector(-velocity.getX(), velocity.getY());
				balls[i].velocity = newvelocity;
			}
			if(rightedge >= 50000) {
				Vector newvelocity = new Vector(-velocity.getX(), velocity.getY());
				balls[i].velocity = newvelocity;
			}
			if(topedge <= 0) {
				Vector newvelocity = new Vector(velocity.getX(), -velocity.getY());
				balls[i].velocity = newvelocity;
			}
			if(bottomedge >= toppaddle && balls[i].center.getX() <= paddleright && balls[i].center.getX()>= paddleleft) {
				Vector newvelocity = new Vector(velocity.getX(), -velocity.getY());
				balls[i].velocity = newvelocity;
			}
			if(bottomedge >= 30000) {
				BallState[] ballsnew = new BallState[balls.length-1];
				for(int j = 0; j < balls.length; j++) {
					if(j > i) {
						ballsnew[j-1] = balls[i];
					}if(j < i) {
						ballsnew[j] = balls[i];
					}
				}
				balls = ballsnew;
			}
		}
	}

	public void movePaddleRight() {
		int x = 10;
		int y = 0;
		Vector vector = new Vector(x,y);
		paddle.center.plus(vector);
	}

	public void movePaddleLeft() {
		int x = -10;
		int y = 0;
		Vector vector = new Vector(x,y);
		paddle.center.plus(vector);
	}

	public boolean isWon() {
		return false;
	}

	public boolean isDead() {
		return false;
	}
}
