package breakout;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BallStateTest {
	Point p4050;
	Point p2080;
	Vector v34;
	Vector vm18;
	BallState b1;
	BallState b2;
	
	@BeforeEach
	void setUp() throws Exception {
		p4050 = new Point(40,50);
		p2080 = new Point(20,80);
		v34 = new Vector(3,4);
		vm18 = new Vector(-1,8);
		b1 = new BallState(p4050,v34,20);
		b2 = new BallState(p2080,vm18,10);
		
	}

	@Test
	void testBall() {
		assertEquals(p4050, b1.getCenter());
		assertEquals(v34, b1.getVelocity());
		assertEquals(20, b1.getDiameter());
		assertEquals(p2080, b2.getCenter());
	    assertEquals(vm18, b2.getVelocity());
		assertEquals(10, b2.getDiameter());
	}
	
	@Test
	void testEqualsObject() {
		//assertEquals(b453420 ,new BallState(p45,v34,20));
		assertNotEquals(b1, b2);
		assertEquals(b2,b2);
		assertNotEquals(b1, null);
	}

}