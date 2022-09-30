package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMaze extends AbstractMaze {
    private final List<Obstacle> obstacles = new ArrayList<>();

    private int numOfObstacles = 0;

    public RandomMaze(){
        Random random = new Random();
        this.numOfObstacles = (random.nextInt(11));
        setObstacles();
    }
    private void setObstacles() {
        Random random = new Random();
        for (int i = 0; i < numOfObstacles; i++) {
            int x = random.nextInt(201) - 100;
            int y = random.nextInt(401) - 200;
            obstacles.add(new SquareObstacle(x,y));
        }
    }
    @Override
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

//    @Override
//    public boolean isWall(int x, int y) {
//        return true;
//    }
//
//    @Override
//    public void setVisited(int x, int y, boolean b) {
//
//    }
//
//    @Override
//    public boolean isExplored(int x, int y) {
//        return true;
//    }
//
//    @Override
//    public boolean isExit(int x, int y) {
//        return true;
//    }
//
//    @Override
//    public Position getEntry() {
//        return null;
//    }
//
//    @Override
//    public boolean blocksPath(int x, int y) {
//        return true;
//    }

    @Override
    public String toString() {
        return "RandomMaze";
    }

}
