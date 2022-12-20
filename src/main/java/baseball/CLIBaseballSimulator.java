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
        System.out.printf("%d자리 숫자를 입력해주세요 : ", super.pitchLen);
    }

    private void displayGameRestart() {

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
    }

    @Override
    protected boolean getShouldRestart() {
       return false;
    }

    @Override
    protected char[] getUserPitch() {
        String userPitch = null;
        do{ // write 또는 read 하는 과정에서 에러가 발생한다면, 게임을 종료시켜야 함 (계속해서 진행한다면, 무한 루프에 빠질 수 있음)
            displayUserPitchInput();
            userPitch = scanner.nextLine();
        }while(!userPitchIsValid(userPitch));

        return userPitch.toCharArray();
    }
}
