package project.project;

import java.time.LocalDateTime;

public class Posts {
    String title;
    String contents;
    int BoardIndex;
    String addTime;
    String editTime;

    public Posts(String title, String content, int BoardIndex, String editTime) {
        this.title = title;
        this.contents = content;
        this.BoardIndex = BoardIndex;
        this.addTime = addTime = LocalDateTime.now().toString();
        this.editTime = editTime;
    }

    public String getTitle() {
        return title;
    }

    public String getAddTime() {
        return addTime;
    }

    public int getBoardIndex() {
        return BoardIndex;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
