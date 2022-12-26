# 숫자 야구 게임
## 진행 방법
* 숫자 야구 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 과제를 제출한다.

## 과제 제출 과정
* [과제 제출 방법](https://github.com/next-step/nextstep-docs/tree/master/precourse)

## 📘 Dictionary
- Inning : 시뮬레이터의 숫자와 유저의 숫자를 비교하는 과정을 inning에 비유 (ex. 123)
- pitch : "투구"의 의미로, 각 숫자를 의미 (ex. 123의 1, 2, 3)

## 🏛️ Structure
  - src
    - test/java/baseball
      - ApplicationTest.java
    - main
      - utll
        - StringUtils.java : 문자열과 관련된 유틸 클래스
      - baseball
        - cil
          - CLIBaseballSimulator.java : CLI 환경에서 숫자 야구 게임을 하기 위한 클래스
        - constant
          - BaseballSimulatorConstant.java : 숫자 야구 게임의 상수를 저장하는 클래스
        - dto
          - BaseballResult.java : 야구 게임의 결과를 저장하는 dto
          - InningResult.java : 각 inning의 결과를 저장하는 dto
          - PitchResult.java : 각 pitch의 결과를 저장하는 dto
        - generator
          - PitchGenerator.java : Pitch를 생성/검증하기 위한 인터페이스 
          - RandomUniquePitchGenerator.java : 랜덤으로 Pitch를 생성/검증하는 클래스
        - Application.java
        - BaseballSimulator.java : 숫자 야구 게임을 정의한 추상 클래스
      
## 📄 Description
  - 입력/출력은 GUI, API... 등으로 확장될 수 있다고 생각되어 자식 클래스에서 오버라이딩하도록 구현함. (템플릿 메서드 패턴)
  - 시뮬레이터에서 Pitch를 생성/검증하는 클래스를 분리함. (전략 패턴)
  - 숫자의 길이가 3으로 고정되지 않도록 구현함
  - game/inning/pitch의 결과를 메서드간 주고 받을 때, dto를 사용함.

## ❓ Question
  - BaseballSimulator와 PitchGenerator의 결합도가 높아보임, 과연 분리하는 게 옳았는가?
  - 시뮬레이터의 설정 값이 늘어날 수 있을 것 같아 SimulatorConfig와 같은 클래스를 생성자로 받는 것을 고려해봄. 하지만, 결합도가 높아질 것 같음.
  - 상수 클래스를 따로 정의하는 것이 좋은지? 마찬가지로, 결합도가 높아질 것 같음. 
  - CLIBaseballSimulator의 출력 메시지도 상수로 빼놓는 것이 좋은지? 하지만, 메시지에 변수가 포함되어 빼기가 힘듦. 
  - RandomUniquePitchGenerator.isPitchWithInRange()처럼, 배열 내의 특정 요소에 대해 동작하는 코드는 어떻게 작성?
  - 메서드의 반환 값을 변수에 넣고 반환하는 것이 아니라, 메서드의 반환을 바로 반환하여도 좋은가? (ex. CLIBaseballSimulator.getUserPitches())
  - Stream의 사용을 지양하는 이유는? 디버깅의 어려움?
  
## Learned
    처음엔 쉬운 과제라고 생각했습니다.
    하지만, 과제를 진행할수록, 어떻게하면 유지보수가 쉬울까, 
    어떻게하면 가독성을 높일 수 있을까 생각하다보니 고민이 깊어졌습니다.

    혼자 고민하다보니, 제출한 코드의 퀄리티가 매우 낮습니다.
    이번 온보딩 과정에서 많이 배워서 성장하고 싶습니다!
    감사합니다.

## 🔍 Reference
- [우테코 Style Guide](https://github.com/woowacourse/woowacourse-docs/tree/main/styleguide/java)
- [우테코 PR 점검사항](https://github.com/woowacourse/woowacourse-docs/blob/main/cleancode/pr_checklist.md)
- [[코딩규칙] 자바 코딩 규칙(Java Code Conventions)](https://myeonguni.tistory.com/1596)
- [Code Conventions for the Java TM Programming Language](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html)
- [AngularJS Git Commit Message Conventions](https://gist.github.com/stephenparish/9941e89d80e2bc58a153)
