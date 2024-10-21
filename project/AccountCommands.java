package project.project;

import java.time.LocalDateTime;
import java.util.*;

public class AccountCommands {
    static List<Accounts> accountsList = new ArrayList<>();

    private static void signUpAccount(Scanner sc){
        System.out.println("회원가입을 시작합니다.");
        System.out.print("아이디 > ");
        String inputId = sc.nextLine();
        System.out.print("비밀번호 > ");
        String inputPw = sc.nextLine();
        System.out.print("사용할 닉네임 > ");
        String inputNn = sc.nextLine();
        String nowTime = LocalDateTime.now().toString();
        Accounts accounts = new Accounts(inputId, inputPw, inputNn, nowTime);
        accountsList.add(accounts);
        System.out.println("회원가입 성공!");
    }

    private static void signInAccount(Scanner sc){
        if(!Request.isLoggedIn){
            System.out.println("로그인을 시작합니다.");
            System.out.print("아이디 > ");
            String inputId = sc.nextLine();
            System.out.print("비밀번호 > ");
            String inputPw = sc.nextLine();
            for (int i = 0; i < accountsList.size(); i++) {
                if(Objects.equals(accountsList.get(i).getId(), inputId)
                    && Objects.equals(accountsList.get(i).getPassword(), inputPw)){
                    Request.isLoggedIn = true;
                    System.out.println(accountsList.get(i).getNickName()+"님 환영합니다.");
                }
            }
        } else{
            System.out.println("이미 로그인 되어있습니다.");
            System.out.println("로그아웃 이후 진행하여 주시오.");
        }
    }

    private static void signOutAccount(){
        if(Request.isLoggedIn){
           Request.isLoggedIn = false;
            System.out.println("로그아웃 되었습니다.");
        } else {
            System.out.println("이미 로그아웃 상태입니다.");
        }
    }

    private static void detailAccount(Map<String, String> inputIndex) {
        String readAccountId = inputIndex.get("accountId");
        String s = readAccountId.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(s)-1;
        Accounts accounts = accountsList.get(index);
        String inputId = accounts.getId();
        String inputNn = accounts.getNickName();
        String inputSD = accounts.getSignUpDate();
        System.out.println(readAccountId + "번 회원");
        System.out.println("계정 : " + inputId);
        System.out.println("닉네임 : " + inputNn);
        System.out.println("가입일 : " + inputSD);
    }

    private static void editAccount(Map<String, String> inputIndex, Scanner sc) {
        String readAccountId = inputIndex.get("accountId");
        String s = readAccountId.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(s)-1;
        if (index >= 0 && index < accountsList.size()){
            System.out.println(readAccountId +"번 계정의 정보를 수정합니다.");
            System.out.print("비밀번호 > ");
            String inputPw = sc.nextLine();
            System.out.print("닉네임 > ");
            String inputNn = sc.nextLine();
            String nowTime = LocalDateTime.now().toString();
            accountsList.get(index).setPassword(inputPw);
            accountsList.get(index).setNickName(inputNn);
            accountsList.get(index).setEditDate(nowTime);
            System.out.println(readAccountId + "번 계정이 성공적으로 수정되었습니다!");
        } else {
            try {
                throw new IndexOutOfBoundsException();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(inputIndex+"번 계정은 존재하지 않습니다");
            }
        }
    }

    private static void removeAccount(Map<String, String> inputIndex) {
        if(!Request.isLoggedIn) {
            String readBoardId = inputIndex.get("accountId");
            String s = readBoardId.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(s) - 1;
            if (index >= 0 && index < accountsList.size()) {
                accountsList.remove(index);
                System.out.println(readBoardId + "번 계정이 성공적으로 삭제되었습니다!");
            } else {
                try {
                    throw new IndexOutOfBoundsException();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(inputIndex + "번 계정은 존재하지 않습니다");
                }
            }
        } else {
            System.out.println("로그아웃을 먼저 해주세요.");
        }

    }


    public static void accountHandler(String inputFunction, Map<String,String> inputParameter, Scanner sc){
        if(Objects.equals(inputFunction, "signup")){
            //회원가입
            signUpAccount(sc);
        } else if (Objects.equals(inputFunction, "signin")) {
            //로그인
            signInAccount(sc);
        } else if (Objects.equals(inputFunction, "signout")) {
            //로그아웃
            signOutAccount();
        } else if (Objects.equals(inputFunction, "detail")) {
            //회원정보
            detailAccount(inputParameter);
        } else if (Objects.equals(inputFunction, "edit")) {
            //회원정보수정
            editAccount(inputParameter, sc);
        } else if (Objects.equals(inputFunction, "remove")) {
            //회원탈퇴
            removeAccount(inputParameter);
        }
    }


}
