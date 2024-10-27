package project.project.Model;

import project.project.ExceptionSet.WrongUrlException;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private static boolean isLoggedIn = false;

    private String emptySpace;
    private String category;
    private String function;
    private Map<String, String> parameter = new HashMap<>();

    public Request(String inputUrl) {
        String[] splitQmark = inputUrl.split("\\?");
        if (splitQmark.length == 2) {
            String[] splitSlash = splitQmark[0].split("/");
            String[] splitAmp = splitQmark[1].split("&");
            emptySpace = splitSlash[0];
            category = splitSlash[1];
            function = splitSlash[2];
            for (int i = 0; i < splitAmp.length; i++) {
                String[] keyValue = splitAmp[i].split("=");
                if(keyValue.length == 2) {
                    parameter.put(keyValue[0], keyValue[1]);
                } else{
                    System.out.println("url을 확인해주세요");
                }
            }
        } else{
            String[] splitSlash = inputUrl.split("/");
            if (splitSlash.length == 3) {
                emptySpace = splitSlash[0];
                category = splitSlash[1];
                function = splitSlash[2];
            } else{
                System.out.println("url을 확인해주세요");
            }
        }
        if(category != null && !(emptySpace.isEmpty())){
            try {
                throw new WrongUrlException();
            } catch (WrongUrlException e) {
                System.out.println("url은 '/'로 시작합니다.");
                category = null;
            }
        } else if(!(category == null) && !(category.equals("posts") ||
                category.equals("boards") || category.equals("accounts")))  {
            try {
                throw new WrongUrlException();
            } catch (WrongUrlException e) {
                System.out.println(category+"에 해당하는 url이 없습니다.");
            }
        }
    }

    public static boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public static void setIsLoggedIn(boolean isLoggedIn) {
        Request.isLoggedIn = isLoggedIn;
    }

    public String getCategory() {
        return category;
    }

    public String getFunction() {
        return function;
    }

    public Map<String, String> getParameter() {
        return parameter;
    }
}
