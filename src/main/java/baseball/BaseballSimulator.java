package baseball;

import baseball.constant.BaseballSimulatorConstant;
import baseball.dto.BaseballResult;
import baseball.dto.InningResult;
import baseball.dto.PitchResult;
import baseball.generator.PitchGenerator;
import baseball.generator.RandomUniquePitchGenerator;

import java.util.*;

public abstract class BaseballSimulator {
    protected final int pitchLen;
    private final PitchGenerator pitchGenerator;

    public BaseballSimulator(int pitchLen){
        this.pitchLen = pitchLen;
        this.pitchGenerator = new RandomUniquePitchGenerator(pitchLen);
    }

    public final void simulate(){
        do{
            BaseballResult baseballResult = playBaseball();
            notifyGameResult(baseballResult);
        }while(isGameRestart());
    }

    private BaseballResult playBaseball(){
        List<Integer> simulatorPitch = pitchGenerator.generatePitches();
        System.out.println("시뮬레이션 값 : " + simulatorPitch);

        InningResult inningResult = null;
        do{
            List<Integer> userPitches = getUserPitches();
            inningResult = playInning(userPitches, simulatorPitch);
            notifyInningResult(inningResult);
        }while(!isGameEnd(inningResult));

        return new BaseballResult();
    }

    private InningResult playInning(List<Integer> userPitches, List<Integer> simulatorPitches){
        if(!pitchGenerator.isPitchesValid(userPitches) || !pitchGenerator.isPitchesValid(simulatorPitches)){
            throw new IllegalArgumentException();
        }

        InningResult inningResult = new InningResult();
        for(int pitchIdx = 0; pitchIdx < pitchLen; pitchIdx++){
            PitchResult pitchResult = playPitch(pitchIdx, userPitches.get(pitchIdx), simulatorPitches);
            inningResult.addPitchResult(pitchResult);
        }
        return inningResult;
    }

    private PitchResult playPitch(int pitchIdx, int userPitch, List<Integer> simulatorPitches){
        boolean includeStrike = (simulatorPitches.get(pitchIdx) == userPitch);
        boolean includeBall = (!includeStrike && simulatorPitches.contains(userPitch));

        return new PitchResult(includeBall, includeStrike);
    }

    private boolean isGameEnd(InningResult inningResult) {
        int strikeCnt = inningResult.getStrikeCnt();
        return (strikeCnt == pitchLen);
    }

    private boolean isGameRestart() {
        String shouldRestart = getShouldRestart();
        return shouldRestart.equals(BaseballSimulatorConstant.GAME_RESTART);
    }

    protected abstract void notifyInningResult(InningResult inningResult);

    protected abstract void notifyGameResult(BaseballResult baseballResult);

    protected abstract List<Integer> getUserPitches();

    protected abstract String getShouldRestart();
}
