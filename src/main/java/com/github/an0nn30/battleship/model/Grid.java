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
                cells.add(new Cell(j, i, CellStatus.EMPTY));
            }
        }
    }

    public Cell getCell(int x, int y) {
        return this.cells.get(x * size + y);
    }

    public void clear() {
        for (Cell cell : this.cells) {
            cell.setStatus(CellStatus.EMPTY);
        }
    }

    public int getSize() {
        return this.size;
    }

    public List<Cell> getCells() {
        return this.cells;
    }

    public boolean placeShip(Ship ship) {
        int x = ship.getX();
        int y = ship.getY();
        int length = ship.getLength();
        Direction direction = ship.getDirection();

        if (isShipOutOfBounds(x, y, length, direction)) {
            // If the ship is out of bounds, try placing it in the other direction
            if (isShipOutOfBounds(x, y, length, flipDirection(direction))) {
                return false;
            } else {
                direction = flipDirection(direction);
                ship.setDirection(direction);
            }
        }

        if (isShipOverlapping(x, y, length, direction)) {
            // If the ship is overlapping with another ship, return false
            return false;
        }

        // Place the ship in the grid
        for (int i = 0; i < length; i++) {
            Cell cell = getCell(x, y);
            cell.setShip(ship);
            if (direction == Direction.HORIZONTAL) {
                x++;
            } else {
                y++;
            }
        }
        return true;
    }

    private boolean isShipOutOfBounds(int x, int y, int length, Direction direction) {
        if (direction == Direction.HORIZONTAL) {
            return x + length > getSize();
        } else {
            return y + length > getSize();
        }
    }

    private Direction flipDirection(Direction direction) {
        if (direction == Direction.HORIZONTAL) {
            return Direction.VERTICAL;
        } else {
            return Direction.HORIZONTAL;
        }
    }

    private boolean isShipOverlapping(int x, int y, int length, Direction direction) {
        for (int i = 0; i < length; i++) {
            Cell cell = getCell(x, y);
            if (cell.getShip() != null) {
                return true;
            }
            if (direction == Direction.HORIZONTAL) {
                x++;
            } else {
                y++;
            }
        }
        return false;
    }

    public void addShip(Ship ship) {
        System.out.println("Adding ship " + ship);
        if (!this.placeShip(ship)) {
            System.out.println("Could not place ship: " + ship);
        }
    }

    public void print(boolean reveal) {
        for (int i = this.getSize() - 1; i >= 0; i--) {
            for (int j = 0; j < this.size; j++) {
                if (j == 0) {
                    System.out.print(i + "| ");
                }
                System.out.print(this.getCell(j, i).getSymbol(reveal) + " ");
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
