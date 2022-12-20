package baseball.dto;

public class PitchResult {
    private final int ballCnt, strikeCnt;
    public PitchResult(int ballCnt, int strikeCnt){
        this.ballCnt = ballCnt;
        this.strikeCnt = strikeCnt;
    }

    public int getBallCnt(){
        return this.ballCnt;
    }

    public int getStrikeCnt(){
        return this.strikeCnt;
    }
}
