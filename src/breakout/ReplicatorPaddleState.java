package breakout;

import java.awt.Color;
import java.util.ArrayList;

public class ReplicatorPaddleState extends PaddleState {

	private int hits = 3;
	/**
	 * @invar | center != null
	 */
	private final Point center;
	private final Color color = new Color(170,70,20);
	
	public ReplicatorPaddleState(Point center, int hits) {
		this.center = center;
		this.hits = hits;
	}
	
	public Color getColor() {
		return color;
	}

	/**
	 * Return the center point of this paddle.
	 */
	public Point getCenter() {
		return center;
	}
	
	/**
	* Returns this object's size.
	* @post | result != null
	*/
	public Vector getSize() {
	   return new Vector(WIDTH, HEIGHT);
	}

	/**
	 * Return the rectangle occupied by this paddle in the field.
	 * 
	 * @post | result != null
	 * @post | result.getTopLeft().equals(getCenter().plus(new Vector(-WIDTH/2,-HEIGHT/2)))
	 * @post | result.getBottomRight().equals(getCenter().plus(new Vector(WIDTH/2,HEIGHT/2)))
	 */
	public Rect getLocation() {
		Vector halfDiag = new Vector(-WIDTH/2,-HEIGHT/2);
		return new Rect(center.plus(halfDiag), center.plus(halfDiag.scaled(-1)));
	}
	
	/**
	* Returns this object's size.
	* @post | result != null
	* @post | hits <= 0 || result == this
	* @post | result.getCenter().equals(this.getCenter())
	*/
	public PaddleState changePaddle() {
		if(getHits() <= 0) {
			return new NormalPaddleState(this.getCenter());
		}
		return this;
	}
	
	public PaddleState samePaddle(Point center) {		
		return new ReplicatorPaddleState(center, getHits());
	}
	
	public Ball[] ballChange(Ball[] balls, Ball ball) {
		ArrayList<Ball> nballs = new ArrayList<Ball>();
		for( Ball b : balls ) {
			nballs.add(b);
		}
		if(getHits() >= 1) {
			nballs.add(new NormalBall(ball.getLocation(), ball.getVelocity().plus(new Vector(2,-2))));
			
		}
		if(getHits() >= 2) {
			nballs.add(new NormalBall(ball.getLocation(), ball.getVelocity().plus(new Vector(-2,2))));
		}
		if(getHits() >= 3) {
			nballs.add(new NormalBall(ball.getLocation(), ball.getVelocity().plus(new Vector(2,2))));
		}
		hits = getHits()-1;
		return nballs.toArray(new Ball[] {});
	}
}
