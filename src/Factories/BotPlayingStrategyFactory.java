package Factories;

import Models.BotDifficultyLevel;
import Strategies.BotPlayingStrategy;
import Strategies.EasyPlayingStrategy;
import Strategies.HardPlayingStrategy;
import Strategies.MediumPlayingStrategy;
import jdk.jshell.spi.ExecutionControl;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) throws ExecutionControl.NotImplementedException {
        if(botDifficultyLevel == BotDifficultyLevel.EASY){
            return new EasyPlayingStrategy();
        } else if (botDifficultyLevel == BotDifficultyLevel.MEDIUM) {
            return new MediumPlayingStrategy();
        } else if(botDifficultyLevel == BotDifficultyLevel.HARD){
            return new HardPlayingStrategy();
        }
        else{
            throw new ExecutionControl.NotImplementedException("This Strategy is not Supported");
        }
    }
}
