package models;

import factory.BotPlayingStrategyFactory;
import strategies.botplayingstrategy.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botdifficultylevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(char symbol,String name,BotDifficultyLevel difficultyLevel){
        super(symbol,name,PlayerType.BOT);
        this.botdifficultylevel = difficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getStrategyForDifficultyLevel(difficultyLevel);
    }
    public BotDifficultyLevel getBotDifficultyLevel() {
        return botdifficultylevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botdifficultylevel) {
        this.botdifficultylevel = botdifficultylevel;
    }

    @Override
    public Move decideMove(Board board) {
        return botPlayingStrategy.decideMove(this,board);
    }
}
