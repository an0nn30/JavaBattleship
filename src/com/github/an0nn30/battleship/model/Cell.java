package com.github.an0nn30.battleship.model;

public class Cell {


    private final int x;
    private final int y;

    private Ship ship;

    private char symbol;

    private boolean isHit;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isHit = false;
    }

    public boolean hasShip() {
        return this.ship != null;
    }

    public boolean isHit() {
        return this.isHit;
    }

    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    public Ship getShip() {
        return this.ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public char getSymbol(boolean showShip) {
        if (showShip && this.hasShip()) {
            return 'S';
        }
        return this.symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getX() {
        return String.valueOf(this.x);
    }

    public String getY() {
        return String.valueOf(this.y);
    }
}
