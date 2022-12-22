package baseball;

import baseball.cli.CLIBaseballSimulator;

public class Application {
    public static void main(String[] args) {
        final int numberPitches = 3;
        BaseballSimulator baseBallGameSimulator = new CLIBaseballSimulator(numberPitches);
        baseBallGameSimulator.simulate();
    }
}
