package project;

import java.util.Scanner;

public class YaguRoute {
    public static void main(String[] args) {
        test1();        //main
    }
    static void test1() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("명령어 > ");
            String command = sc.nextLine();
            if(command.equals("종료")){
                System.out.println("프로그램이 종료됩니다.");
                break;
            } else if (command.equals("작성")){
                System.out.print("제목 > ");
                String inputTitle = sc.nextLine();
                System.out.print("내용 > ");
                String inputContent = sc.nextLine();
                CommendSets.createPost(inputTitle,inputContent);
            } else if (command.equals("조회")){
                System.out.print("어떤 게시물을 조회할까요? > ");
                String inputIndex = sc.nextLine();
                CommendSets.readPost(inputIndex);
            } else if (command.equals("수정")){
                System.out.print("어떤 게시물을 수정할까요? > ");
                String inputIndex = sc.nextLine();
                CommendSets.updatePost(inputIndex, sc);
            } else if (command.equals("삭제")){
                System.out.print("어떤 게시물을 삭제할까요? > ");
                String inputIndex = sc.nextLine();
                CommendSets.deletePost(inputIndex);
            } else if( command.equals("목록")){
                CommendSets.showAllPost();
            }
            else {
                System.out.println("존재하지 않는 명령어 입니다.");
            }
        }
    }
}
