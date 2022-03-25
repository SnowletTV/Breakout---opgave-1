package breakout;

import java.util.Arrays;

// TODO: implement, document
public class BreakoutState {
	/**
	* @invar Balls cannot be left of the field
	* | Arrays.stream(balls).allMatch(u -> u.getCenter().getX()-u.getDiameter()/2 >= 0)
	* @invar Balls cannot be right of the field
	* | Arrays.stream(balls).allMatch(u -> u.getCenter().getX()+u.getDiameter()/2 <= bottomRight.getX())
	* @invar Balls cannot be ontop of the field
	* | Arrays.stream(balls).allMatch(u -> u.getCenter().getY()-u.getDiameter()/2 >= 0)
	* @invar Balls cannot be below of the field
	* | Arrays.stream(balls).allMatch(u -> u.getCenter().getY()-u.getDiameter()/2 <= bottomRight.getY())
	* @invar Paddle cannot be left of the field
	* | paddle.getCenter().getX() - paddle.getSize().getX()/2 >= 0
	* @invar Paddle cannot be right of the field
	* | paddle.getCenter().getX() + paddle.getSize().getX()/2 <= 50000
	*/
	private BallState[] balls;
	private BlockState[] blocks;
	private PaddleState paddle;
	private Point bottomRight;
    
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

	/**
	* Returns this object's balls array.
	*/
	public BallState[] getBalls() {
		return balls;
	}

	/**
	* Returns this object's blocks array.
	*/
	public BlockState[] getBlocks() {
		return blocks;
	}

	/**
	* Returns this object's paddle
	*/
	public PaddleState getPaddle() {
		return paddle;
	}

	/**
	* Returns this object's bottomRight
	*/
	public Point getBottomRight() {
		return bottomRight;
	}
	
	private int checkCollision(Point topLeft1, Point bottomRight1, Point topLeft2, Point bottomRight2 ) {
		//Is hitting the top?
		if(bottomRight1.getX() >= topLeft2.getX() && topLeft1.getX() <= bottomRight2.getX() && bottomRight1.getY() >= topLeft2.getY() && topLeft1.getY() <= topLeft2.getY()) {
			//Is hitting top-right?
			if((bottomRight1.getY() >= topLeft2.getY() && topLeft1.getY() <= bottomRight2.getY()) && topLeft1.getX() <= bottomRight2.getX() && bottomRight1.getX() >= bottomRight2.getX()) {
				return 2;
			}
			return 1;
		}
		//Is hitting on the right?
		if((bottomRight1.getY() >= topLeft2.getY() && topLeft1.getY() <= bottomRight2.getY()) && topLeft1.getX() <= bottomRight2.getX() && bottomRight1.getX() >= bottomRight2.getX()) {
			//Is hitting bottom-right?
			if(bottomRight1.getX() >= topLeft2.getX() && topLeft1.getX() <= bottomRight2.getX() && bottomRight1.getY() >= topLeft2.getY() && topLeft1.getY() <= topLeft2.getY()) {
				return 4;
			}
			return 3;
		}
		//Is hitting the bottom?
		if(bottomRight1.getX() >= topLeft2.getX() && topLeft1.getX() <= bottomRight2.getX() && topLeft1.getY() <= bottomRight2.getY() && bottomRight1.getY() >= bottomRight2.getY()) {
			//Is hitting on the bottom-left?
			if(bottomRight1.getY() >= topLeft2.getY() && topLeft1.getY() <= bottomRight2.getY() && bottomRight1.getX() >= topLeft2.getX() && topLeft1.getX() <= topLeft2.getX()) {
				return 6;
			}
			return 5;
		}
		//Is hitting on the left?
		if(bottomRight1.getY() >= topLeft2.getY() && topLeft1.getY() <= bottomRight2.getY() && bottomRight1.getX() >= topLeft2.getX() && topLeft1.getX() <= topLeft2.getX()) {
			//Is hitting on the top-left?
			if(bottomRight1.getX() >= topLeft2.getX() && topLeft1.getX() <= bottomRight2.getX() && bottomRight1.getY() <= topLeft2.getY() && topLeft1.getY() >= topLeft2.getY()) {
				return 8;
			}
			return 7;
		}
		return 0;
	}
	
