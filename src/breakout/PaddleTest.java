package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaddleTest {
	Point p11;
	PaddleState p1;

	@BeforeEach
	void setUp() throws Exception {
		p11 = new Point(1,1);
		p1 = new NormalPaddleState(p11);
	}

	@Test
	void testPaddle() {
		assertEquals(p11, p1.getCenter());
		assertEquals(new Vector(3000, 500), p1.getSize());
		assertEquals(0, p1.getHits());
	}

	@Test
	void testGetLocation() {
		assertEquals(new Point(1-PaddleState.WIDTH/2,1-PaddleState.HEIGHT/2), p1.getLocation().getTopLeft());
		assertEquals(new Point(1+PaddleState.WIDTH/2,1+PaddleState.HEIGHT/2), p1.getLocation().getBottomRight());
	}

}
