package project.project.Controller;

import project.project.Model.Container;
import project.project.Model.Posts;
import project.project.Model.Session;


import java.time.LocalDateTime;
import java.util.*;



public class PostCommands {
    static Container container = Container.getInstance();
    public static List<Posts> postsList = container.getPostContainer();

    //게시글생성
    public static void createPost(Map<String, String> inputIndex, Scanner sc){
        String func = "add";
        //회원확인
        if(Session.getIsMember()){
            //파라미터확인
            if(checkPara(inputIndex, func)){
                //게시판 권한 확인
                if(checkBoardAuth(inputIndex)){
                    System.out.println("새로운 게시글을 작성합니다.");
                    System.out.print("제목 > ");
                    String inputTitle = sc.nextLine();
                    System.out.print("내용 > ");
                    String inputContent = sc.nextLine();
                    String getBoardName = inputIndex.get("boardName");
                    String nowTime = LocalDateTime.now().toString();
                    int writerId = Session.getuIdSession();
                    container.newPost(inputTitle, inputContent, nowTime, getBoardName, writerId);
                    System.out.println("게시글 작성 성공");
                }
            }
        } else {
            System.out.println("회원 권한입니다.");
        }
    }
    //게시글수정
    public static void updatePost(Map<String, String> inputIndex, Scanner sc){
        String func = "edit";
        //파라미터확인
        if(checkPara(inputIndex, func)) {
            String readPostId = inputIndex.get("postId");
            String s = readPostId.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(s);
            //게시판 권한 확인
            if(checkBoardAuth(inputIndex)){
                //게시글 권한 확인
                if (Session.getIsAdmin()||Session.getuIdSession() == index) {
                    //게시글유무확인
                    if (index < postsList.size()) {
                        System.out.println(readPostId + "번 게시글을 수정합니다.");
                        System.out.print("제목 > ");
                        String inputTitle = sc.nextLine();
                        System.out.print("내용 > ");
                        String inputContent = sc.nextLine();
                        String nowTime = LocalDateTime.now().toString();
                        postsList.get(index).setTitle(inputTitle);
                        postsList.get(index).setContents(inputContent);
                        postsList.get(index).setEditTime(nowTime);
                        System.out.println(readPostId + "번 게시글이 성공적으로 수정되었습니다!");
                    } else {
                        try {
                            throw new IndexOutOfBoundsException();
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(inputIndex + " 게시글은 존재하지 않습니다");
                        }
                    }
                }
            }
        }
    }
    //게시글삭제
    public static void deletePost(Map<String, String> inputIndex){
        String func = "remove";
        //파라미터확인
        if(checkPara(inputIndex, func)) {
            String readPostId = inputIndex.get("postId");
            String s = readPostId.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(s);
            //게시판 권한 확인
            if(checkBoardAuth(inputIndex)){
                //게시글 권한 확인
                if (Session.getIsAdmin()||Session.getuIdSession() == index) {
                    //게시글유무확인
                    if (index < postsList.size()) {
                        postsList.remove(index);
                        System.out.println(readPostId + "번 게시글이 성공적으로 삭제되었습니다!");
                    } else {
                        try {
                            throw new IndexOutOfBoundsException();
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(inputIndex + " 게시글은 존재하지 않습니다");
                        }
                    }
                }
            }
        }
    }
    //게시글조회
    public static void readPost(Map<String, String> inputIndex) {
        String func = "view";
        //파라미터확인
        if(checkPara(inputIndex, func)) {
            String readPostId = inputIndex.get("postId");
            String s = readPostId.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(s);
            //게시판 권한 확인
            try {
                Posts posts = postsList.get(index);
                System.out.println(readPostId + "번 게시물");
                System.out.println("작성일 : " + posts.getAddTime());
                System.out.println("수정일 : " + posts.getEditTime());
                System.out.println("제목 : " + posts.getTitle());
                System.out.println("내용 : " + posts.getContents());
            } catch (IndexOutOfBoundsException e) {
                System.out.println(index + "번 게시글은 존재하지 않습니다.");
            }
        }
    }
    //게시판 삭제시 해당 게시판의 게시글 전체 삭제
    public static void deletePostByBoard(int boardId){
        String name = BoardCommands.getBoardNameById(boardId);
        for (int i = 0; i < postsList.size(); i++) {
            if(Objects.equals(postsList.get(i).getBoardName(), name)){
                postsList.remove(i);
            }
        }
    }
    //게시판에서 게시글조회
    public static void listPost(int boardIndex){
        String name = BoardCommands.getBoardNameById(boardIndex);
        for (int i = 0; i < postsList.size(); i++) {
            if(Objects.equals(postsList.get(i).getBoardName(), name)){
                Posts posts = postsList.get(i);
                String title = posts.getTitle();
                String addTime = posts.getAddTime();
                System.out.print(
                        i + "번 / "
                                + title + " / "
                                + addTime
                );
                System.out.println();
            }
        }
    }
    //0번 게시글 작성
    public static void writeNoti(String notiName, int uid){
        String firstTitle = notiName+" 게시판 공지";
        String firstContent = notiName+" 게시판 공지글 입니다.";
        String nowTime = LocalDateTime.now().toString();
        container.newPost(firstTitle, firstContent, nowTime, notiName, uid);
    }

    //게시판 권환 확인
    public static boolean checkBoardAuth(Map<String, String> InputPara){
        boolean flag = false;
        String name = InputPara.get("boardName");
        if(Session.getIsAdmin() || Objects.equals(name, "자유") ||
                Objects.equals(name, Session.getuTeam())){
            flag = true;
        } else{
            System.out.println("권한이 없습니다.");
        }
        return flag;
    }

    //파라미터확인
    private static boolean checkPara(Map<String, String> inputIndex, String inputFunc){
        //create, update, delete, read
        boolean flag = false;
        if(Objects.equals(inputFunc, "add")){
            String readInputName = inputIndex.get("boardName");
            if(readInputName != null){
                flag = true;
            }
        } else if(Objects.equals(inputFunc, "remove")) {
            String readInputId = inputIndex.get("postId");
            String readInputName = inputIndex.get("boardName");
            if (readInputId != null && readInputName != null) {
                flag = true;
            }
        } else if(Objects.equals(inputFunc, "edit")) {
            String readInputId = inputIndex.get("postId");
            String readInputName = inputIndex.get("boardName");
            if (readInputId != null && readInputName != null) {
                flag = true;
            }
        } else if(Objects.equals(inputFunc, "view")) {
            String readInputId = inputIndex.get("postId");
            String readInputName = inputIndex.get("boardName");
            if (readInputId != null && readInputName != null) {
                flag = true;
            }
        } else{
            try {
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("파라미터를 확인해 주세요.");
            }
        }
    return flag;
    }
}
