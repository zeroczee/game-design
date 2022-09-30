package za.co.wethinkcode.toyrobot;

public class ReplayCommand extends Command {

    public ReplayCommand() {
        super("replay");
    }

    public boolean replayList(Robot target, String instruction) {
        Command command = Command.create(instruction);
        target.handleCommand(command);
        return true;
    }

    @Override
    public boolean execute(Robot target) {
        String output = "";

        for(String instruction : target.replayList){
            replayList(target, instruction);
            output += "[" + target.getPosition().getX() + "," + target.getPosition().getY()  + "] " + target.getName()+ "> "  + target.getStatus() + "\n";
        }
            output += "[" + target.getPosition().getX() + "," + target.getPosition().getY()  + "] " + target.getName() + "> replayed " + target.replayList.size() + " commands.";

            target.setStatus(output);
            return true;
    }
}


