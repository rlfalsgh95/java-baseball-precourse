package baseball;

import baseball.dto.BaseballResult;
import baseball.dto.InningResult;

import java.util.Scanner;

public class CLIBaseballSimulator extends BaseballSimulator{
    private final Scanner scanner;

    public CLIBaseballSimulator(int numberPitches){
        super(numberPitches);
        this.scanner = new Scanner(System.in);
    }

    private void displayUserPitchInput() {

    }

    private void displayGameRestart() {

    }

    @Override
    protected void notifyInningResult(InningResult inningResult) {
    }

    @Override
    protected void notifyGameResult(BaseballResult baseballResult) {
    }

    @Override
    protected boolean getShouldRestart() {
       return false;
    }

    @Override
    protected char[] getUserPitch() {
        return null;
    }
}
