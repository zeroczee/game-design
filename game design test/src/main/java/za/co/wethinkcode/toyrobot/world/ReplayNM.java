package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Command;
import za.co.wethinkcode.toyrobot.Robot;

import java.util.List;

public class ReplayNM extends Command {
    public ReplayNM(String arg) {
        super("replay", arg);
    }

    public boolean replayList(Robot target, String instruction) {
        Command command = Command.create(instruction);
        target.handleCommand(command);
        return true;
    }

    @Override
    public boolean execute(Robot target) {
        String output = "";

        String[] splitArg = getArgument().split("-");
        int n = Integer.parseInt(splitArg[0]);
        int m = Integer.parseInt(splitArg[1]);
        List<String> history = target.replayList();

        for(int i = history.size() - n; i < history.size() - m; i++){
            replayList(target, history.get(i));
            output += "[" + target.getPosition().getX() + "," + target.getPosition().getY()  + "] " + target.getName() + "> " + target.getStatus() + "\n";
        }
        output += "[" + target.getPosition().getX() + "," + target.getPosition().getY()  + "] " + target.getName() + "> replayed " + (n-m) + " commands.";

        target.setStatus(output);
        return true;
    }
}
