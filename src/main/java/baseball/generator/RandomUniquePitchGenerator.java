package baseball.generator;

import baseball.constant.BaseballSimulatorConstant;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class RandomUniquePitchGenerator implements PitchGenerator{
    private final int pitchLen;

    public RandomUniquePitchGenerator(int pitchLen){
        this.pitchLen = pitchLen;
    }

    public List<Integer> generatePitches(){
        Set<Integer> uniquePitches = generateUniquePitchValues();
        return new LinkedList<>(uniquePitches);
    }

    public boolean isPitchesValid(List<Integer> pitches){
        boolean hasDuplication = isPitchesHasDuplication(pitches);
        boolean isLenEqualToDefinedPitchLen = isPitchLenEqualToDefinedPitchLen(pitches);
        boolean withInRange = isPitchWithInRange(pitches);

        return (!hasDuplication && isLenEqualToDefinedPitchLen && withInRange);
    }

    private Set<Integer> generateUniquePitchValues(){
        Set<Integer> uniquePitches = new LinkedHashSet<>();
        while(uniquePitches.size() < pitchLen){
            int pickedPitch = Randoms.pickNumberInRange(PitchGeneratorConstant.MIN_PITCH_VALUE, PitchGeneratorConstant.MAX_PITCH_VALUE);
            uniquePitches.add(pickedPitch);
        }

        return uniquePitches;
    }

    private boolean isPitchLenEqualToDefinedPitchLen(List<Integer> pitches){
        return (pitches.size() == pitchLen);
    }

    private boolean isPitchesHasDuplication(List<Integer> pitches){
        Set<Integer> uniquePitches = new HashSet<>(pitches);
        return !(uniquePitches.size() == pitches.size());
    }

    private boolean isPitchWithInRange(List<Integer> pitches){
        boolean withInRange = true;

        for (int pitch : pitches) {
            if (pitch < PitchGeneratorConstant.MIN_PITCH_VALUE
                    || pitch > PitchGeneratorConstant.MAX_PITCH_VALUE) {
                withInRange = false;
            }
        }

        return withInRange;
    }
}
