package Strategies;

import Models.*;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol s = move.getSymbol();
        HashMap<Character,Integer> rowMap = Game.rowsMap.get(row);
        if(rowMap == null){
            rowMap = new HashMap<>();
            Game.rowsMap.put(row, rowMap);
        }
        if(rowMap.containsKey(s.getCh())){
            int x = rowMap.get(s.getCh());
            x++;
            rowMap.replace(s.getCh(),x);
        }
        else{
            rowMap.put(s.getCh(),1);
        }
        if((rowMap.containsKey(s.getCh())) && (rowMap.get(s.getCh()) == board.getSize())){
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Cell cell) {
        int row = cell.getRow();
        HashMap<Character,Integer> rowMap = Game.rowsMap.get(row);
        int x = rowMap.get(cell.getSymbol().getCh());
        x--;
        rowMap.replace(cell.getSymbol().getCh(),x);
    }
}
