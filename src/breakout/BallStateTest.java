package breakout;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BallStateTest {
	Point p45;
	Point pm28;
	Vector v34;
	Vector vm18;
	BallState b453420;
	BallState bm281830;
	
	@BeforeEach
	void setUp() throws Exception {
		p45 = new Point(4,5);
		pm28 = new Point(-2,8);
		v34 = new Vector(3,4);
		vm18 = new Vector(-1,8);
		b453420 = new BallState(p45,v34,20);
		bm281830 = new BallState(pm28,vm18,-30);
		
	}

	@Test
	void testBall() {
		assertEquals(p45, b453420.getCenter());
		assertEquals(v34, b453420.getVelocity());
		assertEquals(20, b453420.getDiameter());
		assertEquals(pm28, bm281830.getCenter());
	    assertEquals(vm18, bm281830.getVelocity());
		assertEquals(-30, bm281830.getDiameter());
	}
	
	@Test
	void testEqualsObject() {
		//assertEquals(b453420 ,new BallState(p45,v34,20));
		assertNotEquals(b453420, bm281830);
		assertEquals(b453420,b453420);
		assertNotEquals(bm281830, null);
	}

}