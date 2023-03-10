package com.github.an0nn30.battleship.commands;



public interface Command {
    void execute(String[] args);
    String getHelpText();


}