package project.project.Controller;

import project.project.Model.Boards;
import project.project.Model.Container;
import project.project.Model.Session;

import java.util.*;

public class BoardCommands {
    static Container container = Container.getInstance();
    public static List<Boards> boardsList = container.getBoardContainer();

    //게시판생성
    public static void createBoard(Scanner sc) {
        //관리자 확인
        if(Session.getIsAdmin()){
            System.out.println("새로운 게시판을 생성합니다.");
            System.out.print("게시판 이름 > ");
            String inputName = sc.nextLine();
            //게시판 이름 중복 검사
            inputName = NameCheck(inputName, sc);
            container.createBoard(inputName);
            boardsList = container.getBoardContainer();
            System.out.println("게시판 생성 성공");
            //board에 uid 주입
            int uid = Session.getuIdSession();
            boardsList.getLast().setuIdBoards(uid);
            //게시판 작성시 공지글 생성
            PostCommands.writeNoti(inputName, uid);
        } else{
            System.out.println("관리자 권한입니다.");
        }
    }
    //게시판수정
    public static void updateBoard(Map<String, String> inputIndex, Scanner sc){
        String func = "edit";
        //관리자확인
        if(Session.getIsAdmin()) {
            //파라미터확인
            int index = getBoardIdByName(inputIndex);
            String name = inputIndex.get("boardName");
            if(checkPara(inputIndex, func)){
                //게시판유무확인
                if (index >= 0 && index < boardsList.size()){
                    System.out.println(name+" 게시판을 수정합니다.");
                    System.out.print("게시판 명 > ");
                    String inputName = sc.nextLine();
                    inputName = NameCheck(inputName, sc);
                    boardsList.get(index).setBoardName(inputName);
                    System.out.println(name + " 게시판이 성공적으로 수정되었습니다!");
                } else {
                    try {
                        throw new IndexOutOfBoundsException();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(name+" 게시판은 존재하지 않습니다");
                    }
                }
            }
        } else{
            System.out.println("관리자 권한입니다.");
        }
    }
    //게시판삭제
    public static void deleteBoard(Map<String, String> inputIndex){
        String func = "remove";
        //관리자확인
        if(Session.getIsAdmin()){
            //파라미터확인
            if(checkPara(inputIndex, func)) {
                int index = getBoardIdByName(inputIndex);
                String name = inputIndex.get("boardName");
                //게시판유무확인
                if (index >= 0 && index < boardsList.size()) {
                    //게시글삭제
                    PostCommands.deletePostByBoard(index);
                    boardsList.remove(index);
                    System.out.println(name + " 게시판이 성공적으로 삭제되었습니다!");
                } else {
                    try {
                        throw new IndexOutOfBoundsException();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(name + " 게시판은 존재하지 않습니다");
                    }
                }
            }
        } else {
            System.out.println("관리자 권한입니다.");
        }
    }
    //게시글목록
    public static void readBoard(Map<String, String> inputIndex) {
        String func = "view";
        String name = inputIndex.get("boardName");
        int index = getBoardIdByName(inputIndex);
        //파라미터 확인
        if (checkPara(inputIndex, func)) {
            //게시판 권환확인
            if(Session.getIsAdmin() || Objects.equals(name, "자유") ||
                    Objects.equals(name, Session.getuTeam())){
                //게시판검색
                if (index >= 0 && index < boardsList.size()) {
                    PostCommands.listPost(index);
                } else {
                    System.out.println(name + "게시판은 존재하지 않습니다.");
                }
            }

        }
    }
    //전체게시판목록
    public static void listBoard(){
        int index = boardsList.size();
        System.out.println("총 게시판은 "+index+"개가 있습니다.");
        System.out.println();
        for (int i = 0; i < index; i++) {
            System.out.println(i+"번 게시판 " + (boardsList.get(i).getBoardName()));
        }
    }
    //게시판 중복 검사
    private static String NameCheck(String inputName, Scanner sc) {
        while (true) {
            boolean isDuplicate = false;
            for (int i = 0; i < boardsList.size(); i++) {
                if (Objects.equals(boardsList.get(i).getBoardName(), inputName)) {
                    System.out.println("이미 존재하는 게시판입니다.");
                    System.out.print("게시판 명 > ");
                    inputName = sc.nextLine();
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                return inputName;
            }
        }
    }
    private static int getBoardIdByName(Map<String, String> inputIndex){
        int index = -1;
        for (int i = 0; i < boardsList.size(); i++) {
            if (Objects.equals(boardsList.get(i).getBoardName(), inputIndex.get("boardName"))) {
                index =  i;
            }
        }
        return index;
    }

    //게시판 id로 Name 가져오기
    public static String getBoardNameById(int boardId){
        return boardsList.get(boardId).getBoardName();
    }

    //파라미터확인
    private static boolean checkPara(Map<String, String> inputIndex, String inputFunc) {
        String readInput = inputIndex.get("boardName");
        if (readInput != null) {
            return true;
        } else {
            System.out.println("파라미터를 확인해 주세요.");
            return false;
        }
    }

}
