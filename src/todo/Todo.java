package todo;

public class Todo {

    private String name;
    private boolean isDone;

    public Todo(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Todo(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }


    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return name + ';' + isDone;
    }
}
