package com.github.an0nn30.battleship.engine;

import com.github.an0nn30.battleship.commands.*;
import com.github.an0nn30.battleship.model.*;

import java.util.*;

public class Engine {

    private static Engine instance = null;
    private final ArrayList<Ship> ship;
    private final Map<String, Command> commands;
    private final Grid grid;

    private boolean debugMode;


    public Engine() {
        // The engine class is a singleton since we only need one instance of it
        // at any given time. Here we are intialising all the variables and commands
        // that the engine will need to run.
        Player player = new Player();
        int size = 10;
        int shipCount = 5;
        Random random = new Random();
        CompositeCommand showCommand = new CompositeCommand();
        CompositeCommand debugCommand = new CompositeCommand();
        this.commands = new HashMap<>();
        this.grid = new Grid(size);
        this.ship = new ArrayList<>();
        this.debugMode = false;

        this.commands.put("attack", new AttackCommand(this, player));
        this.commands.put("help", new HelpCommand(this));
        this.commands.put("debug", debugCommand);
        debugCommand.addSubCommand("enable", new DebugEnableCommand(this));
        debugCommand.addSubCommand("disable", new DebugDisableCommand(this));
        this.commands.put("quit", new QuitCommand(this));
        this.commands.put("show", showCommand);
        showCommand.addSubCommand("count", new ShowShipCount(this));
        showCommand.addSubCommand("ships", new ShowShipsCommand(this));
        showCommand.addSubCommand("json", new ShowJsonCommand(this));


        for (int i = 0; i < shipCount; i++) {
            this.ship.add(new Ship(
                    random.nextInt(size - 1),
                    random.nextInt(size - 1),
                    random.nextInt(2) == 0 ? Direction.HORIZONTAL : Direction.VERTICAL,
                    random.nextInt(4),
                    (char) (i + 1 + 'A')
            ));
        }
        for (Ship ship : this.ship) {
            this.grid.addShip(ship);
        }
    }

    public void quit() {
        System.exit(0);
    }

    public void enableDebug() {
        this.debugMode = true;
        System.out.println("Debug mode enabled");
    }

    public void disableDebug() {
        this.debugMode = false;
        System.out.println("Debug mode disabled");
    }

    public Grid getGrid() {
        return grid;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public boolean isGameOver() {
        for (Ship ship : this.ship) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public int getShipCount() {
        // TODO: Revisit this method, it may not be needed anymore
        Set<Ship> ships = new HashSet<>();

        List<Cell> cells = grid.getCells();
        for (Cell cell : cells) {
            Ship ship = cell.getShip();
            if (ship != null && !ships.contains(ship)) {
                ships.add(ship);
            }
        }
        return ships.size();
    }

    public void showCommands() {
        System.out.println("Available commands:");
        for (Command command : commands.values()) {
            System.out.println(" - " + command.getHelpText());
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!isGameOver()) {
            clearScreen();
            String title = "BATTLESHIP";
            int titleLength = title.length();

            int numEquals = ((this.grid.getSize() * 2) - titleLength);

            System.out.printf("  %s%s%s%n", "=".repeat(numEquals / 2), title, "=".repeat(numEquals / 2));
            System.out.println(this.grid.getGridDrawing(debugMode));
            System.out.println("Enter orders:");
            System.out.print("> ");
            input = scanner.nextLine().trim();

            String[] parts = input.split(" ");
            String commandName = parts[0];

            // This is how we handle parsing the commands. Commands are stored in a map,
            // which will map the command name to the command object. We can then use the
            // the object to execute the relevent action for the command.
            if (commands.containsKey(commandName)) {
                Command command = commands.get(commandName);
                String[] arguments = Arrays.copyOfRange(parts, 1, parts.length);

                if (command instanceof CompositeCommand compositeCommand && arguments.length > 0) {
                    // Handle commands with subcommands
                    String subCommandName = arguments[0];
                    String[] subCommandArguments = Arrays.copyOfRange(arguments, 1, arguments.length);

                    if (compositeCommand.hasSubCommand(subCommandName)) {
                        Command subCommand = compositeCommand.getSubCommand(subCommandName);
                        subCommand.execute(subCommandArguments);
                        scanner.nextLine();
                    } else {
                        System.out.println("Invalid subcommand. Type 'help' for a list of commands.");
                    }
                } else {
                    // Handle regular commands
                    command.execute(arguments);
                    scanner.nextLine();
                }
            } else {
                System.out.println("Invalid command. Type 'help' for a list of commands.");
                scanner.nextLine();
            }
        }
    }

    public static Engine getInstance() {
        if (instance == null) {
            instance = new Engine();
        }
        return instance;
    }
}
