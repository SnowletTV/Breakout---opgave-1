package breakout.other;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import breakout.BlockState;
import breakout.BreakoutState;
import breakout.GameMap;
import breakout.PaddleState;
import breakout.radioactivity.Alpha;
import breakout.radioactivity.Ball;
import breakout.radioactivity.NormalBall;
import breakout.utils.Circle;
import breakout.utils.Point;
import breakout.utils.Rect;
import breakout.utils.Vector;

class BreakouStateTest {

	Ball[] oneBall;
	BlockState[] oneBlock;
	Point bottomRight;
	PaddleState paddle;
	BreakoutState state1;
	BreakoutState stateWon;
	BreakoutState stateDead;
	BreakoutState stateBeforeBounceBlockTop;
	BreakoutState stateBeforeBouncePaddleLeft;
	BreakoutState stateBeforeBounceWallBottom;
	BreakoutState stateBeforeBounceWallTop;
	Vector origBallVelocity;
	BlockState bounceBlock;
	Ball ball;
	Point topLeftBallRight;
	Point bottomRightBallRight;
	Point topLeftField;
	Point bottomRightRight;
	int collision;
	private Alpha alpha;
	

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
	
	public static final String initMapBeforeBouncePaddleLeft = """
    #		       
		       
		       
		       
		       
		     o
             
		      =

		""";
	

public static final String initMapBeforeBounceWallBottom = """
##########
##########
 #########
  ########


     =
 o
""";
	
public static final String initMapBeforeBounceWallTop = """
########o
#########
##########
##########


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
		stateBeforeBounceWallBottom = GameMap.createStateFromDescription(initMapBeforeBounceWallBottom);
		stateBeforeBounceWallTop = GameMap.createStateFromDescription(initMapBeforeBounceWallTop);
		stateBeforeBouncePaddleLeft = GameMap.createStateFromDescription(initMapBeforeBouncePaddleLeft);
		
		
		
        

	}

	@Test
	void testBreakoutStateNull() {
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(null, null,oneBlock,bottomRight,paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(null, oneBall,null,bottomRight,paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(null, oneBall,oneBlock,null,paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(null, oneBall,oneBlock,bottomRight,null) );
	}
	


	

	@Test
	void testTickBounceBlockTop() {
		for(int i = 0; i < 300; ++i) {
			stateBeforeBounceBlockTop.tick(1,1);
		}
		assertEquals(1,stateBeforeBounceBlockTop.getBalls().length);
		assertEquals(2,stateBeforeBounceBlockTop.getBlocks().length);
		assertEquals(new Vector(4,-5),stateBeforeBounceBlockTop.getBalls()[0].getVelocity());
		
		
		
	}
	
	
	@Test
	void testTickBouncePaddleLeft() {
		for(int i = 0; i < 1000; ++i) {
			stateBeforeBouncePaddleLeft.tick(-1,1);
			
		}
		assertEquals(1,stateBeforeBouncePaddleLeft.getBalls().length);
		assertEquals(1,stateBeforeBouncePaddleLeft.getBlocks().length);
		assertEquals(new Vector(4,-5),stateBeforeBouncePaddleLeft.getBalls()[0].getVelocity());
	}
	
	
	@Test
	void testTickBounceWallBottom() {
		for(int i = 0; i < 2000; ++i) {
			stateBeforeBounceWallBottom.tick(1,1);
		}
		assertEquals(0,stateBeforeBounceWallBottom.getBalls().length);
		assertEquals(37,stateBeforeBounceWallBottom.getBlocks().length);
	}
	
	@Test
	void testTickBounceWallTop() {
		for(int i = 0; i < 600; ++i) {
			stateBeforeBounceWallTop.tick(1,1);
		}
		assertEquals(1,stateBeforeBounceWallTop.getBalls().length);
		assertEquals(36,stateBeforeBounceWallTop.getBlocks().length);
		assertEquals(new Vector(4,5),stateBeforeBounceWallTop.getBalls()[0].getVelocity());
	}

	@Test
	void testIsWon() {
		assertFalse(state1.isWon());
		assertTrue(stateWon.isWon());
		assertEquals(0, stateWon.getBlocks().length);
		assertFalse(stateDead.isWon());
	}

	@Test
	void testIsDead() {
		assertFalse(state1.isDead());
		assertFalse(stateWon.isDead());
		assertTrue(stateDead.isDead());
	}

}

