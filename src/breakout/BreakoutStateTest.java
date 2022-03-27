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
	BreakoutState stateBeforeBouncePaddleRight;
	BreakoutState stateBeforeBouncePaddle;
	BreakoutState stateBeforeBouncePaddleLeft;
	BreakoutState stateBeforeBouncePaddleBlockLeft;
	BreakoutState stateBeforeBouncePaddleBlockRight;
	BreakoutState stateBeforeBouncePaddleBlockBottom;
	BreakoutState stateBeforeBounceWallRight;
	BreakoutState stateBeforeBounceWallLeft;
	BreakoutState stateBeforeBounceWallBottom;
	BreakoutState stateBeforeBounceWallTop;
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
	BallState[] BallWallTop;
	BallState BallWallTop1;
	BallState[] BallBottom;
	BallState BallBottom1;
	BallState[] BallLeft;
	BallState BallLeft1;
	BallState[] BallPaddleLeft;
	BallState BallPaddleLeft1;
	BallState[] BallPaddleBlockRight;
	BallState BallPaddleBlockRight1;
	BallState[] BallPaddleBlockBottom;
	BallState BallPaddleBlockBottom1;
	Point topLeftField;
	Point bottomRightRight;
	int collision;
	Vector velocityBottom;
	Vector velocityRight;
	Vector velocityLeft;
	Vector velocityWallRight;
	Vector velocityWallLeft;
	Vector velocityWallBottom;
	Vector velocityWallTop;
	Vector velocityPaddleLeft;
	Vector velocityPaddleBlockRight;
	Vector velocityPaddleBlockBottom;

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
	
	public static final String initMapBeforeBouncePaddleRight = """
#		       
		       
		       
		       
		       
		     o
             
		    =

		""";
	
	public static final String initMapBeforeBouncePaddleLeft = """
#		       
		       
		       
		       
		       
		     o
             
		      =

		""";
	
	public static final String initMapBeforeBouncePaddleBlockLeft = """
##########
##########
#########
########
         
   o
     =

""";
	
	public static final String initMapBeforeBouncePaddleBlockRight = """
##########
##########
#########
########
         
       o
     =

""";
	
	public static final String initMapBeforeBouncePaddle = """
##########
##########
#########
########
         
     o
     =
       
""";
	
	public static final String initMapBeforeBouncePaddleBlockBottom = """
##########
##########
#########
########
         
       
     =
       o
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
		stateBeforeBouncePaddleRight = GameMap.createStateFromDescription(initMapBeforeBouncePaddleRight);
		stateBeforeBouncePaddleBlockLeft = GameMap.createStateFromDescription(initMapBeforeBouncePaddleBlockLeft);
		stateBeforeBouncePaddle = GameMap.createStateFromDescription(initMapBeforeBouncePaddle);
		stateBeforeBounceBlockBottom = GameMap.createStateFromDescription(initMapBeforeBounceBottom);
		BallBottom = stateBeforeBounceBlockBottom.getBalls();
		BallBottom1 = BallBottom[0];
		velocityBottom = BallBottom1.getVelocity();
		velocityBottom = new Vector(5,-7);
		BallBottom[0] = new BallState(BallBottom[0].getCenter(), velocityBottom, BallBottom[0].getDiameter());
        stateBeforeBounceBlockRight = GameMap.createStateFromDescription(initMapBeforeBounceRight);
        BallRight = stateBeforeBounceBlockRight.getBalls();
		BallRight1 = BallRight[0];
		velocityRight = BallRight1.getVelocity();
		velocityRight = new Vector(-5,-7);
		BallRight[0] = new BallState(BallRight[0].getCenter(), velocityRight, BallRight[0].getDiameter());
		stateBeforeBounceBlockLeft = GameMap.createStateFromDescription(initMapBeforeBounceLeft);
		BallLeft = stateBeforeBounceBlockLeft.getBalls();
		BallLeft1 = BallLeft[0];
		velocityLeft = BallLeft1.getVelocity();
		velocityLeft = new Vector(5,-7);
		BallLeft[0] = new BallState(BallLeft[0].getCenter(), velocityLeft, BallLeft[0].getDiameter());
		stateBeforeBounceWallRight = GameMap.createStateFromDescription(initMapBeforeBounceWallRight);
		BallWallRight = stateBeforeBounceWallRight.getBalls();
		BallWallRight1 = BallWallRight[0];
		velocityWallRight = BallWallRight1.getVelocity();
		velocityWallRight = new Vector(5,-7);
		BallWallRight[0] = new BallState(BallWallRight[0].getCenter(), velocityWallRight, BallWallRight[0].getDiameter());
		stateBeforeBounceWallLeft = GameMap.createStateFromDescription(initMapBeforeBounceWallLeft);
		BallWallLeft = stateBeforeBounceWallLeft.getBalls();
		BallWallLeft1 = BallWallLeft[0];
		velocityWallLeft = BallWallLeft1.getVelocity();
		velocityWallLeft = new Vector(-5,-7);
		BallWallLeft[0] = new BallState(BallWallLeft[0].getCenter(), velocityWallLeft, BallWallLeft[0].getDiameter());
		stateBeforeBounceWallBottom = GameMap.createStateFromDescription(initMapBeforeBounceWallBottom);
		BallWallBottom = stateBeforeBounceWallBottom.getBalls();
		BallWallBottom1 = BallWallBottom[0];
		velocityWallBottom = BallWallBottom1.getVelocity();
		velocityWallBottom = new Vector(5,7);
		BallWallBottom[0] = new BallState(BallWallBottom[0].getCenter(), velocityWallBottom, BallWallBottom[0].getDiameter());
		stateBeforeBounceWallTop = GameMap.createStateFromDescription(initMapBeforeBounceWallTop);
		BallWallTop = stateBeforeBounceWallTop.getBalls();
		BallWallTop1 = BallWallTop[0];
		velocityWallTop = BallWallTop1.getVelocity();
		velocityWallTop = new Vector(5,-7);
		BallWallTop[0] = new BallState(BallWallTop[0].getCenter(), velocityWallTop, BallWallTop[0].getDiameter());
		stateBeforeBouncePaddleLeft = GameMap.createStateFromDescription(initMapBeforeBouncePaddleLeft);
		BallPaddleLeft = stateBeforeBouncePaddleLeft.getBalls();
		BallPaddleLeft1 = BallPaddleLeft[0];
		velocityPaddleLeft = BallPaddleLeft1.getVelocity();
		velocityPaddleLeft = new Vector(-5,7);
		BallPaddleLeft[0] = new BallState(BallPaddleLeft[0].getCenter(), velocityPaddleLeft, BallPaddleLeft[0].getDiameter());
		stateBeforeBouncePaddleBlockRight = GameMap.createStateFromDescription(initMapBeforeBouncePaddleBlockRight);
		BallPaddleBlockRight = stateBeforeBouncePaddleBlockRight.getBalls();
		BallPaddleBlockRight1 = BallPaddleBlockRight[0];
		velocityPaddleBlockRight = BallPaddleBlockRight1.getVelocity();
		velocityPaddleBlockRight = new Vector(-5,7);
		BallPaddleBlockRight[0] = new BallState(BallPaddleBlockRight[0].getCenter(), velocityPaddleBlockRight, BallPaddleBlockRight[0].getDiameter());
		stateBeforeBouncePaddleBlockBottom = GameMap.createStateFromDescription(initMapBeforeBouncePaddleBlockBottom);
		BallPaddleBlockBottom = stateBeforeBouncePaddleBlockBottom.getBalls();
		BallPaddleBlockBottom1 = BallPaddleBlockBottom[0];
		velocityPaddleBlockBottom = BallPaddleBlockBottom1.getVelocity();
		velocityPaddleBlockBottom = new Vector(-5,-7);
		BallPaddleBlockBottom[0] = new BallState(BallPaddleBlockBottom[0].getCenter(), velocityPaddleBlockBottom, BallPaddleBlockBottom[0].getDiameter());
		
        

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
	void testTickBouncePaddleRight() {
		for(int i = 0; i < 1000; ++i) {
			stateBeforeBouncePaddleRight.tick(1);
			
		}
		assertEquals(1,stateBeforeBouncePaddleRight.getBalls().length);
		assertEquals(1,stateBeforeBouncePaddleRight.getBlocks().length);
		assertEquals(new Vector(7,-7),stateBeforeBouncePaddleRight.getBalls()[0].getVelocity());
	}
	
	@Test
	void testTickBouncePaddle() {
		for(int i = 0; i < 600; ++i) {
			stateBeforeBouncePaddle.tick(0);
			
		}
		assertEquals(1,stateBeforeBouncePaddle.getBalls().length);
		assertEquals(37,stateBeforeBouncePaddle.getBlocks().length);
		assertEquals(new Vector(5,-7),stateBeforeBouncePaddle.getBalls()[0].getVelocity());
	}
	
	@Test
	void testTickBouncePaddleLeft() {
		for(int i = 0; i < 1000; ++i) {
			stateBeforeBouncePaddleLeft.tick(-1);
			
		}
		assertEquals(1,stateBeforeBouncePaddleLeft.getBalls().length);
		assertEquals(1,stateBeforeBouncePaddleLeft.getBlocks().length);
		assertEquals(new Vector(-7,-7),stateBeforeBouncePaddleLeft.getBalls()[0].getVelocity());
	}
	
	@Test
	void testTickBouncePaddleBlockLeft() {
		for(int i = 0; i < 600; ++i) {
			stateBeforeBouncePaddleBlockLeft.tick(-1);
			
		}
		assertEquals(1,stateBeforeBouncePaddleBlockLeft.getBalls().length);
		assertEquals(37,stateBeforeBouncePaddleBlockLeft.getBlocks().length);
		assertEquals(new Vector(-19,7),stateBeforeBouncePaddleBlockLeft.getBalls()[0].getVelocity());
	}
	
	@Test
	void testTickBouncePaddleBlockRight() {
		for(int i = 0; i < 600; ++i) {
			stateBeforeBouncePaddleBlockRight.tick(1);
			
		}
		assertEquals(1,stateBeforeBouncePaddleBlockRight.getBalls().length);
		assertEquals(37,stateBeforeBouncePaddleBlockRight.getBlocks().length);
		assertEquals(new Vector(19,7),stateBeforeBouncePaddleBlockRight.getBalls()[0].getVelocity());
	}
	
	@Test
	void testTickBouncePaddleBlockBottom() {
		for(int i = 0; i < 600; ++i) {
			stateBeforeBouncePaddleBlockBottom.tick(1);
			
		}
		assertEquals(1,stateBeforeBouncePaddleBlockBottom.getBalls().length);
		assertEquals(37,stateBeforeBouncePaddleBlockBottom.getBlocks().length);
		assertEquals(new Vector(19,-7),stateBeforeBouncePaddleBlockBottom.getBalls()[0].getVelocity());
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
		for(int i = 0; i < 2000; ++i) {
			stateBeforeBounceWallBottom.tick(1);
		}
		assertEquals(0,stateBeforeBounceWallBottom.getBalls().length);
		assertEquals(37,stateBeforeBounceWallBottom.getBlocks().length);
	}
	
	@Test
	void testTickBounceWallTop() {
		for(int i = 0; i < 600; ++i) {
			stateBeforeBounceWallTop.tick(1);
		}
		assertEquals(1,stateBeforeBounceWallTop.getBalls().length);
		assertEquals(37,stateBeforeBounceWallTop.getBlocks().length);
		assertEquals(new Vector(5,7),stateBeforeBounceWallTop.getBalls()[0].getVelocity());
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

