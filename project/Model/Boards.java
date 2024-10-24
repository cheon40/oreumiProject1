package project.project.Model;

public class Boards {
    private String boardName;
    private int uIdBoards; //게시판 생성자의 id

    public Boards(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void setuIdBoards(int uIdBoards) {
        this.uIdBoards = uIdBoards;
    }
}
