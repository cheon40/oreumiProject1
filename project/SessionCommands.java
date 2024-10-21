//package project.project;
//
//import java.util.Map;
//import java.util.Objects;
//import java.util.Scanner;
//
//public class SessionCommands extends AccountCommands{
//    static void signInAccount(Scanner sc) {
//        System.out.println("로그인");
//        System.out.print("아이디 > ");
//        String inputId = sc.nextLine();
//        System.out.print("비밀번호 > ");
//        String inputPw = sc.nextLine();
//        if(!isLoggedIn){
//            for (int i = 0; i < accountsList.size(); i++) {
//                if(Objects.equals(inputId, accountsList.get(i).getId()) &&
//                        Objects.equals(inputPw, accountsList.get(i).getPassword())){
//                    String nickName = accountsList.get(i).getNickName();
//                    System.out.println(nickName+"님 환영합니다.");
//                    isLoggedIn = true;
//                } else {
//                    System.out.println("해당 계정은 존재하지 않습니다.");
//                }
//            }
//        } else{
//            System.out.println("이미 로그인 되어있습니다.");
//            System.out.println("로그아웃 후 재시도 해보세요.");
//        }
//    }
//
//    static void signOutAccount(Map<String, String> inputParameter) {
//
//    }
//}
