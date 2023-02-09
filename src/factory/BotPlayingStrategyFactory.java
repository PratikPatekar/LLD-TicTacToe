package factory;

import models.BotDifficultyLevel;
import strategies.botplayingstrategy.BotPlayingStrategy;
import strategies.botplayingstrategy.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getStrategyForDifficultyLevel(BotDifficultyLevel difficultyLevel){
        // Currently have only 1 playing strategy so no if/else required, implement it here once created newer bot playing strategies
        return new RandomBotPlayingStrategy();
    }
}
