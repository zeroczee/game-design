package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private final Position TOP_LEFT = new Position(-200, 100);
    private final Position BOTTOM_RIGHT = new Position(100, -200);

    public static final Position CENTRE = new Position(0, 0);

    private Position position;
    private Direction currentDirection;
    private String status;
    private String name;

    ArrayList<String> replayList = new ArrayList<>();

    private IWorld world;

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
        this.world = new TextWorld(new EmptyMaze());
    }

    public Robot(String name, IWorld world) {
        this.name = name;
        this.status = "Ready";
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
        this.world = world;
    }


    public void addCommand(String s) {
        replayList.add(s);
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public String getStatus() {
        return this.status;
    }

    public IWorld.Direction getCurrentDirection() {
        return Play.world.getCurrentDirection();
    }

    public boolean handleCommand(Command command) {
        return command.execute(this);
    }

    public boolean updatePosition(int nrSteps) {
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.UP.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        } else if (Direction.RIGHT.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        } else if (Direction.DOWN.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        } else if (Direction.LEFT.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
            this.position = newPosition;
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
       if(status.split(".").length > 1){
           return this.status;
       }

       return "[" + Play.world.getPos().getX() + "," + Play.world.getPos().getY() + "] "
               + this.name + "> " + this.status;
    }

    public Position getPosition() {
        return this.world.getPos();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public List<String> replayList() {
        return replayList;
    }

    public  IWorld getWorld(){
        return  this.world;
    }
}