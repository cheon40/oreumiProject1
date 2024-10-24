package project.project.Controller;

import project.project.Model.Request;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import static project.project.Controller.BoardCommands.*;
import static project.project.Controller.PostCommands.*;
import static project.project.Controller.AccountCommands.*;

public class HandlerSet {
    public static void requestHandler(Request inputUrl, Scanner sc) {
        if (Objects.equals(inputUrl.getCategory(), "boards")){
            boardHandler(inputUrl.getFunction(), inputUrl.getParameter(), sc);
        } else if (Objects.equals(inputUrl.getCategory(), "posts")) {
            postHandler(inputUrl.getFunction(), inputUrl.getParameter(), sc);
        } else if (Objects.equals(inputUrl.getCategory(), "accounts")) {
            accountHandler(inputUrl.getFunction(), inputUrl.getParameter(), sc);
        }
        else {
            System.out.println("명령어를 확인해주세요.");
        }
    }
    //핸들러
    public static void boardHandler(String inputFunction, Map<String,String>inputParameter, Scanner sc ){
        if(Objects.equals(inputFunction, "add")){
            //게시판생성
            createBoard(sc);
        } else if (Objects.equals(inputFunction, "edit")) {
            //게시판수정
            updateBoard(inputParameter, sc);
        } else if (Objects.equals(inputFunction, "remove")) {
            //게시판삭제
            deleteBoard(inputParameter);
        } else if (Objects.equals(inputFunction, "view")) {
            //게시판보기
            readBoard(inputParameter);
        } else if (Objects.equals(inputFunction, "list")) {
            //전체게시판
            listBoard();
        } else{
            System.out.println(inputFunction+"에 해당하는 기능이 없습니다.");
        }
    }
    //핸들러
    public static void postHandler(String inputFunction, Map<String,String> inputParameter, Scanner sc ){
        if(Objects.equals(inputFunction, "add")){
            //게시글 생성
            createPost(sc, inputParameter);
        } else if (Objects.equals(inputFunction, "edit")) {
            //게시글 수정
            updatePost(inputParameter, sc);
        } else if (Objects.equals(inputFunction, "remove")) {
            //게시글 삭제
            deletePost(inputParameter);
        } else if (Objects.equals(inputFunction, "view")) {
            //게시글 읽기
            readPost(inputParameter);
        } else{
            System.out.println(inputFunction+"에 해당하는 기능이 없습니다.");
        }
    }
    //핸들러
    public static void accountHandler(String inputFunction, Map<String,String> inputParameter, Scanner sc){
        if(Objects.equals(inputFunction, "signup")){
            //회원가입
            signUpAccount(sc);
        } else if (Objects.equals(inputFunction, "changeLevel")) {
            //관리자변환
            changeLevel(inputParameter);
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
        } else{
            System.out.println(inputFunction+"에 해당하는 기능이 없습니다.");
        }
    }

}