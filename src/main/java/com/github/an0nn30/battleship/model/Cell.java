package com.github.an0nn30.battleship.model;



public class Cell {
    private final int x;
    private final int y;

    private Ship ship;

    private CellStatus status;


    private char symbol;

    public Cell(int x, int y, CellStatus status) {
        this.x = x;
        this.y = y;
        this.setStatus(status);
    }

    public CellStatus getStatus() {
        return this.status;
    }

    public char getSymbol(boolean showShip) {
        if (this.ship != null && showShip) {
            return this.ship.getSymbol();
        }
        return this.symbol;
    }

    public void setStatus(CellStatus status) {
        switch (status) {
            case HIT -> this.symbol = 'X';
            case MISS -> this.symbol = 'M';
            case EMPTY -> {
                if (this.ship != null) {
                    this.ship = null;
                }
                this.symbol = '~';
            }
        }
        this.status = status;
    }

    public void takeHit() {
        if (this.getStatus() == CellStatus.EMPTY && this.getShip() != null) {
            this.setStatus(CellStatus.HIT);
            this.ship.takeHit();
        } else if (this.status == CellStatus.EMPTY && this.getShip() == null) {
            this.setStatus(CellStatus.MISS);
        }
    }


    public Ship getShip() {
        return this.ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public String getX() {
        return String.valueOf(this.x);
    }

    public String getY() {
        return String.valueOf(this.y);
    }
}
