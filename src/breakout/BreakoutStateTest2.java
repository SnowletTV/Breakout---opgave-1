package breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

class BreakoutStateTest2 {

private Ball[] oneBall;
private Ball[] onePowerBall;
private BlockState normalblock;
private BlockState sturdyblock;
private BlockState sturdyblock1;
private BlockState powerblock;
private BlockState replicatorblock;
private BlockState secondblock;
private BlockState newblock;
private BlockState[] normaloneBlock;
private BlockState[] sturdyoneBlock;
private BlockState[] sturdyoneBlock1;
private BlockState[] poweroneBlock;
private BlockState[] replicatoroneBlock;
private BlockState[] newoneBlock;
private Point bottomRight;
private PaddleState paddle;
private PaddleState replicatorpaddleHits3;
private PaddleState replicatorpaddleHits2;
private PaddleState replicatorpaddleHits1;
private BreakoutState stateBeforeBounceBlockNormal;
private BreakoutState stateBeforeBounceBlockSturdy;
private BreakoutState stateBeforeBounceBlockSturdy1;
private BreakoutState stateBeforeBounceBlockPower;
private BreakoutState stateBeforeBounceBlockReplicator;
private BreakoutState stateBeforeBounceBlockReplicator2;
private BreakoutState stateBeforeBounceBlockSturdyPower;
private BreakoutState stateBeforeBounceBlockNormalPower;
private BreakoutState stateBeforeBouncePaddleReplicatorHits3;
private BreakoutState stateBeforeBouncePaddleReplicatorHits2;
private BreakoutState stateBeforeBouncePaddleReplicatorHits1;
private Ball ball;
private Ball powerball;
private BreakoutFacade facade = new BreakoutFacade();

@BeforeEach
void setUp() throws Exception {
ball = facade.createNormalBall(new Point(1000, 1000), 100, new Vector(0, 5));
oneBall = new Ball[] { ball };
powerball = facade.createSuperchargedBall(new Point(1000, 1000), 100, new Vector(0, 5), 2);
onePowerBall = new Ball[] { powerball };
secondblock = facade.createNormalBlockState(new Point(0, 849), new Point(2000, 949));
normalblock = facade.createNormalBlockState(new Point(0, 1051), new Point(2000, 1200));
normaloneBlock = new BlockState[] { normalblock, secondblock };
newblock = facade.createNormalBlockState(new Point(6000, 1600), new Point(7000, 1700));
newoneBlock = new BlockState[] { newblock };
sturdyblock = facade.createSturdyBlockState(new Point(0, 1051), new Point(2000, 1200),3);
sturdyoneBlock = new BlockState[] { sturdyblock, secondblock };
sturdyblock1 = facade.createSturdyBlockState(new Point(0, 1051), new Point(2000, 1200),2);
sturdyoneBlock1 = new BlockState[] { sturdyblock1, secondblock };
powerblock = facade.createPowerupBallBlockState(new Point(0, 1051), new Point(2000, 1200));
poweroneBlock = new BlockState[] { powerblock, secondblock };
replicatorblock = facade.createReplicatorBlockState(new Point(0, 1051), new Point(2000, 1200));
replicatoroneBlock = new BlockState[] { replicatorblock, secondblock };
bottomRight = new Point(10000, 2000);
paddle = facade.createNormalPaddleState(new Point(2000, 1750));
replicatorpaddleHits3 = facade.createReplicatorPaddleState(new Point(1500, 1301), 3);
replicatorpaddleHits2 = facade.createReplicatorPaddleState(new Point(1500, 1301), 2);
replicatorpaddleHits1 = facade.createReplicatorPaddleState(new Point(1500, 1301), 1);
stateBeforeBounceBlockNormal = facade.createBreakoutState(oneBall, normaloneBlock, bottomRight, paddle);
stateBeforeBouncePaddleReplicatorHits3 = facade.createBreakoutState(oneBall, newoneBlock, bottomRight, replicatorpaddleHits3);
stateBeforeBouncePaddleReplicatorHits2 = facade.createBreakoutState(oneBall, newoneBlock, bottomRight, replicatorpaddleHits2);
stateBeforeBouncePaddleReplicatorHits1 = facade.createBreakoutState(oneBall, newoneBlock, bottomRight, replicatorpaddleHits1);
stateBeforeBounceBlockSturdy = facade.createBreakoutState(oneBall, sturdyoneBlock, bottomRight, paddle);
stateBeforeBounceBlockSturdy1 = facade.createBreakoutState(oneBall, sturdyoneBlock1, bottomRight, paddle);
stateBeforeBounceBlockPower = facade.createBreakoutState(oneBall, poweroneBlock, bottomRight, paddle);
stateBeforeBounceBlockReplicator = facade.createBreakoutState(oneBall, replicatoroneBlock, bottomRight, paddle);
stateBeforeBounceBlockReplicator2 = facade.createBreakoutState(oneBall, replicatoroneBlock, bottomRight, replicatorpaddleHits1);
stateBeforeBounceBlockSturdyPower = facade.createBreakoutState(onePowerBall, sturdyoneBlock, bottomRight, paddle);
stateBeforeBounceBlockNormalPower = facade.createBreakoutState(onePowerBall, normaloneBlock, bottomRight, paddle);
}

@Test
void testTickBounceBlockNormal() {
assertEquals(1, stateBeforeBounceBlockNormal.getBlocks()[0].getHealth());
stateBeforeBounceBlockNormal.tick(1, 1);
stateBeforeBounceBlockNormal.movePaddleLeft(1);
assertEquals(1, stateBeforeBounceBlockNormal.getBalls().length);
assertEquals(1, stateBeforeBounceBlockNormal.getBlocks().length);
assertEquals(new Vector(0, -5), stateBeforeBounceBlockNormal.getBalls()[0].getVelocity());
}

@Test
void testTickBouncePaddleReplicatorHits3() {
assertEquals(new Color(170,70,20), stateBeforeBouncePaddleReplicatorHits3.getPaddle().getColor());
stateBeforeBouncePaddleReplicatorHits3.tick(1, 1);
assertEquals(4, stateBeforeBouncePaddleReplicatorHits3.getBalls().length);
assertEquals(1, stateBeforeBouncePaddleReplicatorHits3.getBlocks().length);
assertEquals(2, stateBeforeBouncePaddleReplicatorHits3.getPaddle().getHits());
assertEquals(new Vector(2, -5), stateBeforeBouncePaddleReplicatorHits3.getBalls()[0].getVelocity());
}

@Test
void testTickBouncePaddleReplicatorHits2() {
stateBeforeBouncePaddleReplicatorHits2.tick(1, 1);
assertEquals(3, stateBeforeBouncePaddleReplicatorHits2.getBalls().length);
assertEquals(1, stateBeforeBouncePaddleReplicatorHits2.getBlocks().length);
assertEquals(1, stateBeforeBouncePaddleReplicatorHits2.getPaddle().getHits());
assertEquals(new Vector(2, -5), stateBeforeBouncePaddleReplicatorHits2.getBalls()[0].getVelocity());
}

@Test
void testTickBouncePaddleReplicatorHits1() {
stateBeforeBouncePaddleReplicatorHits1.tick(1, 1);
assertEquals(2, stateBeforeBouncePaddleReplicatorHits1.getBalls().length);
assertEquals(1, stateBeforeBouncePaddleReplicatorHits1.getBlocks().length);
assertEquals(0, stateBeforeBouncePaddleReplicatorHits1.getPaddle().getHits());
assertEquals(new Vector(2, -5), stateBeforeBouncePaddleReplicatorHits1.getBalls()[0].getVelocity());
}

@Test
void testTickBounceBlockSturdy() {
assertEquals(new Color(0,0,153), stateBeforeBounceBlockSturdy.getBlocks()[0].getColor());
stateBeforeBounceBlockSturdy.tick(1, 1);
stateBeforeBounceBlockNormal.movePaddleRight(1);
assertEquals(1, stateBeforeBounceBlockSturdy.getBalls().length);
assertEquals(2, stateBeforeBounceBlockSturdy.getBlocks().length);
assertEquals(2, stateBeforeBounceBlockSturdy.getBlocks()[0].getHealth());
assertEquals(new Color(0,0,204), stateBeforeBounceBlockSturdy.getBlocks()[0].getColor());
assertEquals(new Vector(0, -5), stateBeforeBounceBlockSturdy.getBalls()[0].getVelocity());
}

@Test
void testTickBounceBlockSturdy1() {
stateBeforeBounceBlockSturdy1.tick(1, 1);
assertEquals(1, stateBeforeBounceBlockSturdy1.getBalls().length);
assertEquals(2, stateBeforeBounceBlockSturdy1.getBlocks().length);
assertEquals(1, stateBeforeBounceBlockSturdy1.getBlocks()[0].getHealth());
assertEquals(new Color(0,0,255), stateBeforeBounceBlockSturdy1.getBlocks()[0].getColor());
assertEquals(new Vector(0, -5), stateBeforeBounceBlockSturdy1.getBalls()[0].getVelocity());
}

@Test
void testTickBounceBlockPower() {
assertEquals(1, stateBeforeBounceBlockPower.getBlocks()[0].getHealth());
stateBeforeBounceBlockPower.tick(1, 1);
assertEquals(1, stateBeforeBounceBlockPower.getBalls().length);
assertEquals(1, stateBeforeBounceBlockPower.getBlocks().length);
assertEquals(new Vector(0, -5), stateBeforeBounceBlockPower.getBalls()[0].getVelocity());
}

@Test
void testTickBounceBlockReplicator() {
assertEquals(new Color(102,255,102), stateBeforeBounceBlockReplicator.getBlocks()[0].getColor());
assertEquals(1, stateBeforeBounceBlockReplicator.getBlocks()[0].getHealth());
stateBeforeBounceBlockReplicator.tick(1, 1);
assertEquals(1, stateBeforeBounceBlockReplicator.getBalls().length);
assertEquals(1, stateBeforeBounceBlockReplicator.getBlocks().length);
assertEquals(new Vector(0, -5), stateBeforeBounceBlockReplicator.getBalls()[0].getVelocity());
}

@Test
void testTickBounceBlockReplicator2() {
assertEquals(new Color(102,255,102), stateBeforeBounceBlockReplicator2.getBlocks()[0].getColor());
assertEquals(1, stateBeforeBounceBlockReplicator2.getBlocks()[0].getHealth());
stateBeforeBounceBlockReplicator2.tick(1, 1);
assertEquals(1, stateBeforeBounceBlockReplicator2.getBalls().length);
assertEquals(1, stateBeforeBounceBlockReplicator2.getBlocks().length);
assertEquals(3, stateBeforeBounceBlockReplicator2.getPaddle().getHits());
assertEquals(new Vector(0, -5), stateBeforeBounceBlockReplicator2.getBalls()[0].getVelocity());
}

@Test
void testTickBounceBlockSturdyPower() {
stateBeforeBounceBlockSturdyPower.tick(1, 1);
assertEquals(1, stateBeforeBounceBlockSturdyPower.getBalls().length);
assertEquals(2, stateBeforeBounceBlockSturdyPower.getBlocks().length);
assertEquals(2, stateBeforeBounceBlockSturdyPower.getBlocks()[0].getHealth());
assertEquals(new Vector(0, -5), stateBeforeBounceBlockSturdyPower.getBalls()[0].getVelocity());
}

@Test
void testTickBounceBlockNormalPower() {
stateBeforeBounceBlockNormalPower.tick(1, 1);
assertEquals(1, stateBeforeBounceBlockNormalPower.getBalls().length);
assertEquals(1, stateBeforeBounceBlockNormalPower.getBlocks().length);
assertEquals(new Vector(0, 5), stateBeforeBounceBlockNormalPower.getBalls()[0].getVelocity());
stateBeforeBounceBlockNormalPower.tick(1, 1);
}

@Test
void testTickBounceBlock2() {
BlockState block2 = facade.createPowerupBallBlockState(new Point(0, 1051), new Point(2000, 1200));
BlockState block3 = facade.createNormalBlockState(new Point(0, 849), new Point(2000, 949));
BlockState[] oneBlock2 = new BlockState[] { block2, block3 };
BreakoutState stateBeforeBounceBlock2 = facade.createBreakoutState(oneBall, oneBlock2, bottomRight, paddle);
stateBeforeBounceBlock2.tick(1, 1);
assertEquals(1, stateBeforeBounceBlock2.getBalls().length);
assertEquals(1, stateBeforeBounceBlock2.getBlocks().length);
stateBeforeBounceBlock2.tick(1, 1);
stateBeforeBounceBlock2.tick(1, 1);
assertEquals(1, stateBeforeBounceBlock2.getBalls().length);
assertEquals(0, stateBeforeBounceBlock2.getBlocks().length);
assertEquals(new Vector(0, -5), stateBeforeBounceBlock2.getBalls()[0].getVelocity());
}

@Test
void testColor() {
assertEquals(new Color(153,50,50), facade.getColor(paddle));
assertEquals(new Color(255,255,255), facade.getColor(ball));
assertEquals(new Color(100,200,180), facade.getColor(powerball));
assertEquals(new Color(0,204,0), facade.getColor(normalblock));
assertEquals(new Color(255,0,0), facade.getColor(powerblock));
assertEquals(new Point(1000, 1000), facade.getCenter(ball));
}

@Test
void testPoint() {
assertEquals(new Point(1000, 1000), facade.getCenter(ball));
assertEquals(new Rect(new Point(0, 1051), new Point(2000, 1200)), facade.getLocation(normalblock));
assertEquals(100, facade.getDiameter(ball));
assertEquals(1, facade.getBalls(stateBeforeBounceBlockNormal).length);
assertEquals(new Point(500,1500), facade.getLocation(paddle).getTopLeft());
assertEquals(new Point(3500,2000), facade.getLocation(paddle).getBottomRight());

}

}
