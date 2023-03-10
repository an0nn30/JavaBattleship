package com.github.an0nn30.battleship.commands;

import com.github.an0nn30.battleship.engine.Engine;

public class DebugDisableCommand implements Command {
    private final Engine engine;

    public DebugDisableCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(String[] args) {
        engine.disableDebug();
    }

    @Override
    public String getHelpText() {
        return "debug disable - Disable debug mode";
    }
}