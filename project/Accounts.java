package project.project;

import java.time.LocalDateTime;

public class Accounts {
    private String id;
    private String password;
    private String nickName;
    private String signupDate;
    private String editDate;

    public Accounts(String id, String password, String nickName, String editDate) {
        this.id = id;
        this.password = password;
        this.nickName = nickName;
        this.signupDate = LocalDateTime.now().toString();
        this.editDate = editDate;
    }
    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public String getSignUpDate() {
        return signupDate;
    }

    public void setSignUpDate(String signUpDate) {
        this.signupDate = signUpDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
