package project.project;

import java.util.*;

public class BoardCommands {
    static List<Boards> boardsList = new ArrayList<>();

    public static void createBoard(Scanner sc){
        System.out.println("새로운 게시판을 생성합니다.");
        System.out.print("게시판 이름 > ");
        String inputName = sc.nextLine();
        Boards boards = new Boards(inputName);
        boardsList.add(boards);
        System.out.println("게시판 생성 성공");
    }

    public static void updateBoard(Map<String, String> inputIndex, Scanner sc){
        String readBoardId = inputIndex.get("boardId");
        String s = readBoardId.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(s)-1;
        if (index >= 0 && index < boardsList.size()){
            System.out.println(readBoardId+"번 게시판을 수정합니다.");
            System.out.print("게시판 명 > ");
            String inputName = sc.nextLine();
            boardsList.set(index, new Boards(inputName));
            System.out.println(readBoardId + "번 게시판이 성공적으로 수정되었습니다!");
        } else {
            try {
                throw new IndexOutOfBoundsException();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(inputIndex+"번 게시판은 존재하지 않습니다");
            }
        }
    }
    public static void deleteBoard(Map<String, String> inputIndex){
        String readBoardId = inputIndex.get("boardId");
        String s = readBoardId.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(s)-1;
        if (index >= 0 && index < boardsList.size()){
            boardsList.remove(index);
            System.out.println(readBoardId + "번 게시판이 성공적으로 삭제되었습니다!");
        } else {
            try {
                throw new IndexOutOfBoundsException();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(inputIndex+"번 게시판은 존재하지 않습니다");
            }
        }
    }
    public static void readBoard(Map<String, String> inputIndex){
        String readBoardName = inputIndex.get("boardName");
        for (int i = 0; i < boardsList.size(); i++) {
            if(Objects.equals(boardsList.get(i).getBoardName(), readBoardName)){
                System.out.println(i);
                PostCommands.listPost(i);
            }
        }
    }
    public static void listBoard(){
        int index = boardsList.size();
        System.out.println("총 개시판은 "+index+"개가 있습니다.");
        System.out.println();
        for (int i = 1; i < index+1; i++) {
            System.out.println(i+"번 게시판 " + (boardsList.get(i-1)).boardName);
        }
    }
    public static void boardHandler(String inputFunction, Map<String,String>inputParameter, Scanner sc ){
        if(Objects.equals(inputFunction, "add")){
            createBoard(sc);    //생성
        } else if (Objects.equals(inputFunction, "edit")) {
            updateBoard(inputParameter, sc);    //수정
        } else if (Objects.equals(inputFunction, "remove")) {
            deleteBoard(inputParameter);    //삭제
        } else if (Objects.equals(inputFunction, "view")) {
            readBoard(inputParameter);  //읽기
        } else if (Objects.equals(inputFunction, "list")) {
            listBoard();
        }
    }
}
