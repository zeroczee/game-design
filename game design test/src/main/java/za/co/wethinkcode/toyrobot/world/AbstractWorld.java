package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;

public abstract class AbstractWorld implements IWorld {

    protected final Position TOP_LEFT = new Position(-100, 200);
    protected final Position BOTTOM_RIGHT = new Position(100, -200);
    protected final Position CENTRE = new Position(0, 0);

    protected Position position;
    protected Direction currentDirection;

    protected Maze maze;

    public AbstractWorld(Maze maze) {
        this.maze = maze;

        this.position = CENTRE;
        this.currentDirection = Direction.UP;
    }


    @Override
    public IWorld.UpdateResponse updatePosition(int nrSteps) {
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
            return UpdateResponse.SUCCESS;
        }
        return UpdateResponse.FAILED_OUTSIDE_WORLD;
    }
    @Override
    public void updateDirection(boolean turnRight) {
        if (turnRight){
            if (currentDirection == IWorld.Direction.UP) {
                setCurrentDirection(IWorld.Direction.RIGHT);
            }
            else if (currentDirection == IWorld.Direction.RIGHT) {
                setCurrentDirection(Direction.DOWN);
            }
            else if (currentDirection == IWorld.Direction.DOWN) {
                setCurrentDirection(Direction.LEFT);
            }
            else if (currentDirection == IWorld.Direction.LEFT) {
                setCurrentDirection(Direction.UP);
            }

        } else {
            if (currentDirection == IWorld.Direction.UP) {
                setCurrentDirection(IWorld.Direction.LEFT);
            }
            else if (currentDirection == IWorld.Direction.LEFT) {
                setCurrentDirection(Direction.DOWN);
            }
            else if (currentDirection == IWorld.Direction.DOWN) {
                setCurrentDirection(Direction.RIGHT);
            }
            else if (currentDirection == IWorld.Direction.RIGHT) {
                setCurrentDirection(Direction.UP);
            }
        }

    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    public Position getPos() {
        return this.position;
    }

    @Override
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    @Override
    public boolean isNewPositionAllowed(Position position) {
        if(position.isIn(TOP_LEFT, BOTTOM_RIGHT)){
            return true;
        }
        return false;
    }

    @Override
    public boolean isAtEdge() {
        if((position.getX() == 100 || position.getX() == -100)  ||
                (position.getY() == 200 || position.getY() == -200) ){
            return true;
        }

        return false;
    }

    @Override
    public void reset() {
        position = CENTRE;
        currentDirection = Direction.UP;
    }


}
