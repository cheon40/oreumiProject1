package project.study1;

import java.util.HashMap;
import java.util.Map;

public class map {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1번", "조경천");
        map.put("2번", "조경만");
        map.put("3번", "조경억");

        String 경천 = map.get("1번");
        System.out.println("경천 = " + 경천);
    }
}
