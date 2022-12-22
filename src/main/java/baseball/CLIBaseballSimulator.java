package baseball;

import baseball.dto.BaseballResult;
import baseball.dto.InningResult;

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
        int ballCnt = inningResult.getBallCnt();
        int strikeCnt = inningResult.getStrikeCnt();

        if(strikeCnt != 0){
            System.out.print(strikeCnt + " 스트라이크 ");
        }
        if(ballCnt != 0){
            System.out.print(ballCnt + " 볼");
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
    protected char[] getUserPitches() {
        char[] userPitches = null;
        displayUserPitchInput();
        String userPitchInput = scanner.nextLine();
        userPitches = userPitchInput.toCharArray();

        if(!pitchesIsValid(userPitches)){
            throw new IllegalArgumentException();
        }

        return userPitches;
    }
}
