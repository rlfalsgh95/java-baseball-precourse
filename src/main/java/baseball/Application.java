package baseball;

import baseball.cli.CLIBaseballSimulator;
import baseball.generator.RandomUniquePitchGenerator;

public class Application {
    public static void main(String[] args) {
        final int numberPitches = 3;
        BaseballSimulator baseBallGameSimulator = new CLIBaseballSimulator(numberPitches, new RandomUniquePitchGenerator(numberPitches, 1, 9));
        baseBallGameSimulator.simulate();
    }
}
