package com.github.an0nn30.battleship.commands;

import com.github.an0nn30.battleship.engine.Engine;

public class ShowShipCount implements Command {
    private final Engine engine;

    public ShowShipCount(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Ships left: " + engine.getShipCount());
    }

    @Override
    public String getHelpText() {
        return "show count - Show the number of ships left";
    }
}
