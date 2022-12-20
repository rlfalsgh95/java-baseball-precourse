package util;

public class Range {
    private final int low, high;

    public Range(){
        this.low = 0;
        this.high = 0;
    }

    public Range(int digitNums, boolean unsigned){
        this.high = (int) Math.pow(10, digitNums) - 1;
        this.low = (unsigned) ? 0 : (-1 * digitNums);
    }

    public boolean includes(int targetNum){
        return (low >= targetNum && high <= targetNum);
    }

    public int getLow(){
        return this.low;
    }

    public int getHigh(){
        return this.high;
    }
}
