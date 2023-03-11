package com.github.an0nn30.battleship;

import com.github.an0nn30.battleship.engine.Engine;
import com.github.an0nn30.battleship.model.Player;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Engine engine = Engine.getInstance();
//        System.out.println(engine.getGrid().toJson());
        engine.start();
    }
}