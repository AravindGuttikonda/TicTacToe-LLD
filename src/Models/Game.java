package Models;

import Exceptions.DuplicateSymbolException;
import Exceptions.MoreThanOneBotException;
import Exceptions.PlayersAndBoardSizeMisMatchException;
import Strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class Game {
    private int size;
    private List<Player> players;
    private Board board;
    private int nextPlayerIndex;
    private GameState gameState;
    private List<Move> moves;
    private List<WinningStrategy> winningStrategies;
    private Player winner;
    public static HashMap<Integer, HashMap<Character,Integer>> rowsMap = new HashMap<>();
    public static HashMap<Integer, HashMap<Character,Integer>> columnsMap = new HashMap<>();
    public static HashMap<Integer, HashMap<Character,Integer>> diagonalsMap = new HashMap<>();

    public Game(int size, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.size = size;
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.nextPlayerIndex = 0;
        this.board = new Board(size);
    }
    public static Builder getBuilder(){
        return new Builder();
    }

    public void undo() {
        if(moves.size()==0){
            System.out.println("No moves were made to make an undo");
            return;
        }
        if(nextPlayerIndex == 0){
            nextPlayerIndex = players.size()-1;
        }
        else {
            nextPlayerIndex--;
        }
        Move move = moves.get(moves.size()-1);
        moves.remove(move);
        Cell cell = move.getCell();
        for(WinningStrategy winningStrategy : winningStrategies){
            winningStrategy.handleUndo(board, cell);
        }
        cell.setCellState(CellState.EMPTY);
        cell.setSymbol(null);
        cell.setPlayer(null);
    }

    public static class Builder{
        private int size;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        public Builder setSize(int size){
            this.size = size;
            return this;
        }
        public Builder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }
        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies){
            this.winningStrategies = winningStrategies;
            return this;
        }
        public Game build() throws MoreThanOneBotException, DuplicateSymbolException, PlayersAndBoardSizeMisMatchException {
            validateBotCount();
            validateDuplicateSymbols();
            validatePlayersCountAndBoardSize();
            return new Game(size,players,winningStrategies);
        }
        private void validateBotCount() throws MoreThanOneBotException {
            int bots = 0;
            for(Player player : players){
                if(player.getPlayerType() == PlayerType.BOT){
                    bots++;
                }
            }
            if(bots>1){
                throw new MoreThanOneBotException();
            }
        }
        private void validateDuplicateSymbols() throws DuplicateSymbolException {
            HashSet<Character> hs = new HashSet<>();
            for(Player player : players){
                if(hs.contains(player.getSymbol().getCh())){
                    throw new DuplicateSymbolException();
                }
                else{
                    hs.add(player.getSymbol().getCh());
                }
            }
        }
        private void validatePlayersCountAndBoardSize() throws PlayersAndBoardSizeMisMatchException {
            if(players.size()!=size-1){
                throw new PlayersAndBoardSizeMisMatchException();
            }
        }
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }
    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public List<Move> getMoves() {
        return moves;
    }
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }
    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public void displayBoard() {
        board.displayBoard();
    }
    public void makeMove() {
        Player player = players.get(nextPlayerIndex);
        Cell cell = player.makeMove(board);
        Move move = new Move(cell, player, cell.getSymbol());
        moves.add(move);
        if(checkWinner(move)){
            gameState = GameState.COMPLETED;
            winner = player;
            System.out.println("Hurray! " + player.getId() + " has won");
            return;
        }
        if(moves.size()== board.getSize()* board.getSize()){
            gameState = GameState.DRAW;
            System.out.println("It's a draw");
            return;
        }
        nextPlayerIndex++;
        nextPlayerIndex = nextPlayerIndex % players.size();
    }
    private boolean checkWinner(Move move) {
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board, move)){
                return true;
            }
        }
        return false;
    }
}
