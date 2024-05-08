package Strategies;

import Models.*;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol s = move.getSymbol();
        HashMap<Character,Integer> firstDiagonalMap = Game.diagonalsMap.get(0);
        HashMap<Character,Integer> secondDiagonalMap = Game.diagonalsMap.get(1);
        if(firstDiagonalMap==null){
            firstDiagonalMap = new HashMap<>();
            Game.diagonalsMap.put(0,firstDiagonalMap);
        }
        if(secondDiagonalMap==null){
            secondDiagonalMap = new HashMap<>();
            Game.diagonalsMap.put(1,secondDiagonalMap);
        }
        if(row==col){
            if(firstDiagonalMap.containsKey(s.getCh())){
                int x = firstDiagonalMap.get(s.getCh());
                x++;
                firstDiagonalMap.replace(s.getCh(),x);
            }
            else{
                firstDiagonalMap.put(s.getCh(),1);
            }
        }
        if(row+col+1 == board.getSize()){
            if(secondDiagonalMap.containsKey(s.getCh())){
                int x = secondDiagonalMap.get(s.getCh());
                x++;
                secondDiagonalMap.replace(s.getCh(),x);
            }
            else{
                secondDiagonalMap.put(s.getCh(),1);
            }
        }
        if(((firstDiagonalMap.containsKey(s.getCh())) && (firstDiagonalMap.get(s.getCh()) == board.getSize())) || ((secondDiagonalMap.containsKey(s.getCh())) && (secondDiagonalMap.get(s.getCh()) == board.getSize()))){
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        if(row==col){
            HashMap<Character,Integer> firstDiagonalMap = Game.diagonalsMap.get(0);
            int x = firstDiagonalMap.get(cell.getSymbol().getCh());
            x--;
            firstDiagonalMap.replace(cell.getSymbol().getCh(),x);
        }
        if(row+col+1 == board.getSize()){
            HashMap<Character,Integer> secondDiagonalMap = Game.diagonalsMap.get(1);
            int x = secondDiagonalMap.get(cell.getSymbol().getCh());
            x--;
            secondDiagonalMap.replace(cell.getSymbol().getCh(),x);
        }
    }
}
