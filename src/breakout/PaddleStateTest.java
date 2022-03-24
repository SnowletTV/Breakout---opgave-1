package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaddleStateTest {
	Point p45;
	Point p28;
	Vector v34;
	Vector v18;
	PaddleState pd1;
	PaddleState pd2;
	
	@BeforeEach
	void setUp() throws Exception {
		p45 = new Point(4,5);
		p28 = new Point(2,8);
		v34 = new Vector(3,4);
		v18 = new Vector(1,8);
		pd1 = new PaddleState(p45,v34);
		pd2 = new PaddleState(p28,v18);
	}

	@Test
	void testPaddle() {
		assertEquals(p45, pd1.getCenter());
		assertEquals(v34, pd1.getSize());
		assertEquals(p28, pd2.getCenter());
	    assertEquals(v18, pd2.getSize());
	}
	
	@Test
	void testEqualsObject() {
		//assertEquals(pd1 ,new PaddleState(p45,v34));
		assertNotEquals(pd1, pd2);
		assertEquals(pd2,pd2);
		assertNotEquals(pd1, null);
	}

}
