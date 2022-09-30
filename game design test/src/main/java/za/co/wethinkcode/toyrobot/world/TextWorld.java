package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.util.List;

public class TextWorld extends AbstractWorld {
    private List<Obstacle> obstacles;
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

        if(maze.blocksPath(position, newPosition)){
            return UpdateResponse.FAILED_OBSTRUCTED;
        } else if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT) ) {
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
    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }

    public TextWorld(Maze maze) {
        super(maze);
    }
    @Override
    public void showObstacles() {
        System.out.println(obstacles);
    }
}
