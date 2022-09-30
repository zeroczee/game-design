package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class SprintCommand extends Command {

    public SprintCommand(String argument) {
        super("sprint", argument);
    }

    @Override
        public boolean execute(Robot target) {
            int nrSteps = Integer.parseInt(getArgument());
            String output = "";
            for (int i = nrSteps; i >= 1; i--){
                if (target.getWorld().updatePosition(i).equals(IWorld.UpdateResponse.SUCCESS)){
                    if (i != 1){
                        output += "[" + target.getPosition().getX() + "," + target.getPosition().getY() + "] "
                                + target.getName() + "> "  +"Moved forward by "+i+" steps.\n";
                    }else if (i == 1){
                        output += "[" + target.getPosition().getX() + "," + target.getPosition().getY() + "] "
                                + target.getName() + "> " +"Moved forward by "+i+" steps.";
                    }
                } else {
                    if (i != 1){
                        output += "Sorry, I cannot go outside my safe zone.\n";

                    }else  if (i == 1){
                        output += "Sorry, I cannot go outside my safe zone.  ";
                    }
                }
            }
            target.setStatus(output);
                return true;
            }
}


