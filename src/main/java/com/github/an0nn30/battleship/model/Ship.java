package com.github.an0nn30.battleship.model;

import java.util.Random;
import java.util.UUID;

public class Ship {
    public static final int MAX_SHIP_LENGTH = 5;
    public static final int MIN_SHIP_LENGTH = 3;
    private final int x;
    private final int y;
    private Direction direction;
    private final int length;
    private int maxHits;
    private boolean sunk;
    private char symbol;


    public Ship(int x, int y, Direction direction, int length, char symbol) {
        this.maxHits = length;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.length = length;
        this.symbol = symbol;
    }

    public void takeHit() {
        this.maxHits--;
        if (this.maxHits == 0) {
            this.symbol = 'S';
            this.sunk = true;
        }

    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public boolean isSunk() {
        return this.sunk;
    }

    public int getLength() {
        return this.length;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", length=" + length +
                ", maxHits=" + maxHits +
                ", symbol=" + symbol +
                '}';
    }
}
