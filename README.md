# YaguRoot
본 프로젝트는 사용자로부터 URL로 구성되어있는 명령어를 입력받고 입력받은 URl에 따라
다양한 기능을 수행하는 게시판 프로젝트입니다. 게시판의 주제는 야구 게시판이며 회원은 
응원하는 팀을 정할 수 있고 이에 따른 제한된 권한이 부여됩니다.
## 세부사항
### 명령어에 따른 동작
| URL                   | 파라미터                | 동작             |
|-----------------------|---------------------|----------------|
| /boards/add           | -                   | 새로운 게시판 생성     |
| /boards/list          | -                   | 모든 게시판 출력      |
| /boards/edit          | boardName           | 게시판 수정         |
| /boards/remove        | boardName           | 게시판 삭제         |
| /boards/view          | boardName           | 해당 게시판의 게시물 조회 |
|                       |                     |                |
| /posts/add            | boardName           | 해당 게시판에 게시물 작성 |
| /posts/edit           | boardName & postId  | 해당 게시판의 게시물 수정 |
| /posts/remove         | boardName & postId  | 해당 게시판의 게시물 삭제 |
| /posts/view           | boardName & postId  | 해당 게시판의 개시물 조회 |
|                       |                     |                |
| /accounts/signup      | -                   | 회원가입           |
| /accounts/signin      | -                   | 로그인            |
| /accounts/signout     | -                   | 로그아웃           |
| /accounts/changeLevel | accountId           | 관리자 권한 부여      |
| /accounts/detail      | accountId           | 계정 상세          |
| /accounts/edit        | accountId           | 회원 정보 수정       |
| /accounts/remove      | accountId           | 회원 탈퇴          |

### 프로그렘 동작 과정
1. 사용자가 URL 입력
2. URL에 따른 Request 객체 생성 + Session 객체 생성
3. Request 객체의 category, function, parameter 조회
4. 권한 확인
5. 명령 실행

### 새로 추가한 기능 : 응원하는 팀
- 계정 생성시 응원하는 팀을 가집니다.  
- 게시글 생성시 게시글이 생성된 게시판의 이름이 저장됩니다.
- 로그인시 해당 계정의 응원하는 팀의 이름이 세션에 저장됩니다.  
- 게시판 명은 이미 생성되어 있는 자유게시판을 제외하고 모두 "팀 이름" + 게시판으로 생성됩니다.  
- 팀 이름은 중복될수 없습니다.

### 응원하는 팀에 따른 접근 권한
1. 관리자는 모든 게시판에 게시글을 생성 수정 삭제 할수있다.
2. 회원은 자신이 응원하는 팀의 게시판에 게시글을 생성,
   자신이 작성한 게시글을 수정, 삭제 할수있다.
3. 모든 회원은 자유게시판에 게시글을 생성,
   자신이 작성한 게시글을 수정, 삭제 할수있다.

## 트러블 슈팅 (적어도 2개)
### 예외처리
1. 문제 직면  
우선 작성한 코드는 다음과 같습니다. (예외 발생 전)
```java
public static void updatePost(String inputIndex, String title, String contents){
        String s = inputIndex.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(s);
        System.out.println(inputIndex+" 게시물을 수정합니다.");
        System.out.print("제목 > ");
        String inputTitle = sc.nextLine();
        System.out.print("내용 > ");
        String inputContent = sc.nextLine();
        postsList.set(index, new Posts(inputTitle, inputContent));
        System.out.println(inputIndex + " 게시물이 성공적으로 수정되었습니다!");
        System.out.println(inputIndex + " 게시물이 성공적으로 수정되었습니다!");
}

```
근데 이 코드에 그냥 try catch를 사용하면 예외를 출력하기 전에 제목과 내용을 입력받는 명령이 실행되어  
요구사항을 만족하지 못하게 된다.  
이렇게 됨(  
명령어 > 수정  
어떤 게시물을 수정할까요? > 100번  
100번 게시물을 수정합니다.  
제목 > 어쩌구  
내용 > 저쩌구  
100번 게시물은 존재하지 않습니다.  
명령어 >  
)
2. 그에대한 고민 + 해결방안 모색  
순서를 이리 저리 바꿔보고 제목 + 내용을 입력받는 또 다른 함수를 사용하고 여러 시도를 해봤습니다.  
3. 해결
결국 찾아낸 방법은 다음과 같습니다.  
ⓐ if문으로 먼저 index의 유효성을 검사한다.  
ⓑ 유효하지 못할경우 else문에서 throw문으로 직접 예외를 발생시킨 후에 try catch를 사용한다.
```java
public static void updatePost(String inputIndex, Scanner sc){
        String s = inputIndex.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(s)-1;
        if (index >= 0 && index < postsList.size()){
            System.out.println(inputIndex+" 게시물을 수정합니다.");
            System.out.print("제목 > ");
            String inputTitle = sc.nextLine();
            System.out.print("내용 > ");
            String inputContent = sc.nextLine();
            postsList.set(index, new Posts(inputTitle, inputContent));
            System.out.println(inputIndex + " 게시물이 성공적으로 수정되었습니다!");
        } else {
            try {
                throw new IndexOutOfBoundsException();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(inputIndex+" 게시물은 존재하지 않습니다");
            }
        }
}

```
4. 후기  
처음에는 그렇게 어려운 방법이 아닌데 생각하느라 시간이 오래 걸렸어서 아쉬운 마음이 있었지만  
이후 예외 처리를 할 때 빈번하게 등장하고 지금 트러블 슈팅을 작성하면서도 바로 생각나는걸 보아  
앞으로 기억에 더 오래 남을거 같다는 생각이 듭니다.

### Git - 원격 저장소 생성시 주의점
1. 1단계를 완료하고 git에다가 올리는데 또 문제를 만나 강사님의 도움을 받았습니다.  
기존에는 gitbash를 사용하다가 저번에 새롭게 배웠던 sourceTree를 사용해보려 했는데  
2가지 문제에 직면했다.

문제 1. 원격 리포지터리에 연결이 되었는지 확인이 안됨
sourceTree 우측상단에 지구모양 원격 버튼을 누르면 원격 리포지토리에 연결할수 있고
연결 후 원격 리포지토리가 열리면 연결 성공이다.  

문제 2. push가 안됨  
오류코드는 다음과 같았다
```
git -c diff.mnemonicprefix=false -c core.quotepath=false --no-optional-locks push -v oreumiProject1 main:main
Pushing to https://github.com/cheon40/oreumiProject1.git
To https://github.com/cheon40/oreumiProject1.git
 ! [rejected]        main -> main (fetch first)
error: failed to push some refs to 'https://github.com/cheon40/oreumiProject1.git'


hint: Updates were rejected because the remote contains work that you do not
hint: have locally. This is usually caused by another repository pushing to
hint: the same ref. If you want to integrate the remote changes, use
hint: 'git pull' before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.




오류가 나면서 완료됨. 
```
원인 :  처음 원격리포지토리를 생성할때 readMe 파일을 만들었는데  
readMe 때문에 로컬리포지토리와 버전 차이가 생겨서 push가 안되는 오류

해결방안 : 원격리포지토리 삭제 후 재생성

강사님이 강좌 초반에 말씀해주셨던 오류를 막상 직접 만나니  
이게 뭐지 무슨 오류지 싶다가 다시 강사님이 짚어주셔서 쉽게 해결할수있었습니다.  
이 오류도 오래 기억에 남을것 같습니다

