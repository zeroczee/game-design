package za.co.wethinkcode.toyrobot;

public class LeftCommand extends Command {
    @Override
    public boolean execute(Robot target) {
        target.setStatus("Turned left.");
        target.getWorld().updateDirection(false);

        return true;
    }

    public LeftCommand() {
        super("left");
    }
}

