package com.github.an0nn30.battleship.engine;

import com.github.an0nn30.battleship.model.Direction;
import com.github.an0nn30.battleship.model.Player;
import com.github.an0nn30.battleship.model.Cell;
import com.github.an0nn30.battleship.model.Grid;
import com.github.an0nn30.battleship.model.Ship;

import java.util.*;

public class Engine {
    private final ArrayList<Ship> ship;
    private final Grid grid;

    public Engine(int size, int shipCount) {
        Random random = new Random();
        this.grid = new Grid(size);

        this.ship = new ArrayList<>();
        for (int i = 0; i <= shipCount; i++) {
            this.ship.add(new Ship(
                    random.nextInt(size - 1),
                    random.nextInt(size - 1),
                    random.nextInt(2) == 0 ? Direction.HORIZONTAL : Direction.VERTICAL,
                    random.nextInt(4)
            ));
        }
        for (Ship ship : this.ship) {
            this.grid.addShip(ship);
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public boolean isGameOver() {
        for (Ship ship : this.ship) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    private int getShipCount() {
        Set<Ship> ships = new HashSet<>();

        Cell[][] cells = grid.getCells();
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                Ship ship = value.getShip();
                if (ship != null && !ships.contains(ship)) {
                    ships.add(ship);
                }
            }
        }
        return ships.size();
    }

    private void showCommands() {
        System.out.println("<x> <y> - attack a cell");
        System.out.println("ships   - show ship count");
        System.out.println("reveal  - reveal the ships");
        System.out.println("hint    - show a hint");
        System.out.println("details - show ship details");
        System.out.println("exit    - exit the game");
    }

    private void handleInput(String input, Player player, Scanner scanner) {
        String[] split = input.split(" ");
        if (split.length == 2) {
            // Need to invert the coordinates, still working
            // on fixing the grid to have 0, 0 start on bottom left corner
            int x = Integer.parseInt(split[1]);
            int y = Integer.parseInt(split[0]);
            if (player.attack(this.grid, x, y)) {
                System.out.println("You sunk a ship!");
                scanner.nextLine();
            }
        } else if (split.length == 1) {
            switch (split[0]) {
                case "ships" -> {
                    System.out.println("Ships to sink: " + this.getShipCount());
                    scanner.nextLine();
                }
                case "reveal" -> {
                    clearScreen();
                    this.grid.print(true);
                    scanner.nextLine();
                }
                case "hint" -> {
                    System.out.println("Hint: " + this.ship.get(0));
                    scanner.nextLine();
                }
                case "details" -> {
                    for (Ship ship : this.ship) {
                        System.out.println(ship);
                    }
                    scanner.nextLine();
                }
                case "exit" -> System.exit(0);
                default -> System.out.println("Invalid command");
            }
        } else {
            System.out.println("Invalid command");
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        while (true) {
            clearScreen();
            String title = "BATTLESHIP";
            int titleLength = title.length();

            int numEquals = ((this.grid.getSize() * 2) - titleLength);

            System.out.printf("  %s%s%s%n", "=".repeat(numEquals / 2), title, "=".repeat(numEquals / 2));
            this.grid.print(false);
            System.out.println("Enter orders:");
            this.showCommands();
            System.out.print("> ");
            String input = scanner.nextLine();
            this.handleInput(input, player, scanner);
            if (this.isGameOver()) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
//            scanner.nextLine();
            clearScreen();
        }
    }
}
