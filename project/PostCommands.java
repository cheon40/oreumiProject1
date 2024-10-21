package project.project;

import java.time.LocalDateTime;
import java.util.*;

public class PostCommands {
    static List<Posts> postsList = new ArrayList<>();

    public static void createPost(Scanner sc, Map<String, String> inputIndex){
        System.out.println("새로운 게시글을 작성합니다.");
        System.out.print("제목 > ");
        String inputTitle = sc.nextLine();
        System.out.print("내용 > ");
        String inputContent = sc.nextLine();
        int getBoardIndex = Integer.parseInt(inputIndex.get("boardId"));
        String nowTime = LocalDateTime.now().toString();
        Posts posts = new Posts(inputTitle, inputContent, getBoardIndex, nowTime);
        postsList.add(posts);
        System.out.println("게시글 작성 성공");
    }

    public static void updatePost(Map<String, String> inputIndex, Scanner sc){
        String readPostId = inputIndex.get("postId");
        String s = readPostId.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(s)-1;
        if (index >= 0 && index < postsList.size()){
            System.out.println(readPostId +"번 게시글을 수정합니다.");
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
                System.out.println(inputIndex+" 게시글은 존재하지 않습니다");
            }
        }
    }
    public static void deletePost(Map<String, String> inputIndex){
        String readPostId = inputIndex.get("postId");
        String s = readPostId.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(s)-1;
        if (index >= 0 && index < postsList.size()){
            postsList.remove(index);
            System.out.println(readPostId + " 게시글이 성공적으로 삭제되었습니다!");
        } else {
            try {
                throw new IndexOutOfBoundsException();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(inputIndex+" 게시글은 존재하지 않습니다");
            }
        }
    }
    public static void readPost(Map<String, String> inputIndex) {
        String readPostId = inputIndex.get("postId");
        String s = readPostId.replaceAll("[^0-9]", "");//숫자가 아닌 문자를 공백으로 변경
        int index = Integer.parseInt(s); // String을 int로
        try {
            Posts posts = postsList.get(index - 1);
            System.out.println(inputIndex + " 게시물");
            System.out.println("작성일 : " + posts.addTime);
            System.out.println("수정일 : " + posts.editTime);
            System.out.println("제목 : " + posts.title);
            System.out.println("내용 : " + posts.contents);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(inputIndex + "게시글은 존재하지 않습니다.");
        }
    }
    public static void listPost(int boardIndex){
        boolean flag = false;
        for (int i = 0; i < postsList.size(); i++) {
            Posts posts = postsList.get(i);
            if(posts.getBoardIndex()==boardIndex){
                String title = posts.getTitle();
                String addTime = posts.getAddTime();
                System.out.print(
                        i+1 + "번 / "
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
    public static void postHandler(String inputFunction, Map<String,String>inputParameter, Scanner sc ){
        if(Objects.equals(inputFunction, "add")){
            createPost(sc, inputParameter);    //생성
        } else if (Objects.equals(inputFunction, "edit")) {
            updatePost(inputParameter, sc);    //수정
        } else if (Objects.equals(inputFunction, "remove")) {
            deletePost(inputParameter);    //삭제
        } else if (Objects.equals(inputFunction, "view")) {
            readPost(inputParameter);  //읽기
        }
    }
}
