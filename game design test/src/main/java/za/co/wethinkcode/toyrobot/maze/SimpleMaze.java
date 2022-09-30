package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;

public class SimpleMaze extends AbstractMaze {
    private int numOfObstacles = 0;
    public SimpleMaze(){
        this.obstacles = setObstacles();
        this.numOfObstacles = obstacles.size();
    }

    private List<Obstacle> setObstacles() {
        List<Obstacle> obs = new ArrayList<>();
        obs.add(new SquareObstacle(1,1 ));
        return obs;
    }

    @Override
    public String toString() {
        return "SimpleMaze";
    }
}
