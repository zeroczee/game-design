package za.co.wethinkcode.toyrobot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReplayReversed extends Command{
    int start = -1, end = -1;
    public ReplayReversed(String arg, String arg1) {
        super(arg);
        end = Integer.parseInt(arg1.split("-")[0] );
        try{
            start = Integer.parseInt(arg1.split("-")[1]);

        } catch (ArrayIndexOutOfBoundsException e){
            start = 0;
        }
    }

    public boolean replayList(Robot target, String instruction) {

        Command command = Command.create(instruction);
        target.handleCommand(command);
        return true;
    }
    @Override
    public boolean execute(Robot target) {
        String output = "";
        Collections.reverse(target.replayList());

        if(start != -1){
            for(String instruction : target.replayList().subList(start, end)){
                replayList(target, instruction);
                output += "[" + target.getPosition().getX() + "," + target.getPosition().getY()  + "] " + target.getName()+ "> "   + target.getStatus() + "\n";
            }

            output += "[" + target.getPosition().getX() + "," + target.getPosition().getY()  + "] " + target.getName() + "> replayed " +( -(start-end) )+ " commands.";

        } else {
            for(String instruction : target.replayList()){
                replayList(target, instruction);
                output += "[" + target.getPosition().getX() + "," + target.getPosition().getY()  + "] " + target.getName()+ "> "   + target.getStatus() + "\n";
            }

            output += "[" + target.getPosition().getX() + "," + target.getPosition().getY()  + "] " + target.getName() + "> replayed " + target.replayList.size() + " commands.";

        }


        target.setStatus(output);
        return true;

        }

    public ReplayReversed(String argument) {
        super("replay", argument);

    }
}
