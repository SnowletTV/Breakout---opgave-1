package other;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import breakout.BlockState;
import breakout.BreakoutFacade;
import breakout.BreakoutState;
import breakout.PaddleState;
import breakout.radioactivity.Alpha;
import breakout.radioactivity.Ball;
import breakout.radioactivity.NormalBall;
import breakout.utils.Circle;
import breakout.utils.Point;
import breakout.utils.Rect;
import breakout.utils.Vector;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

class BreakoutStateTest2 {

    private Ball[] oneBall;
    private Ball[] oneBallRight;
    private Ball[] onePowerBall;
    private Ball[] oneDeadBall;
    private Alpha[] oneAlpha;
    private Alpha[] oneAlphaRight;
    private Alpha[] oneTopAlpha;
    private Alpha[] oneDeadAlpha;
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
    private BreakoutState stateBeforeBounceBottom;
    private BreakoutState stateBeforeBounceTop;
    private BreakoutState stateBeforeBounceRight;
    private BreakoutState stateBeforeBounceBottomAlpha;
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
    private BreakoutState stateBeforeBouncePaddleReplicatorHits1Power;
    private BreakoutState stateBeforeBouncePaddleReplicatorHits1Alpha;
    private Ball ball;
    private Alpha alpha;
    private Alpha alpharight;
    private Alpha topalpha;
    private Alpha deadalpha;
    private Ball powerball;
    private Ball deadball;
    private Ball ballright;
    private Rect rect;
    private BreakoutFacade facade = new BreakoutFacade();

    @BeforeEach
    void setUp() throws Exception {
        ball = facade.createNormalBall(new Point(1000, 1000), 100, new Vector(0, 5));
        oneBall = new Ball[] { ball };
        ballright = facade.createNormalBall(new Point(9951, 1000), 100, new Vector(5, 0));
        oneBallRight = new Ball[] { ballright };
        deadball = facade.createNormalBall(new Point(1000, 1949), 100, new Vector(0, 5));
        oneDeadBall = new Ball[] { deadball };
        deadalpha = facade.createAlpha(new Point(1000, 1949), 100, new Vector(0, 5));
        oneDeadAlpha = new Alpha[] { deadalpha };
        alpha = facade.createAlpha(new Point(1000, 1000), 100, new Vector(0, 5));
        oneAlpha = new Alpha[] { alpha };
        alpharight = facade.createAlpha(new Point(9951, 1000), 100, new Vector(0, 5));
        oneAlphaRight = new Alpha[] { alpha };
        topalpha = facade.createAlpha(new Point(1000, 51), 100, new Vector(0, -5));
        oneTopAlpha = new Alpha[] { topalpha };
        facade.addLink(ball, alpha);
        facade.addLink(deadball, alpha);
        facade.addLink(ball, deadalpha);
        facade.addLink(ball, topalpha);
        rect = new Rect(new Point(10,10), new Point(20,20));
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
        stateBeforeBounceBottom = facade.createBreakoutState(oneDeadBall, newoneBlock, bottomRight, paddle);
        stateBeforeBounceTop = facade.createBreakoutState(oneTopAlpha, oneBall, newoneBlock, bottomRight, paddle);
        stateBeforeBounceBottomAlpha = facade.createBreakoutState(oneDeadAlpha, oneBall, newoneBlock, bottomRight, paddle);
        stateBeforeBounceBlockNormal = facade.createBreakoutState(oneBall, normaloneBlock, bottomRight, paddle);
        stateBeforeBouncePaddleReplicatorHits3 = facade.createBreakoutState(oneBall, newoneBlock, bottomRight, replicatorpaddleHits3);
        stateBeforeBouncePaddleReplicatorHits2 = facade.createBreakoutState(oneBall, newoneBlock, bottomRight, replicatorpaddleHits2);
        stateBeforeBouncePaddleReplicatorHits1 = facade.createBreakoutState(oneBall, newoneBlock, bottomRight, replicatorpaddleHits1);
        stateBeforeBouncePaddleReplicatorHits1Power = facade.createBreakoutState(onePowerBall, newoneBlock, bottomRight, replicatorpaddleHits1);
        stateBeforeBouncePaddleReplicatorHits1Alpha = facade.createBreakoutState(oneAlpha, oneDeadBall, newoneBlock, bottomRight, replicatorpaddleHits1);
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
    void testTickBounceBottom() {
        stateBeforeBounceBottom.tick(1, 1);
        assertEquals(0, stateBeforeBounceBottom.getBalls().length);
        assertEquals(1, stateBeforeBounceBottom.getBlocks().length);
        assertEquals(0, stateBeforeBounceBottom.getAlphas().length);
    }

    @Test
    void testTickBounceTop() {
        stateBeforeBounceTop.tick(1, 1);
        assertEquals(1, stateBeforeBounceTop.getBalls().length);
        assertEquals(1, stateBeforeBounceTop.getBlocks().length);
        assertEquals(1, stateBeforeBounceTop.getAlphas().length);
        assertEquals(new Vector(0, -5), stateBeforeBounceTop.getBalls()[0].getVelocity());
    }


    @Test
    void testTickBounceBottomAlpha() {
        stateBeforeBounceBottomAlpha.tick(1, 1);
        assertEquals(1, stateBeforeBounceBottomAlpha.getBalls().length);
        assertEquals(1, stateBeforeBounceBottomAlpha.getBlocks().length);
        assertEquals(0, stateBeforeBounceBottomAlpha.getAlphas().length);
        assertEquals(0, stateBeforeBounceBottomAlpha.getBalls()[0].getLinkedAlphas().size());
    }

    @Test
    void testTickBouncePaddleReplicatorHits3() {
        assertEquals(new Color(170,70,20), stateBeforeBouncePaddleReplicatorHits3.getPaddle().getColor());
        stateBeforeBouncePaddleReplicatorHits3.tick(1, 1);
        assertEquals(4, stateBeforeBouncePaddleReplicatorHits3.getBalls().length);
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits3.getBlocks().length);
        assertEquals(2, stateBeforeBouncePaddleReplicatorHits3.getPaddle().getHits());
        assertEquals(new Vector(0, -5), stateBeforeBouncePaddleReplicatorHits3.getBalls()[0].getVelocity());
    }

