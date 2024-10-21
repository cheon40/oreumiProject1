package project.project;

import java.util.Scanner;

public class YaguRoute {

    public static void main(String[] args) {
        Boards testBoard1 = new Boards("한화");
        Boards testBoard2 = new Boards("키움");
        Posts testPost1 = new Posts("1", "11", 0, "1111");
        Posts testPost2 = new Posts("2", "22", 0, "2222");
        Posts testPost3 = new Posts("3", "33", 0, "3333");
        BoardCommands.boardsList.add(testBoard1);
        PostCommands.postsList.add(testPost1);
        PostCommands.postsList.add(testPost2);
        PostCommands.postsList.add(testPost3);


        test1();        //main
    }
    static void test1() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("손님 > ");
            String command = sc.nextLine();
            Request requestUrl = new Request(command);
            if(command.equals("종료")){
                System.out.println("프로그램이 종료됩니다.");
                break;
            } else if (requestUrl.category.equals("boards")){
                BoardCommands.boardHandler(requestUrl.function,requestUrl.parameter, sc);
            } else if (requestUrl.category.equals("posts")) {
                PostCommands.postHandler(requestUrl.function, requestUrl.parameter, sc);
            } else if (requestUrl.category.equals("accounts")) {
                AccountCommands.accountHandler(requestUrl.function, requestUrl.parameter, sc);
            }
//            } else if (command.equals("조회")){
//                System.out.print("어떤 게시물을 조회할까요? > ");
//                String inputIndex = sc.nextLine();
//                CommendSets.readPost(inputIndex);
//            } else if (command.equals("수정")){
//                System.out.print("어떤 게시물을 수정할까요? > ");
//                String inputIndex = sc.nextLine();
//                CommendSets.updatePost(inputIndex, sc);
//            } else if (command.equals("삭제")){
//                System.out.print("어떤 게시물을 삭제할까요? > ");
//                String inputIndex = sc.nextLine();
//                CommendSets.deletePost(inputIndex);
//            } else if( command.equals("목록")){
//                CommendSets.showAllPost();
//            }
            else {
                System.out.println("존재하지 않는 명령어 입니다.");
            }
        }
    }
}
