package com.github.an0nn30.battleship.commands;

import com.github.an0nn30.battleship.engine.Engine;

public class ShowShipsCommand implements Command {
    private final Engine engine;

    public ShowShipsCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(String[] args) {
        this.engine.clearScreen();
        System.out.println(this.engine.getGrid().getGridDrawing(true));
    }

    @Override
    public String getHelpText() {
        return "show ships - Show all ships";
    }
}
