package controllers;

import exceptions.InvalidGameConstructionParametersException;
import models.Game;
import models.GameStatus;
import models.Player;

import java.util.List;

public class GameController {
    public void undo(Game game){
        game.undo();
    }

    public Game createGame(int dimension, List<Player> players){
        try {
            return new Game.Builder().setDimension(dimension).setPlayers(players).build();
        } catch (Exception e) {
            return null;
        }
    }

    public void displayBoard(Game game){
        game.displayBoard();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }
    public void executeNextMove(Game game){
        game.makeNextMove();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

}
