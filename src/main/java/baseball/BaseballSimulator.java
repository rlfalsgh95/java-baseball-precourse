package baseball;

import baseball.constant.BaseballConstant;
import baseball.dto.BaseballResult;
import baseball.dto.InningResult;
import baseball.dto.PitchResult;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;

public abstract class BaseballSimulator {
    protected final int pitchLen;
    public BaseballSimulator(int pitchLen){
        this.pitchLen = pitchLen;
    }

    private char[] getRandomSimulatorPitches(){
        char[] simulatorPitches = new char [pitchLen];

        for(int pitchIdx = 0; pitchIdx < pitchLen; pitchIdx++){
            int pickedPitch = Randoms.pickNumberInRange(BaseballConstant.MIN_PITCH_VALUE, BaseballConstant.MAX_PITCH_VALUE);
            simulatorPitches[pitchIdx] = Character.forDigit(pickedPitch, 10);
        }

        return simulatorPitches;
    }

    protected boolean pitchesIsValid(char[] pitches){
        boolean ExceedPitchesLen = (pitches.length != pitchLen);

        boolean isDigit = true;
        for(int pitchIdx = 0; pitchIdx < pitches.length; pitchIdx++){
            if(!Character.isDigit(pitches[pitchIdx])) isDigit = false;
        }

        return (!ExceedPitchesLen && isDigit);
    }

    private boolean isGameEnd(InningResult inningResult) {
        int strikeCnt = inningResult.getStrikeCnt();
        return (strikeCnt == pitchLen);
    }

    protected boolean isGameRestart(String userInput) {
        return userInput.equals(BaseballConstant.GAME_RESTART);
    }

    protected PitchResult playPitch(char userPitch, char simulatorPitch){
        if(simulatorPitch == userPitch){
            return new PitchResult(0, 1);
        }
        return new PitchResult(1, 0);
    }

    private InningResult playInning(char[] userPitches, char[] simulatorPitches){
        InningResult inningResult = new InningResult();

        if(userPitches.length != simulatorPitches.length){
        }

        for(int pitchIdx = 0; pitchIdx < pitchLen; pitchIdx++){
            PitchResult pitchResult = playPitch(userPitches[pitchIdx], simulatorPitches[pitchIdx]);
            inningResult.addPitchResult(pitchResult);
        }
        return inningResult;
    }

    private BaseballResult playBaseball(){
        char[] simulatorPitch = getRandomSimulatorPitches();
        System.out.println("시뮬레이션 값 : " + Arrays.toString(simulatorPitch));

        InningResult inningResult = null;
        do{
            char[] userPitch = getUserPitches();
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

    protected abstract char[] getUserPitches();

    protected abstract boolean getShouldRestart();
}
