package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.world.turtle.Turtle;
import za.co.wethinkcode.toyrobot.world.turtle.World;

import java.util.List;

public class TurtleWorld extends AbstractWorld {
    private double x,y;
    private double angle;
    private Turtle turtle;
    private List<Obstacle> obstacles;

    public TurtleWorld(Maze maze) {
        super(maze);

        World world = new World();
        this.turtle = new Turtle(world);
        turtle.pickPenUp();
        turtle.left(90);
        this.obstacles = super.maze.getObstacles();
        showObstacles();
        turtle.goTo(0,0);
        turtle.putPenDown();
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

        if(maze.blocksPath(position, newPosition)){
            return UpdateResponse.FAILED_OBSTRUCTED;
        } else if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT) ) {
            this.position = newPosition;
            turtle.putPenDown();
            turtle.forward(nrSteps);
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

            turtle.right(90);

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
            turtle.left(90);
        }

    }

    @Override
    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }

    @Override
    public void showObstacles() {
        this.turtle.setDelay(0);
        this.turtle.pickPenUp();
        this.turtle.goTo(-100,200);
        this.turtle.putPenDown();
        for(int i = 1; i <= 4; i++){
            this.turtle.right(90);
            this.turtle.forward(200);
            this.turtle.right(90);
            this.turtle.forward(400);

        }
        for (Obstacle x : this.obstacles) {
            this.turtle.pickPenUp();
            this.turtle.goTo(x.getBottomLeftX(),x.getBottomLeftY());
            this.turtle.putPenDown();
            this.turtle.goTo(x.getBottomLeftX(),x.getBottomLeftY()+20);
            this.turtle.goTo(x.getBottomLeftX()+20,x.getBottomLeftY()+20);
            this.turtle.goTo(x.getBottomLeftX()+20,x.getBottomLeftY());
            this.turtle.goTo(x.getBottomLeftX(),x.getBottomLeftY());
            this.turtle.pickPenUp();
        }
    }

}
