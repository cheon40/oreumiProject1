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
    public static void createPost(Scanner sc, Map<String, String> inputIndex){
        //회원확인
        if(Session.getIsMember()){
            //파라미터확인
            if(checkInputIndex(inputIndex)){
                System.out.println("새로운 게시글을 작성합니다.");
                System.out.print("제목 > ");
                String inputTitle = sc.nextLine();
                System.out.print("내용 > ");
                String inputContent = sc.nextLine();
                int getBoardIndex = Integer.parseInt(inputIndex.get("boardId"));
                String nowTime = LocalDateTime.now().toString();
                int writerId = Session.getuIdSession();
                container.newPost(inputTitle, inputContent, nowTime, getBoardIndex, writerId);
                System.out.println("게시글 작성 성공");
            }
        } else {
            System.out.println("회원 권한입니다.");
        }
    }
    //게시글수정
    public static void updatePost(Map<String, String> inputIndex, Scanner sc){
        //파라미터확인
        if(checkInputIndex(inputIndex)) {
            String readPostId = inputIndex.get("postId");
            String s = readPostId.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(s);
            //권한확인
            if (Session.getIsAdmin() || Session.getuIdSession() == index) {
                //게시글유무확인
                if (index >= 0 && index < postsList.size()) {
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
            } else {
                System.out.println("수정 권한이 없습니다.");
            }
        }
    }
    //게시글삭제
    public static void deletePost(Map<String, String> inputIndex){
        //파라미터확인
        if(checkInputIndex(inputIndex)) {
            String readPostId = inputIndex.get("postId");
            String s = readPostId.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(s);
            //권한확인
            if (Session.getIsAdmin() || Session.getuIdSession() == index) {
                //게시글유무확인
                if (index >= 0 && index < postsList.size()) {
                    postsList.remove(index);
                    System.out.println(readPostId + " 게시글이 성공적으로 삭제되었습니다!");
                } else {
                    try {
                        throw new IndexOutOfBoundsException();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(inputIndex + " 게시글은 존재하지 않습니다");
                    }
                }
            } else {
                System.out.println("삭제 권한이 없습니다.");
            }
        }
    }
    //게시판 삭제시 해당 게시판의 게시글 전체 삭제
    public static void deletePostByBoard(int boardId){
        for (int i = 0; i < postsList.size(); i++) {
            if(postsList.get(i).getBoardIndex()==boardId){
                postsList.remove(i);
            }
        }
    }
    //게시글조회
    public static void readPost(Map<String, String> inputIndex) {
        //파라미터확인
        if(checkInputIndex(inputIndex)) {
            String readPostId = inputIndex.get("postId");
            String s = readPostId.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(s);
            try {
                Posts posts = postsList.get(index);
                System.out.println(readPostId + "번 게시물");
                System.out.println("작성일 : " + posts.getAddTime());
                System.out.println("수정일 : " + posts.getEditTime());
                System.out.println("제목 : " + posts.getTitle());
                System.out.println("내용 : " + posts.getContents());
            } catch (IndexOutOfBoundsException e) {
                System.out.println(inputIndex + "게시글은 존재하지 않습니다.");
            }
        }
    }
    //게시판에서 게시글조회
    public static void listPost(int boardIndex){
        boolean flag = false;
        for (int i = 0; i < postsList.size(); i++) {
            Posts posts = postsList.get(i);
            if(posts.getBoardIndex()==boardIndex){
                String title = posts.getTitle();
                String addTime = posts.getAddTime();
                System.out.print(
                        i + "번 / "
                        + title + " / "
                        + addTime
                );
                System.out.println();
                flag = true;
            }
        }
        if(!flag){
            System.out.println(boardIndex+"번 게시뮬에 게시글이 없습니다.");
        }
    }
    //0번 게시글 작성
    public static void writeNoti(String notiName, int notiBoardId, int uid){
        String firstTitle = notiName+" 게시판 공지";
        String firstContent = notiName+" 게시판 공지글 입니다.";
        String nowTime = LocalDateTime.now().toString();
        container.newPost(firstTitle, firstContent, nowTime, notiBoardId, uid);
    }
    //파라미터확인
    private static boolean checkInputIndex(Map<String, String> inputIndex){
        String readInput = inputIndex.get("boardId");
        if(readInput != null){
            return true;
        } else{
            readInput = inputIndex.get("postId");
            if (readInput != null){
                return true;
            } else{
                try {
                    throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("파라미터를 확인해 주세요.");
                }
                return false;
            }
        }
    }
}
