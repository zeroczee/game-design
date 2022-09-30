package za.co.wethinkcode.toyrobot;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("I can understand these commands:\n" +
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
                "REPLAY REVERSED - replays all the movement commands in reverse") ;

        return true;
    }
}
