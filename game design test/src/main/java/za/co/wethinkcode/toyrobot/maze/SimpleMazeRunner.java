package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Command;
import za.co.wethinkcode.toyrobot.Play;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.world.IWorld;

public class SimpleMazeRunner extends Command implements MazeRunner {

    private void load() {
        System.out.println("Starting maze run..");
    }

    @Override
    public boolean execute(Robot target) {
        while(true){
            IWorld.UpdateResponse response = Play.world.updatePosition(1);
            if(response == IWorld.UpdateResponse.FAILED_OBSTRUCTED){
                Play.world.updateDirection(true);
                Play.world.updatePosition(1);
            } else if(response == IWorld.UpdateResponse.FAILED_OUTSIDE_WORLD){
                Play.world.updatePosition(-1);
                Play.world.updateDirection(true);
            }

            if(getArgument().equalsIgnoreCase("top") &&
                target.getPosition().getY() == 200){
                break;
            } else if (getArgument().equalsIgnoreCase("bottom") &&
                    target.getPosition().getY() == -200) {
                break;
            } else if (getArgument().equalsIgnoreCase("left") &&
                    target.getPosition().getX() == -100) {
                break;
            } else if (getArgument().equalsIgnoreCase("right") &&
                    target.getPosition().getX() == 100) {
                break;
            }
        }
        return true;
    }

    public SimpleMazeRunner(String arg) {
        super("mazerun",arg);
    }

    @Override
    public boolean mazeRun(Robot target, IWorld.Direction edgeDirection) {
        return false;
    }

    @Override
    public int getMazeRunCost() {
        return 0;
    }
}
