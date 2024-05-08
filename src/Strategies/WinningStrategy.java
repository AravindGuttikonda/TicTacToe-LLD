package Strategies;

import Models.Board;
import Models.Cell;
import Models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);

    void handleUndo(Board board, Cell cell);
}
