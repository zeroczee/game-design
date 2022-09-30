package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Command;
import za.co.wethinkcode.toyrobot.Robot;

import java.util.List;

public class ReplayN extends Command {
    public ReplayN(String argument) {
        super("replay", argument);
    }

    public boolean replayList(Robot target, String instruction) {
        Command command = Command.create(instruction);
        target.handleCommand(command);
        return true;
    }

    @Override
    public boolean execute(Robot target) {
        String output = "";
        int n = Integer.parseInt(getArgument());
        List<String> history = target.replayList();

        for(int i = history.size() - n; i < history.size(); i++){
            replayList(target, history.get(i));
            output += "[" + target.getPosition().getX() + "," + target.getPosition().getY()  + "] " + target.getName() + "> " + target.getStatus() + "\n";
        }
        output += "[" + target.getPosition().getX() + "," + target.getPosition().getY()  + "] " + target.getName() + "> replayed " + n + " commands.";

        target.setStatus(output);
        return true;
    }
}
