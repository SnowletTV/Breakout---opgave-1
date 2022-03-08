package breakout;

public class BallState {
	// TODO: implement
	Point center;
	int diameter;
	Vector velocity;
	
	public Point getCenter() {		
		return center;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	
	public BallState(Point center, Vector velocity, int diameter) {
		this.center = center; 
		this.velocity = velocity;
		this.diameter = diameter;
	}
}