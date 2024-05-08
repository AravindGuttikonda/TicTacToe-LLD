package Models;

public class Move {
    private Cell cell;
    private Player player;
    private Symbol symbol;
    public Move(Cell cell, Player player, Symbol symbol) {
        this.cell = cell;
        this.player = player;
        this.symbol = symbol;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
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
}
