package baseball;

import baseball.constant.BaseballConstant;
import baseball.dto.BaseballResult;
import baseball.dto.InningResult;
import baseball.dto.PitchResult;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public abstract class BaseballSimulator {
    protected final int pitchLen;
    public BaseballSimulator(int pitchLen){
        this.pitchLen = pitchLen;
    }

    private Set<Integer> makeUniquePitches(){
        Set<Integer> uniquePitches = new LinkedHashSet<>();
        while(uniquePitches.size() < pitchLen){
            int pickedPitch = Randoms.pickNumberInRange(BaseballConstant.MIN_PITCH_VALUE, BaseballConstant.MAX_PITCH_VALUE);
            uniquePitches.add(pickedPitch);
        }

        return uniquePitches;
    }

    private List<Integer> getRandomSimulatorPitches(){
        Set<Integer> uniquePitches = makeUniquePitches();
        List<Integer> simulatorPitches = new LinkedList<>(uniquePitches);

        return simulatorPitches;
    }

    protected boolean isPitchesValid(List<Integer> pitches){
        boolean ExceedPitchesLen = (pitches.size() != pitchLen);

        return (!ExceedPitchesLen);
    }

    private boolean isGameEnd(InningResult inningResult) {
        int strikeCnt = inningResult.getStrikeCnt();
        return (strikeCnt == pitchLen);
    }

    protected boolean isGameRestart(String userInput) { // TODO : userInput - > shouldRestart
        return userInput.equals(BaseballConstant.GAME_RESTART);
    }

    protected PitchResult playPitch(int pitchIdx, int userPitch, List<Integer> simulatorPitches){
        boolean isStrike = (simulatorPitches.get(pitchIdx) == userPitch);
        boolean isBall = (!isStrike && simulatorPitches.contains(userPitch));

        return new PitchResult(isBall, isStrike);
    }

    private InningResult playInning(List<Integer> userPitches, List<Integer> simulatorPitches){
        InningResult inningResult = new InningResult();

        if(!isPitchesValid(userPitches) || !isPitchesValid(simulatorPitches)){
            throw new IllegalArgumentException();
        }

        for(int pitchIdx = 0; pitchIdx < pitchLen; pitchIdx++){
            PitchResult pitchResult = playPitch(pitchIdx, userPitches.get(pitchIdx), simulatorPitches);
            inningResult.addPitchResult(pitchResult);
        }
        return inningResult;
    }

    private BaseballResult playBaseball(){
        List<Integer> simulatorPitch = getRandomSimulatorPitches();
        System.out.println("시뮬레이션 값 : " + simulatorPitch);

        InningResult inningResult = null;
        do{
            List<Integer> userPitches = getUserPitches();
            inningResult = playInning(userPitches, simulatorPitch);
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

    protected abstract List<Integer> getUserPitches();

    protected abstract boolean getShouldRestart();
}
