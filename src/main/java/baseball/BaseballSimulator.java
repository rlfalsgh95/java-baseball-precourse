package baseball;

import baseball.dto.BaseballResult;
import baseball.dto.InningResult;
import baseball.dto.PitchResult;
import util.Range;

import java.util.Arrays;

public abstract class BaseballSimulator {
    protected final int pitchLen;
    private final Range pitchRange;
    public BaseballSimulator(int pitchLen){
        this.pitchLen = pitchLen;
        this.pitchRange = new Range(pitchLen, true);
    }

    private char[] getRandomSimulatorInning(){
        int simulatorInning = (int) ((Math.random() * pitchRange.getHigh()) + pitchRange.getLow());
        String simulatorInningFormatted = String.format("%" + "0" + pitchLen + "d", simulatorInning);
        return simulatorInningFormatted.toCharArray();
    }

    protected boolean userPitchIsValid(String userPitch){
        boolean ExceedNumberPitches = (userPitch.length() != pitchLen);

        boolean isDigit = true;
        for(int pitchIdx = 0; pitchIdx < userPitch.length(); pitchIdx++){
            if(!Character.isDigit(userPitch.charAt(pitchIdx))) isDigit = false;
        }

        return (!ExceedNumberPitches && isDigit);
    }

    private boolean isGameEnd(InningResult inningResult) {
        int strikeCnt = inningResult.getStrikeCnt();
        return (strikeCnt == pitchLen);
    }

    protected boolean isGameRestart(String userInput) {
        return userInput.equals("1");
    }

    protected PitchResult playPitch(char userPitch, char simulatorPitch){
        if(simulatorPitch == userPitch){
            return new PitchResult(0, 1);
        }
        return new PitchResult(1, 0);
    }

    private InningResult playInning(char[] userPitches, char[] simulatorPitches){
        InningResult inningResult = new InningResult(0, 0);

        if(userPitches.length != simulatorPitches.length){
        }

        for(int pitchIdx = 0; pitchIdx < pitchLen; pitchIdx++){
            PitchResult pitchResult = playPitch(userPitches[pitchIdx], simulatorPitches[pitchIdx]);
            inningResult.addPitchResult(pitchResult);
        }
        return inningResult;
    }

    private BaseballResult playBaseball(){
        char[] simulatorPitch = getRandomSimulatorInning();
        System.out.println("시뮬레이션 값 : " + Arrays.toString(simulatorPitch));

        InningResult inningResult = null;
        do{
            char[] userPitch = getUserPitch();
            inningResult = playInning(userPitch, simulatorPitch);
            notifyInningResult(inningResult);
        }while(!isGameEnd(inningResult));

        return new BaseballResult();
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
