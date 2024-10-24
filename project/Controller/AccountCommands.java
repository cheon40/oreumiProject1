package project.project.Controller;

import project.project.Model.Accounts;
import project.project.Model.Container;
import project.project.Model.Request;
import project.project.Model.Session;

import java.time.LocalDateTime;
import java.util.*;

public class AccountCommands {
    static Container container = Container.getInstance();
    public static List<Accounts> accountsList = container.getAccountContainer();
    //회원가입
    public static void signUpAccount(Scanner sc){
        //로그인확인
        if(!Session.getIsLoggedIn()){
            System.out.println("회원가입을 시작합니다.");
            System.out.print("아이디 > ");
            String inputId = sc.nextLine();
            //ID 중복 검사
            inputId = IdCheck(inputId, sc);
            System.out.print("비밀번호 > ");
            String inputPw = sc.nextLine();
            System.out.print("사용할 닉네임 > ");
            String inputNn = sc.nextLine();
            String nowTime = LocalDateTime.now().toString();
            container.newAccount(inputId, inputPw, inputNn, nowTime, "normal");
            System.out.println("회원가입 성공!");
        } else {
            System.out.println("회원가입은 로그아웃 상태에서 가능합니다.");
        }
    }
    //계정등급 변경
    public static void changeLevel(Map<String, String> inputIndex){
        //파라미터확인
        if(checkInputIndex(inputIndex)){
            String readAccountId = inputIndex.get("accountId");
            //관리자 확인
            if(Session.getIsAdmin()) {
                try{
                    String s = readAccountId.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(s);
                    accountsList.get(index).setLevel("admin");
                    System.out.println(s+"번 계정의 권한이 변경되었습니다.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(readAccountId+"번 회원은 존재하지 않습니다.");
                }
            }
            else{
                System.out.println("권한이 없습니다.");
            }
        }
    }
    //로그인
    public static void signInAccount(Scanner sc){
        boolean flag = false;
        //로그인확인
        if(!Session.getIsLoggedIn()){
            System.out.println("로그인을 시작합니다.");
            System.out.print("아이디 > ");
            String inputId = sc.nextLine();
            System.out.print("비밀번호 > ");
            String inputPw = sc.nextLine();
            for (int i = 0; i < accountsList.size(); i++) {
                //아이디 비밀번호 유무 확인
                if(Objects.equals(accountsList.get(i).getId(), inputId)
                    && Objects.equals(accountsList.get(i).getPassword(), inputPw)){
                    flag = true;
                    Session.setIsLoggedIn(true);
                    Session.setuIdSession(i);
                    Session.setuName(accountsList.get(i).getNickName());
                    System.out.println(accountsList.get(i).getNickName()+"님 환영합니다.");
                    //권한 부여
                    if (Objects.equals(accountsList.get(i).getLevel(), "admin")){
                        Session.setIsAdmin(true);
                        Session.setIsMember(true);
                    } else {
                        Session.setIsAdmin(false);
                        Session.setIsMember(true);
                    }
                }
            }
            if(!flag){
                System.out.println("해당 계정이 존재하지 않습니다.");
            }
        } else{
            System.out.println("이미 로그인 되어있습니다.");
            System.out.println("로그아웃 이후 진행하여 주시오.");
        }
    }
    //로그아웃
    public static void signOutAccount(){
        //로그인확인
        if(Session.getIsLoggedIn()){
            Session.setIsLoggedIn(false);
            Session.setIsAdmin(false);
            Session.setIsMember(false);
            System.out.println("로그아웃 되었습니다.");
        } else {
            System.out.println("이미 로그아웃 상태입니다.");
        }
    }
    //회원정보
    public static void detailAccount(Map<String, String> inputIndex) {
        //파라키터확인
        if(checkInputIndex(inputIndex)){
            String readAccountId = inputIndex.get("accountId");
            try{
                String s = readAccountId.replaceAll("[^0-9]", "");
                int index =  Integer.parseInt(s);
                Accounts accounts = accountsList.get(index);
                String inputId = accounts.getId();
                String inputNn = accounts.getNickName();
                String inputSD = accounts.getSignUpDate();
                System.out.println(index + "번 회원");
                System.out.println("계정 : " + inputId);
                System.out.println("닉네임 : " + inputNn);
                System.out.println("가입일 : " + inputSD);
            } catch (IndexOutOfBoundsException e){
                System.out.println(readAccountId+"번 회원은 존재하지 않습니다.");
            }
        }
    }
    //회원정보 수정
    //관리자 혹은 본인
    public static void editAccount(Map<String, String> inputIndex, Scanner sc) {
        //파라미터확인
        if(checkInputIndex(inputIndex)){
            String readAccountId = inputIndex.get("accountId");
            String s = readAccountId.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(s);
            //계정유무확인
            if (index >= 0 && index < accountsList.size()){
                //관리자권한 및 계정일치여부
                if (index == Session.getuIdSession()) {
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
                    System.out.println("보안을 위해 로그아웃 됩니다.");
                    signOutAccount();
                } else if (Session.getIsAdmin()) {
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
                        System.out.println("권한이 없습니다.");
                }
            } else {
                try {
                    throw new IndexOutOfBoundsException();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(readAccountId + "번 계정은 존재하지 않습니다");
                }
            }
        }
    }
    //회원탈퇴
    //관리자 혹은 본인
    public static void removeAccount(Map<String, String> inputIndex) {
        //파라미터확인
        if(checkInputIndex(inputIndex)) {
            String readAccountId = inputIndex.get("accountId");
            String s = readAccountId.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(s);
            //로그인확인
            if(Session.getIsLoggedIn()) {
                //계정유무확인
                if (index >= 0 && index < accountsList.size()) {
                    //관리자권한 및 계정일치여부
                    if (index == Session.getuIdSession()) {
                        accountsList.remove(index);
                        System.out.println(readAccountId + "번 계정이 성공적으로 삭제되었습니다!");
                        signOutAccount();
                    } else if (Session.getIsAdmin()) {
                        accountsList.remove(index);
                        System.out.println(readAccountId + "번 계정이 성공적으로 삭제되었습니다!");
                    }
                    else {
                        System.out.println("권한이 없습니다.");
                    }
                } else {
                    try {
                        throw new IndexOutOfBoundsException();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(readAccountId + "번 계정은 존재하지 않습니다");
                    }
                }
            } else {
                System.out.println("로그인 이후 가능합니다.");
            }
            System.out.println(index);
            System.out.println(Session.getuIdSession());
        }
    }
    //파라미터확인
    private static boolean checkInputIndex(Map<String, String> inputIndex){
        String readInput = inputIndex.get("accountId");
        if(readInput != null){
            return true;
        } else {
            try {
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("파라미터를 확인해 주세요.");
            }
            return false;
        }
    }
    //ID중복 검사
    private static String IdCheck(String inputId, Scanner sc) {
        while (true) {
            boolean isDuplicate = false;
            for (int i = 0; i < accountsList.size(); i++) {
                if (Objects.equals(accountsList.get(i).getId(), inputId)) {
                    System.out.println("이미 사용중인 ID입니다.");
                    System.out.print("아이디 > ");
                    inputId = sc.nextLine();
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                return inputId;
            }
        }
    }
}
