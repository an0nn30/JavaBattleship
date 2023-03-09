package com.github.an0nn30.battleship.model;

import com.github.an0nn30.battleship.Direction;

import java.util.UUID;

public class Ship {
    private final int x;
    private final int y;
    private final Direction direction;
    private final int length;
    private int maxHits;
    private boolean sunk;
    private UUID id;


    public Ship(int x, int y, Direction direction, int length) {
        this.maxHits = length;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.length = length;
        this.id = UUID.randomUUID();
    }

    public void takeHit() {
        this.maxHits--;
        if (this.maxHits == 0) {
            this.sunk = true;
        }

    }

    public boolean isSunk() {
        return this.sunk;
    }

    public int getLength() {
        return this.length;
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

    public UUID getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", length=" + length +
                ", maxHits=" + maxHits +
                ", id=" + id +
                '}';
    }
}
