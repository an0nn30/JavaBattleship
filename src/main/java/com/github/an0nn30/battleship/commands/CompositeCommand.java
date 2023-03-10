package com.github.an0nn30.battleship.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CompositeCommand implements Command {
    // This class is a composite class used to store subcommands of a given command.
    private final Map<String, Command> subCommands;

    public CompositeCommand() {
        subCommands = new HashMap<>();
    }

    public void addSubCommand(String name, Command command) {
        subCommands.put(name, command);
    }

    public boolean hasSubCommand(String name) {
        return subCommands.containsKey(name);
    }

    public Command getSubCommand(String name) {
        return subCommands.get(name);
    }

    @Override
    public void execute(String[] args) {
        String subCommandName = args[0];
        String[] subCommandArgs = Arrays.copyOfRange(args, 1, args.length);

        if (subCommands.containsKey(subCommandName)) {
            Command subCommand = subCommands.get(subCommandName);
            subCommand.execute(subCommandArgs);
        } else {
            System.out.println("Invalid subcommand. Type 'help' for a list of commands.");
        }
    }

    @Override
    public String getHelpText() {
        StringBuilder builder = new StringBuilder();
        builder.append("Available subcommands:");

        for (Command command : subCommands.values()) {
            builder.append("\n  ");
            builder.append(command.getHelpText());
        }

        return builder.toString();
    }
}
