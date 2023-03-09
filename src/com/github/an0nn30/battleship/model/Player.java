package com.github.an0nn30.battleship.model;

import com.github.an0nn30.battleship.model.Cell;
import com.github.an0nn30.battleship.model.Grid;

public class Player {
    public boolean attack(Grid grid, int x, int y) {
        Cell cell = grid.getCell(x, y);
        if (cell.hasShip() && !cell.isHit()) {
            cell.setHit(true);
            cell.getShip().takeHit();
            cell.setSymbol('X');
            return cell.getShip().isSunk();
        } else if (!cell.isHit()) {
            cell.setHit(true);
            cell.setSymbol('M');
            return false;
        } else {
            System.out.println("You already hit this cell");
            return false;
        }
    }
}
