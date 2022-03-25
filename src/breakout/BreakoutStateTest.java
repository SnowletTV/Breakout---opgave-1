package breakout;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BreakouStateTest {

	BallState[] oneBall;
	BlockState[] oneBlock;
	Point bottomRight;
	PaddleState paddle;
	BreakoutState state1;
	BreakoutState stateWon;
	BreakoutState stateDead;
	BreakoutState stateBeforeBounceBlockTop;
	BreakoutState stateBeforeBounceBlockBottom;
	BreakoutState stateBeforeBounceBlockRight;
	BreakoutState stateBeforeBounceBlockLeft;
	BreakoutState stateBeforeBouncePaddle;
	Vector origBallVelocity;
	BlockState bounceBlock;
	BallState ball;

	public static final String initMap1 = """
#		       
		       
		       
		       
		       
		     o

		     =

		""";
	public static final String initMapWon = """
		       
		       
		       
		       
		       
		     o

		     =

		""";
	public static final String initMapDead = """
#		       
		       
		       
		       
		       
		     

		     =

		""";
	
	public static final String initMapBeforeBounce = """
		       
		       
	o	       
	###	       
		       
		     

		     =

		""";
	
	public static final String initMapBeforeBounce2 = """
#		       
		       
		       
		       
		       
		     
             o
		     =

		""";
	
	public static final String initMapBeforeBounce3 = """
		       
		       
		       
	###	       
	o	       
		     

		     =

		""";
	
	public static final String initMapBeforeBounce4 = """
		       
		       
		       
	###o     
		       
		     

		     =

		""";
	
	public static final String initMapBeforeBounce5 = """
		       
		       
		       
   o###     
		       
		     

		     =

		""";
	
	


	@BeforeEach
	void setUp() throws Exception {
		state1 = GameMap.createStateFromDescription(initMap1);
		oneBall = state1.getBalls();
		ball = oneBall[0];
		origBallVelocity = ball.getVelocity();
		oneBlock = state1.getBlocks();
		bottomRight = state1.getBottomRight();
		paddle = state1.getPaddle();
		stateWon = GameMap.createStateFromDescription(initMapWon);
		stateDead = GameMap.createStateFromDescription(initMapDead);
		stateBeforeBounceBlockTop = GameMap.createStateFromDescription(initMapBeforeBounce);
		//stateBeforeBouncePaddle = GameMap.createStateFromDescription(initMapBeforeBounce2);
		stateBeforeBounceBlockBottom = GameMap.createStateFromDescription(initMapBeforeBounce3);
        stateBeforeBounceBlockRight = GameMap.createStateFromDescription(initMapBeforeBounce4);
        stateBeforeBounceBlockLeft = GameMap.createStateFromDescription(initMapBeforeBounce5);
	}

	@Test
	void testBreakoutStateNull() {
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(null,oneBlock,bottomRight,paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(oneBall,null,bottomRight,paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(oneBall,oneBlock,null,paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(oneBall,oneBlock,bottomRight,null) );
	}
	
	@Test
	void testBreakoutStateNormal() {
		BreakoutState state = new BreakoutState(oneBall,oneBlock,bottomRight,paddle);
		assertTrue(Arrays.equals(oneBall, state.getBalls()));
		assertTrue(Arrays.equals(oneBlock, state.getBlocks()));
		assertEquals(bottomRight,state.getBottomRight());
		assertEquals(paddle, state.getPaddle());
	}

	@Test
	void testTickNormal() {
		state1.tick(0);
		assertEquals(1,state1.getBalls().length);
		BallState b = state1.getBalls()[0];
		assertEquals(origBallVelocity,b.getVelocity());
	}

	@Test
	void testTickBounceBlockTop() {
		for(int i = 0; i < 300; ++i) {
			stateBeforeBounceBlockTop.tick(1);
		}
		assertEquals(1,stateBeforeBounceBlockTop.getBalls().length);
		assertEquals(2,stateBeforeBounceBlockTop.getBlocks().length);
		assertEquals(new Vector(5,-7),stateBeforeBounceBlockTop.getBalls()[0].getVelocity());
	}
	
	@Test
	void testTickBouncePaddle() {
		for(int i = 0; i < 300; ++i) {
			stateBeforeBouncePaddle.tick(1);
			
		}
		assertEquals(1,stateBeforeBouncePaddle.getBalls().length);
		assertEquals(1,stateBeforeBouncePaddle.getBlocks().length);
		assertEquals(new Vector(5,-7),stateBeforeBouncePaddle.getBalls()[0].getVelocity());
	}
	
	@Test
	void testTickBounceBlockBottom() {
		for(int i = 0; i < 300; ++i) {
			stateBeforeBounceBlockBottom.tick(1);
		}
		assertEquals(1,stateBeforeBounceBlockBottom.getBalls().length);
		assertEquals(3,stateBeforeBounceBlockBottom.getBlocks().length);
		assertEquals(new Vector(5,7),stateBeforeBounceBlockBottom.getBalls()[0].getVelocity());
	}
	@Test
	void testTickBounceBlockright() {
		for(int i = 0; i < 300; ++i) {
			stateBeforeBounceBlockRight.tick(1);
			
		}
		assertEquals(1,stateBeforeBounceBlockRight.getBalls().length);
		assertEquals(3,stateBeforeBounceBlockRight.getBlocks().length);
		assertEquals(new Vector(5,7),stateBeforeBounceBlockRight.getBalls()[0].getVelocity());
	}
	
	@Test
	void testTickBounceBlockleft() {
		for(int i = 0; i < 300; ++i) {
			stateBeforeBounceBlockLeft.tick(1);
		}
		assertEquals(1,stateBeforeBounceBlockLeft.getBalls().length);
		assertEquals(3,stateBeforeBounceBlockLeft.getBlocks().length);
		assertEquals(new Vector(5,7),stateBeforeBounceBlockLeft.getBalls()[0].getVelocity());
	}

	@Test
	void testIsWon() {
		assertFalse(state1.isWon());
		assertTrue(stateWon.isWon());
		assertFalse(stateDead.isWon());
	}

	@Test
	void testIsDead() {
		assertFalse(state1.isDead());
		assertFalse(stateWon.isDead());
		assertTrue(stateDead.isDead());
	}

}

