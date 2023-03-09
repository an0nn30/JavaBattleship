package com.github.an0nn30.battleship.model;


public class Grid {


    private final int size;

    private final Cell[][] cells;

    public Grid(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.cells[i][j] = new Cell(i, j);
                this.cells[i][j].setSymbol('~');
            }
        }
    }

    public Cell[][] getCells() {
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
                this.cells[i][y].setShip(ship);
            }
        } else {
            for (int i = y; i < y + length; i++) {
                this.cells[x][i].setShip(ship);
            }
        }
    }

    public int getSize() {
        return this.size;
    }

    public Cell getCell(int x, int y) {
        return this.cells[x][y];
    }

    public void print(boolean showShips) {
        for (int i = this.getSize() - 1; i >= 0; i--) {
            for (int j = 0; j < this.size; j++) {
                if (j == 0) {
                    System.out.print(i + "| ");
                }
                System.out.print(this.cells[i][j].getSymbol(showShips) + " ");
            }
            System.out.println();
            if (i == 0) {
                for (int s = 0; s < this.size; s++) {
                    if (s == 0) {
                        System.out.print("  ");
                    }
                    System.out.print(" " + s);
                }
                System.out.println();
            }
        }

    }
}
