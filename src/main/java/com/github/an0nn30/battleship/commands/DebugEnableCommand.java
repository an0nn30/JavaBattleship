package com.github.an0nn30.battleship.commands;

import com.github.an0nn30.battleship.engine.Engine;

public class DebugEnableCommand implements Command {
    private final Engine engine;

    public DebugEnableCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(String[] args) {
        engine.enableDebug();
    }

    @Override
    public String getHelpText() {
        return "debug enable - Enable debug mode";
    }
}