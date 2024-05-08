package Models;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private int id;
    private PlayerType playerType;
    private Scanner scanner;
    public Player(Symbol symbol, int id, PlayerType playerType, Scanner scanner) {
        this.symbol = symbol;
        this.id = id;
        this.playerType = playerType;
        this.scanner = scanner;
    }
    public Symbol getSymbol() {
        return symbol;
    }
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public PlayerType getPlayerType() {
        return playerType;
    }
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
    public Cell makeMove(Board board) {
        System.out.println("It is " + this.id + "'s turn");
        System.out.println("Please enter row");
        int row = scanner.nextInt();
        System.out.println("Please enter col");
        int col = scanner.nextInt();
        while(!validateMove(row, col, board)){
            System.out.println("Entered row or col is invalid");
            System.out.println("Please enter row");
            row = scanner.nextInt();
            System.out.println("Please enter col");
            col = scanner.nextInt();
        }
        Cell cell = board.getCells().get(row).get(col);
        cell.setPlayer(this);
        cell.setSymbol(symbol);
        cell.setCellState(CellState.OCCUPIED);
        return cell;
    }
    private boolean validateMove(int row, int col, Board board) {
        if(row<0 || row>=board.getSize()){
            return false;
        }
        if(col<0 || col>= board.getSize()){
            return false;
        }
        if(board.getCells().get(row).get(col).getCellState().equals(CellState.OCCUPIED)){
            return false;
        }
        return true;
    }
}
