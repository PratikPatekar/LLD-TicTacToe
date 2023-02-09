package strategies.gamewinningstrategy;

import models.Board;
import models.Cell;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy{
    List<HashMap<Character,Integer>> rowSymbolCount = new ArrayList<>();
    List<HashMap<Character,Integer>> colSymbolCount = new ArrayList<>();
    HashMap<Character,Integer> topLeftDiagSymbolCount = new HashMap<>();
    HashMap<Character,Integer> topRightDiagSymbolCount = new HashMap<>();
    public OrderOneGameWinningStrategy(int dimension){
        for(int i=0;i<dimension;i++){
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }

    public boolean isCellOnTopLeftDiag(int row,int col){
        return row==col;
    }

    public boolean isCellOnTopRightDiag(int row,int col,int dimension){
        return row+col==dimension-1;
    }
    public boolean checkWinner(Board board, Player player, Cell cell){
        char symbol = cell.getPlayer().getSymbol();
        int row = cell.getRow();
        int col = cell.getCol();
        int dimension = board.getBoard().size();

        if(!rowSymbolCount.get(row).containsKey(symbol)){
            rowSymbolCount.get(row).put(symbol,0);
        }
        rowSymbolCount.get(row).put(symbol,rowSymbolCount.get(row).get(symbol)+1);

        if(!colSymbolCount.get(col).containsKey(symbol)){
            colSymbolCount.get(col).put(symbol,0);
        }
        colSymbolCount.get(col).put(symbol,colSymbolCount.get(col).get(symbol)+1);

        if(isCellOnTopLeftDiag(row,col)){
            if(!topLeftDiagSymbolCount.containsKey(symbol)){
                topLeftDiagSymbolCount.put(symbol,0);
            }
            topLeftDiagSymbolCount.put(symbol,topLeftDiagSymbolCount.get(symbol)+1);
        }

        if(isCellOnTopRightDiag(row,col,dimension)){
            if(!topRightDiagSymbolCount.containsKey(symbol)){
                topRightDiagSymbolCount.put(symbol,0);
            }
            topRightDiagSymbolCount.put(symbol,topRightDiagSymbolCount.get(symbol)+1);
        }

        if(rowSymbolCount.get(row).get(symbol)==dimension)return true;
        if(colSymbolCount.get(col).get(symbol)==dimension)return true;
        if(isCellOnTopLeftDiag(row,col) && topLeftDiagSymbolCount.get(symbol)==dimension)return true;
        if(isCellOnTopRightDiag(row,col,dimension) && topRightDiagSymbolCount.get(symbol)==dimension)return true;

        return false;
    }
}
