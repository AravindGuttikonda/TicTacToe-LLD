package Models;

import Factories.BotPlayingStrategyFactory;
import Strategies.BotPlayingStrategy;
import jdk.jshell.spi.ExecutionControl;

import java.util.Scanner;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Symbol symbol, int id, BotDifficultyLevel botDifficultyLevel, Scanner scanner) throws ExecutionControl.NotImplementedException {
        super(symbol, id, PlayerType.BOT, scanner);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
    public Cell makeMove(Board board) {
        Cell cell = botPlayingStrategy.getCellToFill(board);
        cell.setCellState(CellState.OCCUPIED);
        cell.setPlayer(this);
        cell.setSymbol(this.getSymbol());
        return cell;
    }
}
