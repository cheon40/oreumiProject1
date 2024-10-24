package project.project;

import project.project.Controller.AccountCommands;
import project.project.Controller.BoardCommands;
import project.project.Controller.HandlerSet;
import project.project.Controller.PostCommands;
import project.project.Model.*;

import java.util.Scanner;

public class YaguRoute {

    public static void main(String[] args) {
        Boards testBoard1 = new Boards("키움");
        Accounts account1 = new Accounts("rudcjs","0502", "조경천", "1234", "admin");
        Posts testPost1 = new Posts("공지","키움 게시판 공지","1234",0,0);

        BoardCommands.boardsList.add(testBoard1);
        PostCommands.postsList.add(testPost1);
        AccountCommands.accountsList.add(account1);

        test1();
    }
    static void test1() {
        System.out.println("야구소식은 야구루트!");
        while (true) {
            Scanner sc = new Scanner(System.in);
            if(Session.getIsLoggedIn()){
                System.out.print(Session.getuName());
                if (Session.getIsAdmin()){
                    System.out.print("(관리자)");
                }
                System.out.print(" > ");
            } else {
                System.out.print("익명 > ");
            }

            String command = sc.nextLine();
            Request requestUrl = Container.getInstance().newRequest(command);

            if(command.equals("종료")){
                System.out.println("프로그램이 종료됩니다.");
                break;
            } else{
                HandlerSet.requestHandler(requestUrl, sc);
            }
        }
    }
}
