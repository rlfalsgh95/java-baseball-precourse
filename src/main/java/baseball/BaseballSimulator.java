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

    private List<Character> getRandomSimulatorPitches(){
        Set<Integer> uniquePitches = makeUniquePitches();
        List<Character> simulatorPitches = new LinkedList<>();
        for(int pitch : uniquePitches){
            simulatorPitches.add(Character.forDigit(pitch, BaseballConstant.PITCH_RADIX));
        }

        return simulatorPitches;
    }

    protected boolean isPatchesValid(List<Character> pitches){
        boolean ExceedPitchesLen = (pitches.size() != pitchLen);

        boolean isDigit = true;
        for(int pitchIdx = 0; pitchIdx < pitches.size(); pitchIdx++){
            if(!Character.isDigit(pitches.get(pitchIdx))) isDigit = false;
        }

        return (!ExceedPitchesLen && isDigit);
    }

    private boolean isGameEnd(InningResult inningResult) {
        int strikeCnt = inningResult.getStrikeCnt();
        return (strikeCnt == pitchLen);
    }

    protected boolean isGameRestart(String userInput) { // TODO : userInput - > shouldRestart
        return userInput.equals(BaseballConstant.GAME_RESTART);
    }

    protected PitchResult playPitch(int pitchIdx, char userPitch, List<Character> simulatorPitches){
        boolean isStrike = (simulatorPitches.get(pitchIdx) == userPitch);
        boolean isBall = (!isStrike && simulatorPitches.contains(userPitch));

        return new PitchResult(isBall, isStrike);
    }

    private InningResult playInning(List<Character> userPitches, List<Character> simulatorPitches){
        InningResult inningResult = new InningResult();

        if(!isPatchesValid(userPitches) || !isPatchesValid(simulatorPitches)){
            throw new IllegalArgumentException();
        }

        for(int pitchIdx = 0; pitchIdx < pitchLen; pitchIdx++){
            PitchResult pitchResult = playPitch(pitchIdx, userPitches.get(pitchIdx), simulatorPitches);
            inningResult.addPitchResult(pitchResult);
        }
        return inningResult;
    }

    private BaseballResult playBaseball(){
        List<Character> simulatorPitch = getRandomSimulatorPitches();
        System.out.println("시뮬레이션 값 : " + simulatorPitch);

        InningResult inningResult = null;
        do{
            List<Character> userPitches = getUserPitches();
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

    protected abstract List<Character> getUserPitches();

    protected abstract boolean getShouldRestart();
}
