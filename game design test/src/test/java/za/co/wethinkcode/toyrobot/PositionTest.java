package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

//    @Test
    public void shouldKnowXandY() {
        Position p = new Position(1, 1);
        assertEquals(1, p.getX());
        assertEquals(1, p.getY());

    }
    @Test
    public void equality() {
        assertEquals(new Position(-44, 63), new Position(-44, 63));
        assertNotEquals(new Position(-100, 30), new Position(0, 30));

    }
    @Test
    public void insideRectangularRegion() {
        Position topLeft = new Position(-30, 30);
        Position bottomRight = new Position(30,-30);
        assertTrue((new Position(15,15)).isIn(topLeft, bottomRight), "should be inside");
        assertTrue((new Position(5,20)).isIn(topLeft, bottomRight), "should be beyond top boundary");
        assertTrue((new Position(5,-20)).isIn(topLeft, bottomRight), "should be beyond bottom boundary");
        assertTrue((new Position(30,10)).isIn(topLeft, bottomRight), "should be beyond right boundary");
        assertTrue((new Position(-30,10)).isIn(topLeft, bottomRight), "should be beyond left boundary");
    }
}
