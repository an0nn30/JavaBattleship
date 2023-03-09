package com.github.an0nn30.battleship;

import com.github.an0nn30.battleship.engine.Engine;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        String playAgain = "y";
        while (playAgain.equals("y")) {
            System.out.println("Grid size: ");
            Scanner scanner = new Scanner(System.in);
            int size = scanner.nextInt();
            System.out.println("Ship count: ");
            int shipCount = scanner.nextInt();
            Engine engine = new Engine(size, shipCount);
            engine.start();
            System.out.println("Play again? (y/n)");
            playAgain = scanner.nextLine();
        }
    }
}