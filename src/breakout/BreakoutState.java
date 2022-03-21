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
			movePaddleLeft();
		}
		if(paddleDir > 0) {
			movePaddleRight();
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
			if(bottomedge >= toppaddle && bottomedge < toppaddle + velocity.getY() && topedge <= toppaddle && balls[i].center.getX() <= paddleright && balls[i].center.getX()>= paddleleft) {
                Vector newvelocity = new Vector(velocity.getX(), -velocity.getY());
                if(paddleDir < 0) {
                    newvelocity = new Vector((int) (velocity.getX() - (2*Math.cos(Math.atan(velocity.getY()/ velocity.getX())))), (int) -(velocity.getY()+ (2*Math.sin(Math.atan2(velocity.getY(), velocity.getX())))));
                }
                if(paddleDir > 0) {
                    newvelocity = new Vector((int) (velocity.getX() + (2*Math.cos(Math.atan(velocity.getY()/ velocity.getX())))), (int) -(velocity.getY()+ (2*Math.sin(Math.atan2(velocity.getY(), velocity.getX())))));
                }            
                balls[i].velocity = newvelocity;
            }
			for(int j = 0; j < blocks.length; j++) {
				int bottomblockedge = blocks[j].blockBR.getY();
				int leftblockedge = blocks[j].blockTL.getX();
				int topblockedge = blocks[j].blockTL.getY();
				int rightblockedge = blocks[j].blockBR.getX();
				if(topedge <= bottomblockedge && bottomedge >= bottomblockedge && balls[i].center.getX() <= rightblockedge && balls[i].center.getX()>= leftblockedge) {
					Vector newvelocity = new Vector(velocity.getX(), -velocity.getY());
					balls[i].velocity = newvelocity;
					BlockState[] blocksnew = new BlockState[blocks.length-1];
					for(int k = 0; k < blocksnew.length; k++) {
						if(k >= j) {
							blocksnew[k] = blocks[k+1];
						}if(k < j) {
							blocksnew[k] = blocks[k];
						}
					}
					blocks = blocksnew;
				}
				if(rightedge >= leftblockedge && leftedge <= leftblockedge && balls[i].center.getY() >= topblockedge && balls[i].center.getY()<= bottomblockedge) {
					Vector newvelocity = new Vector(-velocity.getX(), velocity.getY());
					balls[i].velocity = newvelocity;
					BlockState[] blocksnew = new BlockState[blocks.length-1];
					for(int k = 0; k < blocksnew.length; k++) {
						if(k >= j) {
							blocksnew[k] = blocks[k+1];
						}if(k < j) {
							blocksnew[k] = blocks[k];
						}
					}
					blocks = blocksnew;
				}
				if(bottomedge >= topblockedge && topedge <= topblockedge && balls[i].center.getX() <= rightblockedge && balls[i].center.getX()>= leftblockedge) {
					Vector newvelocity = new Vector(velocity.getX(), -velocity.getY());
					balls[i].velocity = newvelocity;
					BlockState[] blocksnew = new BlockState[blocks.length-1];
					for(int k = 0; k < blocksnew.length; k++) {
						if(k >= j) {
							blocksnew[k] = blocks[k+1];
						}if(k < j) {
							blocksnew[k] = blocks[k];
						}
					}
					blocks = blocksnew;
				}
				if(leftedge <= rightblockedge && rightedge >= rightblockedge && balls[i].center.getY() <= topblockedge && balls[i].center.getY()>= bottomblockedge) {
					Vector newvelocity = new Vector(-velocity.getX(), -velocity.getY());
					balls[i].velocity = newvelocity;
					BlockState[] blocksnew = new BlockState[blocks.length-1];
					for(int k = 0; k < blocksnew.length; k++) {
						if(k >= j) {
							blocksnew[k] = blocks[k+1];
						}if(k < j) {
							blocksnew[k] = blocks[k];
						}
					}
					blocks = blocksnew;
				}
				
			}
			if(bottomedge >= 30000) {
				BallState[] ballsnew = new BallState[balls.length-1];
				for(int j = 0; j < ballsnew.length; j++) {
					if(j > i) {
						ballsnew[j] = balls[j+1];
					}if(j < i) {
						ballsnew[j] = balls[j];
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
		paddle.center = paddle.center.plus(vector);
	}

	public void movePaddleLeft() {
		int x = -10;
		int y = 0;
		Vector vector = new Vector(x,y);
		paddle.center = paddle.center.plus(vector);
	}

	public boolean isWon() {
		if(blocks.length == 0) {
			return true;
		}
		return false;
	}

	public boolean isDead() {
		if(balls.length == 0) {
			return true;
		}
		return false;
	}
}
