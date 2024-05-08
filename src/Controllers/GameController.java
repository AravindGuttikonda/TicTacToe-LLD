package Controllers;

import Exceptions.DuplicateSymbolException;
import Exceptions.MoreThanOneBotException;
import Exceptions.PlayersAndBoardSizeMisMatchException;
import Models.Game;
import Models.Player;
import Strategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int size, List<Player> players, List<WinningStrategy> winningStrategies) throws DuplicateSymbolException, PlayersAndBoardSizeMisMatchException, MoreThanOneBotException {
        Game game = Game.getBuilder().setSize(size).setPlayers(players).setWinningStrategies(winningStrategies).build();
        return game;
    }
    public void displayBoard(Game game){
        game.displayBoard();
    }
    public void makeMove(Game game){
        game.makeMove();
    }

    public void undo(Game game) {
        game.undo();
    }
}
