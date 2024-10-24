package project.project.Controller;

import project.project.ExceptionSet.WrongUrlException;
import project.project.Model.Boards;
import project.project.Model.Container;
import project.project.Model.Session;

import java.util.*;

public class BoardCommands {
    static Container container = Container.getInstance();
    public static List<Boards> boardsList = container.getBoardContainer();

    //게시판생성
    public static void createBoard(Scanner sc){
        //관리자확인
        if(Session.getIsAdmin()){
            System.out.println("새로운 게시판을 생성합니다.");
            System.out.print("게시판 이름 > ");
            String inputName = sc.nextLine();
            container.createBoard(inputName);
            System.out.println("게시판 생성 성공");
            boardsList.getLast().setuIdBoards(Session.getuIdSession());
            //게시판 작성시 공지글 생성
            int boardId = boardsList.size() - 1;
            int uid = Session.getuIdSession();
            PostCommands.writeNoti(inputName, boardId, uid);
        } else{
            System.out.println("관리자 권한입니다.");
        }
    }
    //게시판수정
    public static void updateBoard(Map<String, String> inputIndex, Scanner sc){
        //관리자확인
        if(Session.getIsAdmin()){
            //파라미터확인
            if(checkInputIndex(inputIndex)){
                String readBoardId = inputIndex.get("boardId");
                String s = readBoardId.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(s);
                //게시판유무확인
                if (index >= 0 && index < boardsList.size()){
                    System.out.println(readBoardId+"번 게시판을 수정합니다.");
                    System.out.print("게시판 명 > ");
                    String inputName = sc.nextLine();
                    boardsList.get(index).setBoardName(inputName);
                    System.out.println(readBoardId + "번 게시판이 성공적으로 수정되었습니다!");
                } else {
                    try {
                        throw new IndexOutOfBoundsException();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(inputIndex+"번 게시판은 존재하지 않습니다");
                    }
                }
            }
        } else{
            System.out.println("관리자 권한입니다.");
        }
    }
    //게시판삭제
    public static void deleteBoard(Map<String, String> inputIndex){
        //관리자확인
        if(Session.getIsAdmin()){
            //파라미터확인
            if(checkInputIndex(inputIndex)) {
                String readBoardId = inputIndex.get("boardId");
                String s = readBoardId.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(s);
                //게시판유무확인
                if (index >= 0 && index < boardsList.size()) {
                    boardsList.remove(index);
                    //게시글삭제
                    PostCommands.deletePostByBoard(index);
                    System.out.println(readBoardId + "번 게시판이 성공적으로 삭제되었습니다!");
                } else {
                    try {
                        throw new IndexOutOfBoundsException();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(readBoardId + "번 게시판은 존재하지 않습니다");
                    }
                }
            }
        } else {
            System.out.println("관리자 권한입니다.");
        }
    }
    //게시글목록
    public static void readBoard(Map<String, String> inputIndex){
        boolean flag = true;
        //파라미터확인
        if(checkInputIndex(inputIndex)) {
            String readBoardName = inputIndex.get("boardName");
                //게시판검색
                for (int i = 0; i < boardsList.size(); i++) {
                    if (Objects.equals(boardsList.get(i).getBoardName(), readBoardName)) {
                        PostCommands.listPost(i);
                        flag = false;
                    }
                }
                if(flag){
                    System.out.println(readBoardName+"게시판은 존재하지 않습니다.");
                }
        }
    }
    //전체게시판목록
    public static void listBoard(){
        int index = boardsList.size();
        System.out.println("총 개시판은 "+index+"개가 있습니다.");
        System.out.println();
        for (int i = 0; i < index; i++) {
            System.out.println(i+"번 게시판 " + (boardsList.get(i).getBoardName()));
        }
    }
    //파라미터확인
    private static boolean checkInputIndex(Map<String, String> inputIndex){
        String readInput = inputIndex.get("boardId");
        if(readInput != null){
            return true;
        } else{
            readInput = inputIndex.get("boardName");
            if (readInput != null){
                return true;
            } else{
                try {
                    throw new WrongUrlException();
                } catch (WrongUrlException e) {
                    System.out.println("파라미터를 확인해 주세요.");
                }
                return false;
            }
        }
    }
}
