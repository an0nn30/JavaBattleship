package com.github.an0nn30.battleship.commands;

import com.github.an0nn30.battleship.engine.Engine;

public class ShowJsonCommand implements Command {
    private final Engine engine;

    public ShowJsonCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(String[] args) {
        engine.clearScreen();
        System.out.println(engine.getGrid().toJson());
    }

    @Override
    public String getHelpText() {
        return "show json - Show all contents in json format";
    }
}
