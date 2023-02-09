package models;

import exceptions.InvalidGameConstructionParametersException;
import strategies.gamewinningstrategy.GameWinningStrategy;
import strategies.gamewinningstrategy.OrderOneGameWinningStrategy;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class Game {
    private List<Player>players;
    private Board board;
    private List<Move>moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private GameWinningStrategy gameWinningStrategy;
    private Player winner;

    private Game(){
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    //TODO: Implement UNDO here
    public void undo(){

    }

    public void displayBoard(){
        board.display();
    }
    public void makeNextMove(){
        Player toMovePlayer = players.get(nextPlayerIndex);
        System.out.println("It is "+players.get(nextPlayerIndex).getName()+"'s turn");
        Move move = toMovePlayer.decideMove(this.board);
        // TODO: validate move here
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        System.out.println("Played at "+row+", "+col);
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(players.get(nextPlayerIndex));
        Move finalMove = new Move(players.get(nextPlayerIndex),board.getBoard().get(row).get(col));
        this.moves.add(finalMove);

        if(gameWinningStrategy.checkWinner(board,players.get(nextPlayerIndex),finalMove.getCell())){
            gameStatus = GameStatus.ENDED;
            winner = players.get(nextPlayerIndex);
        }
        //TODO: if !winner and no. of moves==n*n then its a draw. But be careful while implementing UNDO.
        nextPlayerIndex=(nextPlayerIndex+1)%players.size();
    }
    public static Builder getBuilder(){
        return new Builder();
    }
    public static class Builder{
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        //Create Exceptions Class and Throw Custom Exceptions
        private boolean valid() throws InvalidGameConstructionParametersException {
            if(this.dimension<3){
                throw new InvalidGameConstructionParametersException("Dimension cannot be less than 3");
            }
            if(this.players.size()!=this.dimension-1){
                throw new InvalidGameConstructionParametersException("No. of Players must be equal to dimension-1");
            }
            // Add other validations like min 1 player and a bot, no 2 people with same symbol etc.
            return true;
        }

        public Game build() throws InvalidGameConstructionParametersException {
            try{
                valid();
            }catch(Exception e){
                throw new InvalidGameConstructionParametersException(e.getMessage());
            }
            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setBoard(new Board(dimension));
            game.setNextPlayerIndex(0);
            // Hardcoding for now, can be taken from user as input or an obj can be created based on certain constraints
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));
            return game;
        }
    }

}
