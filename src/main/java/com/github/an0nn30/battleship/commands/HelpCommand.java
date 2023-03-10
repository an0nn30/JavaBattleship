package com.github.an0nn30.battleship.commands;


import com.github.an0nn30.battleship.engine.Engine;
public class HelpCommand implements Command {
    private final Engine engine;

    public HelpCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(String[] args) {
        engine.showCommands();
    }

    @Override
    public String getHelpText() {
        return "help - Show all commands";
    }
}
