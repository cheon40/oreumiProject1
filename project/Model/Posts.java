package project.project.Model;

import java.time.LocalDateTime;

public class Posts {
    private String title;
    private String contents;
    private String addTime;
    private String editTime;
    private String BoardName; //작성된 게시판의 이름
    private int uIdPosts; //작성한 유저의 id

    public Posts(String title, String content, String editTime, String BoardName, int uIdPosts) {
        this.title = title;
        this.contents = content;
        this.addTime = addTime = LocalDateTime.now().toString();
        this.editTime = editTime;
        this.BoardName = BoardName;
        this.uIdPosts = uIdPosts;
    }

    public String getTitle() {
        return title;
    }

    public String getAddTime() {
        return addTime;
    }

    public String getContents() {
        return contents;
    }

    public String getBoardName() {
        return BoardName;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
