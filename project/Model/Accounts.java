package project.project.Model;

import java.time.LocalDateTime;

public class Accounts {
    final private String id;
    private String password;
    private String nickName;
    final private String signupDate;
    private String editDate;
    private String level;

    public Accounts(String id, String password, String nickName, String editDate, String level) {
        this.id = id;
        this.password = password;
        this.nickName = nickName;
        this.signupDate = LocalDateTime.now().toString();
        this.editDate = editDate;
        this.level = level;
    }


    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSignUpDate() {
        return signupDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
