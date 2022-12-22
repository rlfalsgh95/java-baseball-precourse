package baseball.cli;

import baseball.BaseballSimulator;
import baseball.dto.BaseballResult;
import baseball.dto.InningResult;
import baseball.generator.PitchGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CLIBaseballSimulator extends BaseballSimulator{
    private final Scanner scanner;

    public CLIBaseballSimulator(int pitchLen, PitchGenerator pitchGenerator){
        super(pitchLen, pitchGenerator);
        this.scanner = new Scanner(System.in);
    }

    private void displayUserPitchInput() {
        System.out.printf("%d자리 숫자를 입력해주세요 : ", super.pitchLen);
    }

    private void displayGameRestart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    }

    private boolean isUserPitchValid(String userPitchInput){
        boolean isDigit = true;
        for(int pitchIdx = 0; pitchIdx < userPitchInput.length(); pitchIdx++){
            if(!Character.isDigit(userPitchInput.charAt(pitchIdx))){
                isDigit = false;
            }
        }
        return isDigit;
    }

    private List<Integer> convertUserPitchToIntegerList(String userPitchInput){
        List<Integer> userPitches = new LinkedList<>();
        for(int pitchIdx = 0; pitchIdx < userPitchInput.length(); pitchIdx++){
            char pitch = userPitchInput.charAt(pitchIdx);
            userPitches.add(Character.getNumericValue(pitch));
        }

        return userPitches;
    }

    @Override
    protected void notifyInningResult(InningResult inningResult) {
        String inningResultMsg = inningResult.getResultMsg();
        System.out.println(inningResultMsg);
    }

    @Override
    protected void notifyGameResult(BaseballResult baseballResult) {
        System.out.printf("%d개의 숫자를 모두 맞히셨습니다! 게임 종료\n", super.pitchLen);
    }

    @Override
    protected boolean getShouldRestart() {
        displayGameRestart();
        String shouldRestart = scanner.nextLine();
        return isGameRestart(shouldRestart);
    }

    @Override
    protected List<Integer> getUserPitches() {
        displayUserPitchInput();
        String userPitchInput = scanner.nextLine();

        if(!isUserPitchValid(userPitchInput)){
            throw new IllegalArgumentException();
        }

        return convertUserPitchToIntegerList(userPitchInput);
    }
}
