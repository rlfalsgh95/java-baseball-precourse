package baseball.generator;

import java.util.List;

public interface PitchGenerator {
    List<Integer> generatePitches();

    boolean isPitchesValid(List<Integer> pitches);
}
