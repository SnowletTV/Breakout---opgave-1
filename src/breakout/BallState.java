package breakout;

public class BallState {
	// TODO: implement
	public Point center;
	public Vector velocity;
	public int diameter;

	public BallState(Point center, Vector velocity, int diameter) {
		this.center = center;
		this.velocity = velocity;
		this.diameter = diameter;
	}

	public Point getCenter() {
		return center;
	}

	public Vector getVelocity() {
		return velocity;
	}
}
