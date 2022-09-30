package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import static org.junit.jupiter.api.Assertions.*;

public class ObstacleTest {
    @Test
    void testObstacleDimensions() {
        Obstacle obstacle = new SquareObstacle(20,20);
        assertEquals(20, obstacle.getBottomLeftX());
        assertEquals(20, obstacle.getBottomLeftY());
        assertEquals(5, obstacle.getSize());
    }

    @Test
    void testBlockPosition(){
        Obstacle obstacle = new SquareObstacle(20,20);
        assertTrue(obstacle.blocksPosition(new Position(20,20)));
        assertFalse(obstacle.blocksPosition(new Position(100,20)));
        assertFalse(obstacle.blocksPosition(new Position(20,100)));
        assertFalse(obstacle.blocksPosition(new Position(0,100)));
        assertFalse(obstacle.blocksPosition(new Position(120,100)));
    }

    @Test
    void testBlockPath(){
        Obstacle obstacle = new SquareObstacle(10,10);
        assertTrue(obstacle.blocksPath(new Position(10,0), new Position(10,50)));
        assertFalse(obstacle.blocksPath(new Position(20,-100), new Position(20, 100)));
        assertFalse(obstacle.blocksPath(new Position(-100,50), new Position(200, 50)));
        assertFalse(obstacle.blocksPath(new Position(0,10), new Position(0, 100)));
        assertFalse(obstacle.blocksPath(new Position(10,60), new Position(10, 100)));

    }

}
