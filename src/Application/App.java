package Application;

import Controllers.GameController;
import Exceptions.DuplicateSymbolException;
import Exceptions.MoreThanOneBotException;
import Exceptions.PlayersAndBoardSizeMisMatchException;
import Models.*;
import Strategies.*;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws DuplicateSymbolException, PlayersAndBoardSizeMisMatchException, MoreThanOneBotException, ExecutionControl.NotImplementedException {
        Scanner scanner = new Scanner(System.in);
        int size = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player(new Symbol('*'), 1, PlayerType.HUMAN, scanner));
        players.add(new Bot(new Symbol('0'),2, BotDifficultyLevel.EASY, scanner));
        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        GameController gameController = new GameController();

        Game game = gameController.startGame(size,players,winningStrategies);
        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            gameController.displayBoard(game);
            System.out.println("Does anyone want to undo the move(Y/N)");
            String input = scanner.next();
            if(input.equalsIgnoreCase("Y")){
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);
        }
        gameController.displayBoard(game);
    }
}
