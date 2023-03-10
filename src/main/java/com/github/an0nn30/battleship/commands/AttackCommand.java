package com.github.an0nn30.battleship.commands;


import com.github.an0nn30.battleship.engine.Engine;
import com.github.an0nn30.battleship.model.Player;

public class AttackCommand implements Command {
    private final Engine engine;
    private final Player player;

    public AttackCommand(Engine engine, Player player) {
        this.engine = engine;
        this.player = player;
    }

    @Override
    public void execute(String[] args) {
        // Parse the x and y coordinates from the arguments
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);

        boolean attackResult = this.player.attack(this.engine.getGrid(), x, y);

        System.out.println(attackResult ?
                (this.engine.getGrid().getCell(x, y).getShip().isSunk()
                        ? "You sunk a ship!"
                        : "You hit a ship!")
                : "You missed!");
    }

    @Override
    public String getHelpText() {
        return "attack <x> <y> - Attack the cell at the specified coordinates";
    }
}

