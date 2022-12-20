package baseball;

import baseball.dto.BaseballResult;
import baseball.dto.InningResult;
import baseball.dto.PitchResult;
import util.Range;

public abstract class BaseballSimulator {
    protected final int pitchLen;
    private final Range pitchRange;
    public BaseballSimulator(int pitchLen){
        this.pitchLen = pitchLen;
        this.pitchRange = new Range(pitchLen, true);
    }

    private char[] getRandomSimulatorInning(){
        return null;
    }

    protected boolean userPitchIsValid(String userPitch){
        return false;
    }

    private boolean isGameEnd(InningResult inningResult) {
        return false;
    }

    protected boolean isGameRestart(String userInput) {
        return false;
    }

    protected PitchResult playPitch(char userPitch, char simulatorPitch){
        return null;
    }

    private InningResult playInning(char[] userPitches, char[] simulatorPitches){
        return null;
    }

    private BaseballResult playBaseball(){
        return null;
    }

    public final void simulate(){
        do{
            BaseballResult baseballResult = playBaseball();
            notifyGameResult(baseballResult);
        }while(getShouldRestart());
    }

    protected abstract void notifyInningResult(InningResult inningResult);

    protected abstract void notifyGameResult(BaseballResult baseballResult);

    protected abstract char[] getUserPitch();

    protected abstract boolean getShouldRestart();
}
