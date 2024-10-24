package project.study1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("한글");
        //문자열 공백제거, 문자열 분리
        //입력 받는 것
        //Scanner <- 표준입력
        //Map() 활용법
        //프로젝트 템플릿
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        System.out.println("data = " + data);
        System.out.println("data.length() = " + data.length());

        String trimData = data.trim();

        if(trimData.equals("인사")){
            System.out.println("Program : hello!");
        }

        String sentence = "hello.my.name.is.Cheon";
        String[] words = sentence.split("\\.");
        for (int i = 0; i < words.length; i++) {
            System.out.println("words = " + words[i]);
        }

        String targetUrl = "www.example.com/hello/world?a=10&b=20";
        String[] split = targetUrl.split("\\?", 2);
        for (int i = 0; i < split.length; i++) {
            System.out.println("");
        }
    }
}
