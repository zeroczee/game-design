package za.co.wethinkcode.toyrobot;

public class RightCommand extends Command {
    @Override
    public boolean execute(Robot target) {
        target.setStatus("Turned right.");
        target.getWorld().updateDirection(true);

        return true;
    }

    public RightCommand() {
        super("right");
    }
}
