package baseball.dto;

public class InningResult {
    private final static String NOTHING_MSG = "낫싱";
    private final static String BALL_MSG = "볼";
    private final static String STRIKE_MSG = "스트라이크";

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

    public boolean isNothing(){
        return (this.ballCnt == 0 && this.strikeCnt == 0);
    }

    public boolean includesBall(){
        return (this.ballCnt != 0);
    }

    public boolean includesStrike(){
        return (this.strikeCnt != 0);
    }

    public void addPitchResult(PitchResult pitchResult){
        this.ballCnt += pitchResult.getBallCnt();
        this.strikeCnt += pitchResult.getStrikeCnt();
    }

    public String getResultMsg(){
        if(this.isNothing()){
            return NOTHING_MSG;
        }

        StringBuilder resultMsgBuilder = new StringBuilder();
        if(this.includesBall()){
            resultMsgBuilder.append(this.ballCnt + BALL_MSG + " ");
        }

        if(this.includesStrike()){
            resultMsgBuilder.append(this.strikeCnt + STRIKE_MSG);
        }

        return resultMsgBuilder.toString();
    }
    @Override
    public String toString() {
        if(this.isNothing()){
            return NOTHING_MSG;
        }

        return this.ballCnt + BALL_MSG
                + " "
                + this.strikeCnt + STRIKE_MSG;
    }
}