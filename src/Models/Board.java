package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> cells;

    public Board(int size) {
        this.size = size;
        List<List<Cell>> cells = new ArrayList<>();
        for(int i=0;i<size;i++){
            List<Cell> row = new ArrayList<>();
            for(int j=0;j<size;j++){
                Cell cell = new Cell(i,j,CellState.EMPTY);
                row.add(cell);
            }
            cells.add(row);
        }
        this.cells = cells;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public List<List<Cell>> getCells() {
        return cells;
    }
    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }
    public void displayBoard() {
        for(List<Cell> row : cells){
            for(Cell cell : row){
                cell.displayBoard();
            }
            System.out.println();
        }
    }
}
