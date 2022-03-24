package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlockStateTest {
	Point p4050;
	Point p140150;
	Point p2030;
	Point p200300;
	BlockState bl1;
	BlockState bl2;
	
	@BeforeEach
	void setUp() throws Exception {
		p4050 = new Point(40,50);
		p140150 = new Point(140,150);
		p2030 = new Point(20,30);
		p200300 = new Point(200,300);
		bl1 = new BlockState(p4050,p140150);
		bl2 = new BlockState(p2030,p200300);
	}

	@Test
	void testBlock() {
		assertEquals(p4050, bl1.getBlockTL());
		assertEquals(p140150, bl1.getBlockBR());
		assertEquals(p2030, bl2.getBlockTL());
		assertEquals(p200300, bl2.getBlockBR());
	}
	
	@Test
	void testEqualsObject() {
		//assertEquals(bl1 ,new BlockState(p4050,p140150));
		assertNotEquals(bl1, bl2);
		assertEquals(bl2, bl2);
		assertNotEquals(bl1, null);
	}

}