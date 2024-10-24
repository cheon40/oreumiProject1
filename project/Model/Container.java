package project.project.Model;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Boards> boardContainer = new ArrayList<>();
    private List<Posts> postContainer = new ArrayList<>();
    private List<Accounts> accountContainer = new ArrayList<>();
    private List<Request> requestContainer = new ArrayList<>();

    private static Container instance = new Container();

    public static Container getInstance() {
        return instance;
    }

    //게시판
    public List<Boards> getBoardContainer() {
        return boardContainer;
    }
    public void createBoard(String boardName) {
        Boards newBoard = new Boards(boardName);
        boardContainer.add(newBoard);
    }

    //게시글
    public List<Posts> getPostContainer() {
        return postContainer;
    }
    public void newPost(String title, String content, String editTime, int BoardIndex, int uIdPosts) {
        Posts newPost = new Posts(title, content, editTime, BoardIndex, uIdPosts);
        postContainer.add(newPost);
    }

    //계정
    public List<Accounts> getAccountContainer() {
        return accountContainer;
    }
    public void newAccount(String id, String password, String nickName, String editDate, String level) {
        Accounts newAccount = new Accounts(id, password, nickName, editDate, level);
        accountContainer.add(newAccount);
    }

    //요청
    public Request newRequest(String inputUrl){
        Request request = new Request(inputUrl);
        requestContainer.add(request);
        return request;
    }
}