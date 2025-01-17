package baseball.dto;

public class PitchResult {
    private int ballCnt, strikeCnt;
    public PitchResult(){
        this.ballCnt = 0;
        this.strikeCnt = 0;
    }

    public PitchResult(boolean includeBall, boolean includeStrike){
        if(includeStrike){
            this.strikeCnt = 1;
        }
        if(includeBall){
            this.ballCnt = 1;
        }
    }

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

    public void addBall(){
        this.ballCnt++;
    }

    public void addStrike(){
        this.strikeCnt++;
    }
}
