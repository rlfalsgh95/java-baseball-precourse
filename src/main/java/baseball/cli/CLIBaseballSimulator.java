package baseball.cli;

import baseball.BaseballSimulator;
import baseball.dto.BaseballResult;
import baseball.dto.InningResult;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CLIBaseballSimulator extends BaseballSimulator{
    private final Scanner scanner;

    public CLIBaseballSimulator(int pitchLen){
        super(pitchLen);
        this.scanner = new Scanner(System.in);
    }

    private void displayUserPitchInput() {
        System.out.printf("%d자리 숫자를 입력해주세요 : ", super.pitchLen);
    }

    private void displayGameRestart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    }

    @Override
    protected void notifyInningResult(InningResult inningResult) {
        if(inningResult.isNothing()){
            System.out.print("낫싱");
        }
        if(inningResult.getBallCnt() != 0){
            System.out.print(inningResult.getBallCnt() + "볼");
            System.out.print(" ");
        }
        if(inningResult.getStrikeCnt() != 0){
            System.out.print(inningResult.getStrikeCnt() + "스트라이크");
        }

        System.out.println();
    }

    @Override
    protected void notifyGameResult(BaseballResult baseballResult) {
        System.out.println(super.pitchLen + "개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    @Override
    protected boolean getShouldRestart() {
        displayGameRestart();
        String shouldRestart = scanner.nextLine();
        return isGameRestart(shouldRestart);
    }

    @Override
    protected List<Character> getUserPitches() {
        displayUserPitchInput();
        String userPitchInput = scanner.nextLine();
        List<Character> userPitches = new LinkedList<>();

        for(Character pitch : userPitchInput.toCharArray()){
            userPitches.add(pitch);
        }

        if(!isPatchesValid(userPitches)){
            throw new IllegalArgumentException();
        }

        return userPitches;
    }
}