    @Test
    void testTickBouncePaddleReplicatorHits2() {
        stateBeforeBouncePaddleReplicatorHits2.tick(1, 1);
        assertEquals(3, stateBeforeBouncePaddleReplicatorHits2.getBalls().length);
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits2.getBlocks().length);
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits2.getPaddle().getHits());
        assertEquals(new Vector(0, -5), stateBeforeBouncePaddleReplicatorHits2.getBalls()[0].getVelocity());
    }

    @Test
    void testTickBouncePaddleReplicatorHits1() {
        stateBeforeBouncePaddleReplicatorHits1.tick(1, 1);
        assertEquals(2, stateBeforeBouncePaddleReplicatorHits1.getBalls().length);
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1.getAlphas().length);
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1.getAlphas()[0].getECharge());
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1.getBlocks().length);
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1.getAlphas()[0].getLinkedBalls().size());
        assertEquals(0, stateBeforeBouncePaddleReplicatorHits1.getPaddle().getHits());
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1.getBalls()[0].getLinkedAlphas().size());
        assertEquals(-1, stateBeforeBouncePaddleReplicatorHits1.getBalls()[0].getECharge());
        assertEquals(new Vector(0, -5), stateBeforeBouncePaddleReplicatorHits1.getBalls()[0].getVelocity());
        assertEquals(new Vector(-2, -7), stateBeforeBouncePaddleReplicatorHits1.getAlphas()[0].getVelocity());
    }

    @Test
    void testTickBouncePaddleReplicatorHits1Power() {
        for(int i = 0; i < 300; ++i) {
            stateBeforeBouncePaddleReplicatorHits1Power.tick(1,1);
        }
        assertEquals(5, stateBeforeBouncePaddleReplicatorHits1Power.getBalls().length);
        assertEquals(9, stateBeforeBouncePaddleReplicatorHits1Power.getAlphas().length);
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1Power.getAlphas()[0].getECharge());
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1Power.getBlocks().length);
        assertEquals(3, stateBeforeBouncePaddleReplicatorHits1Power.getAlphas()[0].getLinkedBalls().size());
        assertEquals(0, stateBeforeBouncePaddleReplicatorHits1Power.getPaddle().getHits());
        assertEquals(4, stateBeforeBouncePaddleReplicatorHits1Power.getBalls()[0].getLinkedAlphas().size());
        assertEquals(-3, stateBeforeBouncePaddleReplicatorHits1Power.getBalls()[0].getECharge());
        assertEquals(new Vector(-14, -1), stateBeforeBouncePaddleReplicatorHits1Power.getBalls()[0].getVelocity());
        assertEquals(new Vector(2, -7), stateBeforeBouncePaddleReplicatorHits1Power.getAlphas()[0].getVelocity());
    }

    @Test
    void testTickBouncePaddleReplicatorHits1Alpha() {
        stateBeforeBouncePaddleReplicatorHits1Alpha.tick(1, 1);
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1Alpha.getBalls().length);
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1Alpha.getAlphas().length);
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1Alpha.getAlphas()[0].getECharge());
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1Alpha.getBlocks().length);
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1Alpha.getAlphas()[0].getLinkedBalls().size());
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1Alpha.getPaddle().getHits());
        assertEquals(1, stateBeforeBouncePaddleReplicatorHits1Alpha.getBalls()[0].getLinkedAlphas().size());
        assertEquals(-1, stateBeforeBouncePaddleReplicatorHits1Alpha.getBalls()[0].getECharge());
        assertEquals(new Vector(-2, -7), stateBeforeBouncePaddleReplicatorHits1Alpha.getBalls()[0].getVelocity());
        assertEquals(new Vector(0, -5), stateBeforeBouncePaddleReplicatorHits1Alpha.getAlphas()[0].getVelocity());
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
        assertEquals(new Color(170,170,170), facade.getColor(alpha));
        assertEquals(new Color(0,204,0), facade.getColor(normalblock));
        assertEquals(new Color(255,0,0), facade.getColor(powerblock));
        assertEquals(new Point(1000, 1000), facade.getCenter(ball));
    }

    @Test
    void testPoint() {
        assertEquals(new Point(1000, 1000), facade.getCenter(ball));
        assertEquals(new Point(1000, 1000), facade.getCenter(alpha));
        assertEquals(new Rect(new Point(0, 1051), new Point(2000, 1200)), facade.getLocation(normalblock));
        assertEquals(100, facade.getDiameter(ball));
        assertEquals(100, facade.getDiameter(alpha));
        assertEquals(1, facade.getBalls(stateBeforeBounceBlockNormal).length);
        assertEquals(2, facade.getBlocksLen(stateBeforeBounceBlockNormal));
        assertEquals(3, facade.getAlphas(ball).size());
        assertEquals(2, facade.getBalls(alpha).size());
        assertEquals(2, facade.getEcharge(ball));
        assertEquals(new Point(10,10), facade.getRectTL(rect));
        assertEquals(new Point(20,20), facade.getRectBR(rect));
        assertEquals(new Point(0, 1051), facade.getBlockTL(normalblock));
        assertEquals(new Point(2000, 1200), facade.getBlockBR(normalblock));
        assertEquals(new Point(500,1500), facade.getLocation(paddle).getTopLeft());
        assertEquals(new Point(3500,2000), facade.getLocation(paddle).getBottomRight());
        facade.movePaddleLeft(stateBeforeBounceBlockNormal, 1);
        facade.movePaddleRight(stateBeforeBounceBlockNormal, 1);
        facade.removeLink(ball, alpha);
        facade.setLocation(ball, bottomRight, 100);
        facade.setLocation(alpha, bottomRight, 100);
        facade.setSpeed(ball, new Vector(0, 5));
        facade.setSpeed(alpha, new Vector(0, 5));
        facade.hitBlock(ball, rect, false);
        alpha.EChargeCheckAll();
        assertEquals(2, facade.getAlphas(ball).size());
        assertEquals(1, facade.getBalls(alpha).size());
        assertTrue(facade.collidesWith(ballright, new Rect(new Point(0,0), bottomRight)));
        assertTrue(facade.collidesWith(alpharight, new Rect(new Point(0,0), bottomRight)));


    }

}
