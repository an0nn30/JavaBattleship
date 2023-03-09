package com.github.an0nn30.battleship.model;
public class Player {
    public boolean attack(Grid grid, int x, int y) {
        Cell cell = grid.getCell(x, y);
        cell.takeHit();
        if (cell.getShip() != null && cell.getShip().isSunk()) {
            return true;
        }
        return false;
    }
}
