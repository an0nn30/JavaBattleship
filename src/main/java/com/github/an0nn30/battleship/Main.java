package com.github.an0nn30.battleship;

import com.github.an0nn30.battleship.engine.Engine;
import com.github.an0nn30.battleship.model.Player;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        String playAgain = "y";
        while (playAgain.equals("y")) {
            Scanner scanner = new Scanner(System.in);
            Engine engine = Engine.getInstance();
            engine.start();
            System.out.println("Play again? (y/n)");
            playAgain = scanner.nextLine();
        }
    }
}