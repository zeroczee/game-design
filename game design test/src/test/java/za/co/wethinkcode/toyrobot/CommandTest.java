package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void getShutdownName() {
        Command test = new ShutdownCommand();
        assertEquals("off", test.getName());
    }

    @Test
    void executeShutdown() {
        Robot robot = new Robot("CrashTestDummy");
        Command shutdown = Command.create("shutdown");
        assertFalse(shutdown.execute(robot));
        assertEquals("Shutting down...", robot.getStatus());
    }

    @Test
    void getForwardName() {
        ForwardCommand test = new ForwardCommand("10");
        assertEquals("forward", test.getName());
        assertEquals("10", test.getArgument());
    }

    @Test
    void updateDirectionRight() {
        IWorld world = new TextWorld(new EmptyMaze());
        world.updateDirection(true);
        assertEquals(IWorld.Direction.RIGHT, world.getCurrentDirection());
        world.updatePosition(10);
        Position newPosition = new Position(IWorld.CENTRE.getX() + 10, IWorld.CENTRE.getY());
        assertEquals(newPosition, world.getPosition());
    }

    @Test
    void updateDirectionLeft() {
        IWorld world = new TextWorld(new EmptyMaze());
        world.updateDirection(false);
        assertEquals(IWorld.Direction.LEFT, world.getCurrentDirection());
        world.updatePosition(10);
        Position newPosition = new Position(IWorld.CENTRE.getX() - 10, IWorld.CENTRE.getY());
        assertEquals(newPosition, world.getPosition());
    }

    @Test
    void updateDirectionForward() {
        IWorld world = new TextWorld(new EmptyMaze());
        world.updateDirection(false);
        assertEquals(IWorld.Direction.LEFT, world.getCurrentDirection());
        world.updatePosition(10);
        Position newPosition = new Position(IWorld.CENTRE.getX() - 10, IWorld.CENTRE.getY());
        assertEquals(newPosition.getX(), world.getPosition().getX());
    }

    @Test
    void updateDirectionBack() {
        IWorld world = new TextWorld(new EmptyMaze());
        world.updateDirection(false);
        assertEquals(IWorld.Direction.LEFT, world.getCurrentDirection());
        world.updatePosition(-10);
        Position newPosition = new Position(IWorld.CENTRE.getX() + 10, IWorld.CENTRE.getY());
        assertEquals(newPosition.getX(), world.getPosition().getX());
    }

    @Test
    void executeForward() {
        Robot robot = new Robot("CrashTestDummy");
        Command forward100 = Command.create("forward 100");
        assertTrue(forward100.execute(robot));
        Position expectedPosition = new Position(Robot.CENTRE.getX(), Robot.CENTRE.getY() + 100);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved forward by 100 steps.", robot.getStatus());
    }

    @Test
    void getBackName() {
        BackCommand test = new BackCommand("10");
        assertEquals("back", test.getName());
        assertEquals("10", test.getArgument());
    }

    @Test
    void executeBack() {
        Robot robot = new Robot("CrashTestDummy");
        Command back100 = Command.create("back 100");
        assertTrue(back100.execute(robot));
        Position expectedPosition = new Position(Robot.CENTRE.getX(), Robot.CENTRE.getY() - 100);
        assertEquals(expectedPosition.getX(), robot.getPosition().getX());
        assertEquals(expectedPosition.getY(), robot.getPosition().getY());
        assertEquals("Moved back by 100 steps.", robot.getStatus());
    }

    @Test
    void getHelpName() {
        Command test = new HelpCommand();                                                               //<1>
        assertEquals("help", test.getName());
    }

    @Test
    void executeHelp() {
        Robot robot = new Robot("CrashTestDummy");
        Command help = Command.create("help");
        assertTrue(help.execute(robot));
        assertEquals("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move back by a specific number of steps, e.g. 'BACK 10'\n" +
                "LEFT - turn robot to the left\n" +
                "RIGHT - turn robot to the right\n" +
                "SPRINT - sprints the robot by a specific number of steps\n" +
                "REPLAY - replays all movement commands\n" +
                "REPLAY n - replays the last n number of commands\n" +
                "REPLAY (n - m) - replays the commands from n to m\n" +
                "REPLAY REVERSED - replays all the movement commands in reverse", robot.getStatus());
    }

    @Test
    void createCommand() {
        Command forward = Command.create("forward 100");                                                 //<1>
        assertEquals("forward", forward.getName());
        assertEquals("100", forward.getArgument());

        Command shutdown = Command.create("shutdown");                                                  //<2>
        assertEquals("off", shutdown.getName());

        Command help = Command.create("help");                                                          //<3>
        assertEquals("help", help.getName());
    }

    @Test
    void createInvalidCommand() {
        try {
            Command forward = Command.create("say hello");                                              //<4>
            fail("Should have thrown an exception");                                                    //<5>
        } catch (IllegalArgumentException e) {
            assertEquals("Unsupported command: say hello", e.getMessage());                             //<6>
        }
    }
}
