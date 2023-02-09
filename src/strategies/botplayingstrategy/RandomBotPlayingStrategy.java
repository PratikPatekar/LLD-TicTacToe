package strategies.botplayingstrategy;

import models.*;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{

    // Simplest Bot playing strategy, just finds first empty cell and fills it
    @Override
    public Move decideMove(Player player, Board board) {
        int dimension = board.getBoard().size();
        for(int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    return new Move(player,new Cell(i,j));
                }
            }
        }
        return null;
    }
}
