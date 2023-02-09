import controllers.GameController;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello world! Welcome to TicTacToe Game");

        GameController gameController = new GameController();

        System.out.println("Enter Dimension of the Board: ");
        int dimension = scanner.nextInt();

        System.out.println("Will there be any bots ? y/n");
        String isBot = scanner.next();

        List<Player> players = new ArrayList<>();

        int toIterate = dimension-1;

        if(isBot.equals("y")){
            toIterate = dimension-2;
            System.out.println("What is the name of Bot ?");
            String botName = scanner.next();

            System.out.println("What is the symbol of Bot ?");
            String botSymbol = scanner.next();

            players.add(new Bot(botSymbol.charAt(0),botName,BotDifficultyLevel.EASY));
        }

        for(int i=0;i<toIterate;i++){
            System.out.println("What is the name of player "+i);
            String playerName = scanner.next();

            System.out.println("What is the symbol of player "+i);
            String playerSymbol = scanner.next();

            players.add(new Player(playerSymbol.charAt(0),playerName,PlayerType.HUMAN));
        }

        Game game = gameController.createGame(dimension,players);

        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){

            System.out.println("This is the current board: ");
            gameController.displayBoard(game);

            // TODO: add code to throw exception when undo is done on the first move
            System.out.println("Do you want to Undo ? y/n");
            String input = scanner.next();

            if(input.equals("y")){
                gameController.undo(game);
            }else{
                gameController.executeNextMove(game);
            }
        }

        System.out.println("Game has ended, Result is: ");
        if(!game.getGameStatus().equals(GameStatus.DRAW)){

            System.out.println("The winner is "+gameController.getWinner(game).getName());
        }else{
            //TODO: Maintain count of moves to check whether its a draw. But be careful while implementing UNDO.
            System.out.println("It's a Draw!");
        }

    }
}