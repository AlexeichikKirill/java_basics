package response;
public class ToDoList {

    private int id = 0;
    private String authorName;
    private String message;

    public ToDoList(String authorName, String message) {
        int id = this.id++;
        setId(id);
        this.authorName = authorName;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
