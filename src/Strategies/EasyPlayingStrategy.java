package Strategies;

import Models.Board;
import Models.Cell;
import Models.CellState;

import java.util.List;

public class EasyPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Cell getCellToFill(Board board) {
        List<List<Cell>> cells = board.getCells();
        for(List<Cell> row : cells){
            for(Cell cell : row){
                if(cell.getCellState()== CellState.EMPTY){
                    return cell;
                }
            }
        }
        return null;
    }
}
