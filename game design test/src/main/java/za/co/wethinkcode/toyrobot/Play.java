package za.co.wethinkcode.toyrobot;
import za.co.wethinkcode.toyrobot.maze.DesignedMaze;
import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.maze.RandomMaze;
import za.co.wethinkcode.toyrobot.maze.SimpleMaze;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

import java.util.Scanner;

public class Play {
    static Scanner scanner;
    public static IWorld world;

    public static void main(String[] args) {

        world = setWorld( args);
        scanner = new Scanner(System.in);
        Robot robot;

        String name = getInput("What do you want to name your robot?");
        robot = new Robot(name, world);
        System.out.println("Hello Kiddo!");

        System.out.println(robot.toString());

        Command command;
        boolean shouldContinue = true;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {

                command = Command.create(instruction);
                shouldContinue = robot.handleCommand(command);

                if(!(instruction.contains("replay") || instruction.contains("help"))){
                    robot.addCommand(instruction);
                }



            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
            }
            System.out.println(robot);
        } while (shouldContinue);

    }

    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }

    public static IWorld setWorld( String[] args) {
        IWorld world;
        String robotWorld = args[0];
        String maze = null;
        try{
            maze = args[1];
        }catch (IndexOutOfBoundsException e) {
            maze = "";
        }

        if (robotWorld.equalsIgnoreCase("turtle")) {
            switch (maze.toLowerCase()) {
                case "designedmaze":
                    System.out.println("Loaded DesignedMaze");
                    world = new TurtleWorld(new DesignedMaze());
                    break;
                case "simplemaze":
                    System.out.println("Loaded SimpleMaze");
                    world =  new TurtleWorld(new SimpleMaze());
                    break;
                case "randommaze":
                    System.out.println("Loaded RandomMaze");
                    world = new TurtleWorld(new RandomMaze());
                    break;
                default:
                    System.out.println("Loaded EmptyMaze");
                    world = new TurtleWorld(new EmptyMaze());
                    break;
            }
        } else {
            switch (maze.toLowerCase()) {
                case "designedmaze":
                    System.out.println("Loaded DesignedMaze");
                    world = new TextWorld(new DesignedMaze());
                    break;
                case "simplemaze":
                    System.out.println("Loaded SimpleMaze");
                    world = new TextWorld(new SimpleMaze());
                    break;
                case "randommaze":
                    System.out.println("Loaded RandomMaze");
                    world = new TextWorld(new RandomMaze());
                    break;
                default:
                    System.out.println("Loaded EmptyMaze");
                    world = new TextWorld(new EmptyMaze());
                    break;
            }
        }


        return world;
    }
}
