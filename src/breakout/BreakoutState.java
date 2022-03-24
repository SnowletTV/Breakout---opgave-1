package breakout;

// TODO: implement, document
public class BreakoutState {
	BallState[] balls;
	BlockState[] blocks;
    PaddleState paddle;
    Point bottomRight;
    
    /**
    * Initializes this BreakoutState with the given balls, blocks, bottomright and paddle.
    * @throws IllegalArgumentException if balls array is {@code null}.
    * | balls == null
    * @throws IllegalArgumentException if blocks array is {@code null}.
    * | blocks == null
    * @throws IllegalArgumentException if paddle is {@code null}.
    * | paddle == null
    * @throws IllegalArgumentException if bottomRight is {@code null}.
    * | bottomRight == null
    * @post This object's balls array equal the given balls array.
	* | getBalls() == balls
	* @post This object's blocks array equal the given blocks array.
	* | getBlocks() == blocks
	* @post This object's bottomRight equal the given bottomRight.
	* | getBottomRight() == bottomRight
	* @post This object's paddle equal the given paddle.
	* | getPaddle() == paddle
    */
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		if (balls == null)
			throw new IllegalArgumentException("balls is null");
		this.balls = balls;
		if (blocks == null)
			throw new IllegalArgumentException("blocks is null");
        this.blocks = blocks;
        if (paddle == null)
			throw new IllegalArgumentException("paddle is null");
        this.paddle = paddle;
        if (bottomRight == null)
			throw new IllegalArgumentException("bottomRight is null");
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
                	//Blijkbaar moesten we niet versnellen... newvelocity = new Vector((int) (velocity.getX() - (2*Math.cos(Math.atan(velocity.getY()/ velocity.getX())))), (int) -(velocity.getY()+ (2*Math.sin(Math.atan2(velocity.getY(), velocity.getX())))));
                	newvelocity = new Vector(velocity.getX()-2, -velocity.getY());
                }
                if(paddleDir > 0) {
                	//Blijkbaar moesten we niet versnellen... newvelocity = new Vector((int) (velocity.getX() + (2*Math.cos(Math.atan(velocity.getY()/ velocity.getX())))), (int) -(velocity.getY()+ (2*Math.sin(Math.atan2(velocity.getY(), velocity.getX())))));
                	newvelocity = new Vector(velocity.getX()+2, -velocity.getY());
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
	
	/**
	* Moves the paddle right by 10
	* @post Center is not {@code null}.
	* | getPaddle().getCenter() != null
    * @post X coordinate of the center of the paddle must have been increased by 10
	* | getPaddle().getCenter().getX() == old(getPaddle().getCenter().getX())+10
	* @post the paddle must not be across the right wall
	* | getPaddle().getCenter().getX() + getPaddle().getSize().getX()/2 <= getBottomRight().getX()
	*/
	public void movePaddleRight() {
		if(paddle.getCenter().getX() + getPaddle().getSize().getX()/2 + 10 <= getBottomRight().getX()) {
			Vector vector = new Vector(10,0);
			paddle.center = paddle.getCenter().plus(vector);
		}
	}

	/**
	* Moves the paddle left by 10
	* @post Center is not {@code null}.
	* | getPaddle().getCenter() != null
    * @post X coordinate of the center of the paddle must have been decreased by 10
	* | getPaddle().getCenter().getX() == old(paddle.center.getX())-10
	* @post the paddle must not be across the left wall
	* | getPaddle().getCenter().getX() - getPaddle().getSize().getX()/2 >= 0
	*/
	public void movePaddleLeft() {
		if(getPaddle().getCenter().getX() - getPaddle().getSize().getX()/2 - 10 >= 0) {
			Vector vector = new Vector(-10,0);
			paddle.center = getPaddle().getCenter().plus(vector);
		}	
	}
	
	/**
	* Returns if the game is Won by having a ball and no more blocks.
    * @post You either have Won or have Not Won.
	* | result == true || result == false
	*/
	public boolean isWon() {
		if(blocks.length == 0 && balls.length > 0) {
			return true;
		}
		return false;
	}

	/**
	* Returns if the player is dead by having no more balls.
    * @post You either are Dead or are Not Dead.
    * | result == true || result == false
	*/
	public boolean isDead() {
		if(balls.length == 0) {
			return true;
		}
		return false;
	}
}
