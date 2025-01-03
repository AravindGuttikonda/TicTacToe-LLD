package Models;

public class Cell {
    private int row;
    private int col;
    private Player player;
    private Symbol symbol;
    private CellState cellState;

    public Cell(int row, int col, CellState cellState) {
        this.row = row;
        this.col = col;
        this.cellState = cellState;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Symbol getSymbol() {
        return symbol;
    }
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public CellState getCellState() {
        return cellState;
    }
    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }
    public void displayBoard() {
        if(cellState.equals(CellState.EMPTY)){
            System.out.print("| - |");
        }
        else{
            System.out.print("| " + symbol.getCh() + " |");
        }
    }
}
