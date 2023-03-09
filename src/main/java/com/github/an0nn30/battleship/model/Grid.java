package com.github.an0nn30.battleship.model;


import java.util.ArrayList;
import java.util.List;

public class Grid {


    private final int size;

    private final List<Cell> cells;


    public Grid(int size) {
        this.size = size;
        this.cells = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells.add(new Cell(i, j, CellStatus.EMPTY));
            }
        }
    }
    public Grid(int size, List<Cell> cells) {
        this.size = size;
        this.cells = cells;
    }

    public Cell getCell(int x, int y) {
        return this.cells.get(x * size + y);
    }

    public int getSize() {
        return this.size;
    }
    public List<Cell> getCells() {
        return this.cells;
    }

    public void addShip(Ship ship) {
        System.out.println("Adding ship " + ship);
        int x = ship.getX();
        int y = ship.getY();
        int length = ship.getLength();
        Direction direction = ship.getDirection();
        if (direction == Direction.HORIZONTAL) {
            for (int i = x; i < x + length; i++) {
                this.cells.get(i * size + y).setShip(ship);
            }
        } else {
            for (int i = y; i < y + length; i++) {
                this.cells.get(x * size + i).setShip(ship);
            }
        }
    }

    public void print(boolean reveal) {
        for (int i = this.getSize() - 1; i >= 0; i--) {
            for (int j = 0; j < this.size; j++) {
                if (j == 0) {
                    System.out.print(i + "| ");
                }
                System.out.print(this.cells.get(i * size + j).getSymbol(reveal) + " ");
            }
            System.out.println();
            if (i == 0) {
                System.out.print("   ");
                for (int j = 0; j < this.size; j++) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }
        }
    }
}
