package project.project;

import java.util.HashMap;
import java.util.Map;

public class Request {
    static boolean isLoggedIn = false;
    String category;
    String function;
    Map<String, String> parameter = new HashMap<>();
    public Request(String inputUrl) {
        try {
            String[] splitQmark = inputUrl.split("\\?");
            if (splitQmark.length == 2) {
                String[] splitSlash = splitQmark[0].split("/");
                String[] splitAmp = splitQmark[1].split("&");
                category = splitSlash[1];
                function = splitSlash[2];
                for (int i = 0; i < splitAmp.length; i++) {
                    String[] keyValue = splitAmp[i].split("=");
                    parameter.put(keyValue[0], keyValue[1]);
                }
            } else{
                String[] splitSlash = inputUrl.split("/");
                category = splitSlash[1];
                function = splitSlash[2];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("요청 url이 올바르지 않습니다.");
        }
    }
}