	/**
	* Orders the paddle to move left or right.
	* Moves the ball forward by its own velocity
	* Checks for collissions with paddle
	*/
	public void tick(int paddleDir) {
		if(paddleDir < 0) {
			movePaddleLeft();
		}
		if(paddleDir > 0) {
			movePaddleRight();
		}
		//Defining the paddles corners
		Point topLeftPaddle = new Point(paddle.getCenter().getX() - (paddle.getSize().getX()/2), paddle.getCenter().getY() - (paddle.getSize().getY()/2));
		Point bottomRightPaddle = new Point(paddle.getCenter().getX() + (paddle.getSize().getX()/2), paddle.getCenter().getY() + (paddle.getSize().getY()/2));
		for(int i = 0; i < balls.length; i++) {
			Vector velocity = balls[i].getVelocity();
			BallState[] ballsmoved = new BallState[balls.length];
			for(int k = 0; k < ballsmoved.length; k++) {
				if(k!= i) {
					ballsmoved[k] = balls[k];
				}if(k == i) {
					ballsmoved[k] = new BallState(balls[i].getCenter().plus(velocity), balls[i].getVelocity(), balls[i].getDiameter() );
				}
			}
			balls = ballsmoved;
			//Defining the balls corners
			Point topLeftBall = new Point(balls[i].getCenter().getX() - (balls[i].getDiameter()/2), balls[i].getCenter().getY() - (balls[i].getDiameter()/2));
			Point bottomRightBall = new Point(balls[i].getCenter().getX() + (balls[i].getDiameter()/2), balls[i].getCenter().getY() + (balls[i].getDiameter()/2));
			//Defining the fields corners
			Point topLeftField = new Point(0,0);
			//bottomRight already exists
			//Checks against the field borders;
			int collision = checkCollision(topLeftBall, bottomRightBall, topLeftField, bottomRight);
			//First check if there was any collision at all and whether it wasn't the bottom
			if(collision != 0 && collision != 4 && collision != 5 && collision != 6) {
				Vector newvelocity = new Vector(-velocity.getX(), velocity.getY());
				BallState[] ballsnew = new BallState[balls.length];
				//Hits the bottom or top
				if(collision == 1 || collision == 2|| collision == 8) {
					newvelocity = new Vector(velocity.getX(), -velocity.getY());
					ballsnew = new BallState[balls.length];
					for(int k = 0; k < ballsnew.length; k++) {
						if(k!= i) {
							ballsnew[k] = balls[k];
						}if(k == i) {
							ballsnew[k] = new BallState(balls[i].getCenter(), newvelocity, balls[i].getDiameter() );
						}
					}
					balls = ballsnew;
				}
				//Hits the right or left
				if(collision == 2 || collision == 3 || collision == 7 || collision == 8) {
					newvelocity = new Vector(-velocity.getX(), velocity.getY());
					ballsnew = new BallState[balls.length];
					for(int k = 0; k < ballsnew.length; k++) {
						if(k!= i) {
							ballsnew[k] = balls[k];
						}if(k == i) {
							ballsnew[k] = new BallState(balls[i].getCenter(), newvelocity, balls[i].getDiameter() );
						}
					}
					balls = ballsnew;
				}
			}
			//Hits bottomRight, bottom or bottomLeft
			if(collision == 4 || collision == 5 || collision == 6) {
				//Delete ball from array
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
			if(collision != 4 || collision != 5 || collision != 6) {
				//reset collision
				collision = 0;
				//check collision with paddle
				collision = checkCollision(topLeftBall, bottomRightBall, topLeftPaddle, bottomRightPaddle);
				//First check if there was any collision at all and no bottom(not corners) since that is impossible with the Paddle
				if(collision != 0) {
					//apply speed
					Vector newvelocity = new Vector(velocity.getX(), -velocity.getY());
	                if(paddleDir < 0) {
	                	newvelocity = new Vector(velocity.getX()-2, -velocity.getY());
	                }
	                if(paddleDir > 0) {
	                	newvelocity = new Vector(velocity.getX()+2, -velocity.getY());
	                }            
	                BallState[] ballsnew = new BallState[balls.length];
					for(int k = 0; k < ballsnew.length; k++) {
						if(k!= i) {
							ballsnew[k] = balls[k];
						}if(k == i) {
							ballsnew[k] = new BallState(balls[i].getCenter(), newvelocity, balls[i].getDiameter() );
						}
					}
					balls = ballsnew;
					//Hits the bottom or top
					if(collision == 1 || collision == 2 || collision == 4 || collision == 5 || collision == 6 || collision == 8) {
						newvelocity = new Vector(velocity.getX(), -velocity.getY());
		                ballsnew = new BallState[balls.length];
						for(int k = 0; k < ballsnew.length; k++) {
							if(k!= i) {
								ballsnew[k] = balls[k];
							}if(k == i) {
								ballsnew[k] = new BallState(balls[i].getCenter(), newvelocity, balls[i].getDiameter() );
							}
						}
						balls = ballsnew;
					}
					//Hits the right or left
					if(collision == 2 || collision == 3 || collision == 4 || collision == 6 || collision == 7 || collision == 8) {
						newvelocity = new Vector(-velocity.getX(), velocity.getY());
		                ballsnew = new BallState[balls.length];
						for(int k = 0; k < ballsnew.length; k++) {
							if(k!= i) {
								ballsnew[k] = balls[k];
							}if(k == i) {
								ballsnew[k] = new BallState(balls[i].getCenter(), newvelocity, balls[i].getDiameter() );
							}
						}
						balls = ballsnew;
					}
				}
				//Loop over every block
				for(int j = 0; j < blocks.length; j++) {
					//reset collision
					collision = 0;
					//check collision with blocks
					collision = checkCollision(topLeftBall, bottomRightBall, blocks[j].getBlockTL(), blocks[j].getBlockBR());
					if(collision != 0) {
						//Hits the bottom or top
						if(collision == 1 || collision == 2 || collision == 4 || collision == 5 || collision == 6 || collision == 8) {
							Vector newvelocity = new Vector(velocity.getX(), -velocity.getY());
			                BallState[] ballsnew = new BallState[balls.length];
							for(int k = 0; k < ballsnew.length; k++) {
								if(k!= i) {
									ballsnew[k] = balls[k];
								}if(k == i) {
									ballsnew[k] = new BallState(balls[i].getCenter(), newvelocity, balls[i].getDiameter() );
								}
							}
							balls = ballsnew;
						}
						//Hits the right or left
						if(collision == 2 || collision == 3 || collision == 4 || collision == 6 || collision == 7 || collision == 8) {
							Vector newvelocity = new Vector(-velocity.getX(), velocity.getY());
			                BallState[] ballsnew = new BallState[balls.length];
							for(int k = 0; k < ballsnew.length; k++) {
								if(k!= i) {
									ballsnew[k] = balls[k];
								}if(k == i) {
									ballsnew[k] = new BallState(balls[i].getCenter(), newvelocity, balls[i].getDiameter() );
								}
							}
							balls = ballsnew;
						}
						//Delete the block after collision
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
			}
		}
	}
	
	/**
	* Moves the paddle right by 10 when allowed by wall
	* @pre Paddle is not {@code null}.
	* | getPaddle().getCenter() != null
	* @pre Center is not {@code null}.
	* | getPaddle().getCenter() != null
	* @post Paddle is not {@code null}.
	* | getPaddle() != null
	* @post Center is not {@code null}.
	* | getPaddle().getCenter() != null
	* @post the paddle cannot be right  of the field
	* | getPaddle().getCenter().getX() + getPaddle().getSize().getX()/2 <= getBottomRight().getX()
	*/
	public void movePaddleRight() {
		if(getPaddle().getCenter().getX() + getPaddle().getSize().getX()/2 + 10 <= getBottomRight().getX()) {
			Vector vector = new Vector(10,0);
			paddle = new PaddleState(getPaddle().getCenter().plus(vector), paddle.getSize());
		}
	}

	/**
	* Moves the paddle left by 10 when allowed by wall.
	* @pre Paddle is not {@code null}.
	* | getPaddle().getCenter() != null
	* @pre Center is not {@code null}.
	* | getPaddle().getCenter() != null
	* @post Paddle is not {@code null}.
	* | getPaddle() != null
	* @post Center is not {@code null}.
	* | getPaddle().getCenter() != null
	* @post the paddle cannot be left of the field
	* | getPaddle().getCenter().getX() - getPaddle().getSize().getX()/2 >= 0
	*/
	public void movePaddleLeft() {
		if(getPaddle().getCenter().getX() - getPaddle().getSize().getX()/2 - 10 >= 0) {
			Vector vector = new Vector(-10,0);
			paddle = new PaddleState(getPaddle().getCenter().plus(vector), paddle.getSize());
		}	
	}
	
	/**
	* Returns if the game is Won by having a ball and no more blocks.
    * @post You either have Won or have Not Won.
	* | result == true || result == false
	*/
	public boolean isWon() {
		if(getBlocks().length == 0 && balls.length > 0) {
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
		if(getBalls().length == 0) {
			return true;
		}
		return false;
	}
}
