package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.ReplayN;
import za.co.wethinkcode.toyrobot.maze.SimpleMazeRunner;
import za.co.wethinkcode.toyrobot.world.ReplayNM;

import java.lang.reflect.Array;

public abstract class Command {
    private final String name;
    private String argument;

    public abstract boolean execute(Robot target);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]){
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                return new ForwardCommand(args[1]);
            case "back":
                return new BackCommand(args[1]);
            case "left":
                return new LeftCommand();
            case "right":
                return new RightCommand();
            case "sprint":
                return new SprintCommand(args[1]);
            case "replay":
                if(args.length >= 2 && args[1].toLowerCase().contentEquals("reversed")){
                    try{
                        return new ReplayReversed(args[1], args[2]);
                    } catch (ArrayIndexOutOfBoundsException e){
                        return new ReplayReversed(args[1]);
                    }

                } else if (args.length == 2 && checkArgs(args[1])){
                    return new ReplayN(args[1]);
                } else if (args.length == 2 && checkArgsDash(args[1])){
                    return new ReplayNM(args[1]);
                } else{
                    return new ReplayCommand();
                }
            case "mazerun":
                if(args.length == 2 &&( args[1].toLowerCase().contentEquals("top") ||
                        args[1].toLowerCase().contentEquals("bottom") ||
                        args[1].toLowerCase().contentEquals("right")||
                        args[1].toLowerCase().contentEquals("left"))) {
                    return new SimpleMazeRunner(args[1]);
                } else {
                    return new SimpleMazeRunner("top");
                }
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }

    static boolean checkArgs(String arg) {
        for (int i = 0; i < arg.length(); i++) {
            if (!Character.isDigit(arg.charAt(i))){
                return false;
            }
        }

        return true;
    }

    static boolean checkArgsDash(String arg) {
        String[] args = arg.split("-");

        if (args.length != 2) {
            return false;
        }

        for (String s : args) {
            if (!Character.isDigit(s.charAt(0))) {
                return false;
            }
        }

        return true;
    }
}

