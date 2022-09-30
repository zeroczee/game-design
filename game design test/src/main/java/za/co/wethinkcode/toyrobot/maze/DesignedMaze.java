package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.List;

public class DesignedMaze extends AbstractMaze {
    private int numOfObstacles = 0;
    String[] out = {
            "11111111111111111111",
            "11111111111111111111",
            "10000000000000000011",
            "11111111111111111111",
            "10000000000000000011",
            "11111111111111111111",
            "10000000000000000011",
            "11111111111111111111",
            "11111111111111111111",
            "11111111111111111111",
            "11111111111111111111",
            "10001111111111100011",
            "10001111111111100011",
            "11111111111111111111",
            "10001111111111100011",
            "11111111111111111111",
            "10001111111111100011",
            "11111111111111111111",
            "10001111111111100011",
            "11111111111111111111",
            "10001111111111100011",
            "11111111111111111111",
            "10001111111111100011",
            "11111111111111111111",
            "10001111111111100011",
            "11111111111111111111",
            "10001111111111100011",
            "11111111111111111111",
            "11111111111111111111",
            "11111111111111111111",
            "11111111111111111111",
            "11111111111111111111",
            "10000000000000000011",
            "11111111111111111111",
            "10000000000000000011",
            "11111111111111111111",
            "10000000000000000011",
            "11111111111111111111",
            "11111111111111111111"};
    @Override
    public List<Obstacle> getObstacles() {
        return obstacles;
    }
    public DesignedMaze(){
        this.obstacles = setObstacles();
        this.numOfObstacles = obstacles.size();
    }

    private List<Obstacle> setObstacles() {
        for(int y = 0; y<out.length; y++){
            for(int x=0; x < out[y].length(); x++){

                if(out[y].charAt(x) == '0'){
                    int x_cor = -100 + (x * 10);
                    int y_cor = -200 + (y * 10);
                    obstacles.add(new SquareObstacle(x_cor, y_cor ));
                }
            }
        }
        return obstacles;
    }

    @Override
    public String toString() {
        return "DesignedMaze";
    }
}
