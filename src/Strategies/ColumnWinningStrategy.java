package Strategies;

import Models.*;

import java.util.HashMap;

public class ColumnWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol s = move.getSymbol();
        HashMap<Character,Integer> columnMap = Game.columnsMap.get(col);
        if(columnMap == null){
            columnMap = new HashMap<>();
            Game.columnsMap.put(col, columnMap);
        }
        if(columnMap.containsKey(s.getCh())){
            int x = columnMap.get(s.getCh());
            x++;
            columnMap.replace(s.getCh(),x);
        }
        else{
            columnMap.put(s.getCh(),1);
        }
        if((columnMap.containsKey(s.getCh())) && (columnMap.get(s.getCh()) == board.getSize())){
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Cell cell) {
        int col = cell.getCol();
        HashMap<Character,Integer> colMap = Game.columnsMap.get(col);
        int x = colMap.get(cell.getSymbol().getCh());
        x--;
        colMap.replace(cell.getSymbol().getCh(),x);
    }
}
