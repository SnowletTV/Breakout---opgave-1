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
	BreakoutState stateBeforeBounceWallRight;
	BreakoutState stateBeforeBounceWallLeft;
	BreakoutState stateBeforeBounceWallBottom;
	Vector origBallVelocity;
	BlockState bounceBlock;
	BallState ball;
	Point topLeftBallRight;
	Point bottomRightBallRight;
	BallState[] BallRight;
	BallState BallRight1;
	BallState[] BallWallRight;
	BallState BallWallRight1;
	BallState[] BallWallLeft;
	BallState BallWallLeft1;
	BallState[] BallWallBottom;
	BallState BallWallBottom1;
	BallState[] BallBottom;
	BallState BallBottom1;
	BallState[] BallLeft;
	BallState BallLeft1;
	Point topLeftField;
	Point bottomRightRight;
	int collision;
	Vector velocityBottom;
	Vector velocityRight;
	Vector velocityLeft;
	Vector velocityWallRight;
	Vector velocityWallLeft;
	Vector velocityWallBottom;

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
	
	public static final String initMapBeforeBouncePaddle = """
#		       
		       
		       
		       
		       
		     
             o
		     =

		""";
	
	public static final String initMapBeforeBounceBottom = """
		       
		       
		       
	###	       
	o	       
		     

		     =

		""";
	
	public static final String initMapBeforeBounceRight = """
		       
		       
	  #	       
	###o     
		       
		     

		     =

		""";
	
	public static final String initMapBeforeBounceLeft = """
		       
		       
	#   #	       
    ### #     
	  o	       
		     

		     =

		""";
	
	public static final String initMapBeforeBounceWallRight = """
##########
##########
#########
########
         o

     =

""";
	
	public static final String initMapBeforeBounceWallLeft = """
##########
##########
 #########
  ########
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
		//stateBeforeBouncePaddle = GameMap.createStateFromDescription(initMapBeforeBouncePaddle);
		stateBeforeBounceBlockBottom = GameMap.createStateFromDescription(initMapBeforeBounceBottom);
		BallBottom = stateBeforeBounceBlockBottom.getBalls();
		BallBottom1 = BallBottom[0];
		velocityBottom = BallBottom1.getVelocity();
		velocityBottom = new Vector(5,-7);
		BallBottom[0].velocity = velocityBottom;
        stateBeforeBounceBlockRight = GameMap.createStateFromDescription(initMapBeforeBounceRight);
        BallRight = stateBeforeBounceBlockRight.getBalls();
		BallRight1 = BallRight[0];
		velocityRight = BallRight1.getVelocity();
		velocityRight = new Vector(-5,-7);
		BallRight[0].velocity = velocityRight;
		stateBeforeBounceBlockLeft = GameMap.createStateFromDescription(initMapBeforeBounceLeft);
		BallLeft = stateBeforeBounceBlockLeft.getBalls();
		BallLeft1 = BallLeft[0];
		velocityLeft = BallLeft1.getVelocity();
		velocityLeft = new Vector(5,-7);
		BallLeft[0].velocity = velocityLeft;
		stateBeforeBounceWallRight = GameMap.createStateFromDescription(initMapBeforeBounceWallRight);
		BallWallRight = stateBeforeBounceWallRight.getBalls();
		BallWallRight1 = BallWallRight[0];
		velocityWallRight = BallWallRight1.getVelocity();
		velocityWallRight = new Vector(5,-7);
		BallWallRight[0].velocity = velocityWallRight;
		stateBeforeBounceWallLeft = GameMap.createStateFromDescription(initMapBeforeBounceWallLeft);
		BallWallLeft = stateBeforeBounceWallLeft.getBalls();
		BallWallLeft1 = BallWallLeft[0];
		velocityWallLeft = BallWallLeft1.getVelocity();
		velocityWallLeft = new Vector(-5,-7);
		BallWallLeft[0].velocity = velocityWallLeft;
		stateBeforeBounceWallBottom = GameMap.createStateFromDescription(initMapBeforeBounceWallBottom);
		BallWallBottom = stateBeforeBounceWallBottom.getBalls();
		BallWallBottom1 = BallWallBottom[0];
		velocityWallBottom = BallWallBottom1.getVelocity();
		velocityWallBottom = new Vector(5,7);
		BallWallBottom[0].velocity = velocityWallBottom;
		
        //BallRight = stateBeforeBounceBlockRight.getBalls();
        //BallRight1 = BallRight[0];
        //topLeftBallRight = new Point(BallRight1.getCenter().getX() - (BallRight1.getDiameter()/2), BallRight1.getCenter().getY() - (BallRight1.getDiameter()/2));
        //bottomRightBallRight = new Point(BallRight1.getCenter().getX() + (BallRight1.getDiameter()/2), BallRight1.getCenter().getY() + (BallRight1.getDiameter()/2));
        //topLeftField = new Point(0,0);
        //bottomRightRight = stateBeforeBounceBlockRight.getBottomRight();
        //stateBeforeBounceBlockLeft = GameMap.createStateFromDescription(initMapBeforeBounceLeft);
        //collision = stateBeforeBounceBlockRight.checkCollision(topLeftBallRight, bottomRightBallRight, topLeftField, bottomRight);

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
			stateBeforeBounceBlockBottom.tick(-1);
		}
		assertEquals(1,stateBeforeBounceBlockBottom.getBalls().length);
		assertEquals(2,stateBeforeBounceBlockBottom.getBlocks().length);
		assertEquals(new Vector(5,7),stateBeforeBounceBlockBottom.getBalls()[0].getVelocity());
	}
	@Test
	void testTickBounceBlockright() {
		for(int i = 0; i < 600; ++i) {
			stateBeforeBounceBlockRight.tick(1);
			//stateBeforeBounceBlockRight.checkCollision(topLeftBallRight, bottomRightBallRight, topLeftField, bottomRight);
		}
		assertEquals(1,stateBeforeBounceBlockRight.getBalls().length);
		assertEquals(3,stateBeforeBounceBlockRight.getBlocks().length);
		assertEquals(new Vector(5,-7),stateBeforeBounceBlockRight.getBalls()[0].getVelocity());
	}
	
	@Test
	void testTickBounceBlockleft() {
		for(int i = 0; i < 600; ++i) {
			stateBeforeBounceBlockLeft.tick(1);
		}
		assertEquals(1,stateBeforeBounceBlockLeft.getBalls().length);
		assertEquals(5,stateBeforeBounceBlockLeft.getBlocks().length);
		assertEquals(new Vector(-5,-7),stateBeforeBounceBlockLeft.getBalls()[0].getVelocity());
	}
	
	@Test
	void testTickBounceWallRight() {
		for(int i = 0; i < 600; ++i) {
			stateBeforeBounceWallRight.tick(1);
		}
		assertEquals(1,stateBeforeBounceWallRight.getBalls().length);
		assertEquals(37,stateBeforeBounceWallRight.getBlocks().length);
		assertEquals(new Vector(-5,-7),stateBeforeBounceWallRight.getBalls()[0].getVelocity());
	}
	
	@Test
	void testTickBounceWallLeft() {
		for(int i = 0; i < 600; ++i) {
			stateBeforeBounceWallLeft.tick(1);
		}
		assertEquals(1,stateBeforeBounceWallLeft.getBalls().length);
		assertEquals(37,stateBeforeBounceWallLeft.getBlocks().length);
		assertEquals(new Vector(5,-7),stateBeforeBounceWallLeft.getBalls()[0].getVelocity());
	}
	
	@Test
	void testTickBounceWallBottom() {
		for(int i = 0; i < 1000; ++i) {
			stateBeforeBounceWallBottom.tick(1);
		}
		assertEquals(0,stateBeforeBounceWallBottom.getBalls().length);
		assertEquals(37,stateBeforeBounceWallBottom.getBlocks().length);
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

