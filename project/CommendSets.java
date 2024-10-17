package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommendSets {
    static List<Posts> postsList = new ArrayList<>();

    public static void createPost(String title, String contents){
        Posts post = new Posts(title, contents);
        postsList.add(post);
        System.out.println("글 작성 성공");
    }

    public static void readPost(String inputIndex){
        String s = inputIndex.replaceAll("[^0-9]", "");//숫자가 아닌 문자를 공백으로 변경
        int index = Integer.parseInt(s); // String을 int로
        try{
            Posts posts = postsList.get(index-1);
            System.out.println(inputIndex + " 게시물");
            System.out.println("제목 : " + posts.title);
            System.out.println("내용 : " + posts.contents);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(inputIndex+"게시글은 존재하지 않습니다.");
        }
    }
    public static void updatePost(String inputIndex, Scanner sc){
        String s = inputIndex.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(s)-1;
        if (index >= 0 && index < postsList.size()){
            System.out.println(inputIndex+" 게시물을 수정합니다.");
            System.out.print("제목 > ");
            String inputTitle = sc.nextLine();
            System.out.print("내용 > ");
            String inputContent = sc.nextLine();
            postsList.set(index, new Posts(inputTitle, inputContent));
            System.out.println(inputIndex + " 게시물이 성공적으로 수정되었습니다!");
        } else {
            try {
                throw new IndexOutOfBoundsException();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(inputIndex+" 게시물은 존재하지 않습니다");
            }
        }
    }
    public static void deletePost(String inputIndex){
        String s = inputIndex.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(s)-1;
        if (index >= 0 && index < postsList.size()){
           postsList.remove(index-1);
           System.out.println(inputIndex + " 게시물이 성공적으로 삭제되었습니다!");
        } else {
            try {
                throw new IndexOutOfBoundsException();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(inputIndex+" 게시물은 존재하지 않습니다");
            }
        }
    }
    public static void showAllPost(){
        int index = postsList.size();
        System.out.println("총 개시글은 "+index+"개 작성되어있습니다.");
        System.out.println();
        for (int i = 1; i < index+1; i++) {
            readPost(i+"번");
            System.out.println();
        }
    }

}
