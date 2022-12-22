package baseball.dto;

public class InningResult {
    private int ballCnt, strikeCnt;
    public InningResult(){
        this.ballCnt = 0;
        this.strikeCnt = 0;
    }

    public InningResult(int ballCnt, int strikeCnt){
        this.ballCnt = ballCnt;
        this.strikeCnt = strikeCnt;
    }

    public InningResult(InningResult inningResult){
        this.ballCnt = inningResult.ballCnt;
        this.strikeCnt = inningResult.strikeCnt;
    }

    public int getBallCnt(){
        return this.ballCnt;
    }

    public int getStrikeCnt(){
        return this.strikeCnt;
    }

    public void addPitchResult(PitchResult pitchResult){
        this.ballCnt += pitchResult.getBallCnt();
        this.strikeCnt += pitchResult.getStrikeCnt();
    }
}