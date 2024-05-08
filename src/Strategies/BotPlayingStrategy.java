package Strategies;

import Models.Board;
import Models.Cell;

public interface BotPlayingStrategy {
    Cell getCellToFill(Board board);
}
