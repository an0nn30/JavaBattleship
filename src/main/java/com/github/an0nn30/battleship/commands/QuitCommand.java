package com.github.an0nn30.battleship.commands;

import com.github.an0nn30.battleship.engine.Engine;

public class QuitCommand implements Command {
    private final Engine engine;

    public QuitCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(String[] args) {
        this.engine.quit();
    }

    @Override
    public String getHelpText() {
        return "quit - Quit the game";
    }
}
