package breakout;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents the state of a paddle in the breakout game.
 *
 * @immutable
 * @invar | getCenter() != null
 */
public class PaddleState {
	
	public static final int HEIGHT = 500;
	public static final int WIDTH = 3000;
	/**
	 * @invar | center != null
	 */
	private final Point center;
	private final Color color = new Color(153,50,50);

	/**
	 * Construct a paddle located around a given center in the field.
	 * @pre | center != null
	 * @post | getCenter().equals(center)
	 */
	public PaddleState(Point center) {
		this.center = center;
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
	
	public PaddleState changePaddle() {		
		return new ReplicatorPaddleState(this.getCenter(), 3);
	}
	
	public PaddleState samePaddle(Point center) {		
		return new NormalPaddleState(center);
	}
	
	public Ball[] ballChange(Ball[] balls, Ball ball) {
		return balls;
	}

}
class NormalPaddleState extends PaddleState	{

	public NormalPaddleState(Point center) {
		super(center);
	}
	
}

class ReplicatorPaddleState extends PaddleState	{
	private int hits = 3;
	private final Color color = new Color(170,70,20);
	
	public ReplicatorPaddleState(Point center, int hits) {
		super(center);
		this.hits = hits;
	}
	
	public Color getColor() {
		return color;
	}
	
	public PaddleState changePaddle() {
		if(hits <= 0) {
			return new NormalPaddleState(this.getCenter());
		}
		return this;
	}
	
	public PaddleState samePaddle(Point center) {		
		return new ReplicatorPaddleState(center, hits);
	}
	
	public Ball[] ballChange(Ball[] balls, Ball ball) {
		ArrayList<Ball> nballs = new ArrayList<Ball>();
		for( Ball b : balls ) {
			nballs.add(b);
		}
		if(hits == 3) {
			nballs.add(new Ball(ball.getLocation(), ball.getVelocity().plus(new Vector(2,-2))));
			nballs.add(new Ball(ball.getLocation(), ball.getVelocity().plus(new Vector(-2,2))));
			nballs.add(new Ball(ball.getLocation(), ball.getVelocity().plus(new Vector(2,2))));
		}
		if(hits == 2) {
			nballs.add(new Ball(ball.getLocation(), ball.getVelocity().plus(new Vector(2,-2))));
			nballs.add(new Ball(ball.getLocation(), ball.getVelocity().plus(new Vector(-2,2))));
		}
		if(hits == 1) {
			nballs.add(new Ball(ball.getLocation(), ball.getVelocity().plus(new Vector(2,-2))));
		}
		hits = hits-1;
		return nballs.toArray(new Ball[] {});
	}
	
}
