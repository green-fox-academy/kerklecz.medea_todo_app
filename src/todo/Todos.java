package todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Todos {

    private List<Todo> todos;

    public Todos() {
        this.todos = new ArrayList<>();
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(String todoName) {
        todos.add(new Todo(todoName));
    }

    public void startWithoutArgument() {
        String starter =
                "Parancssori Todo applikáció \n" +
                        "==============================\n \n" +
                        "Parancssori argumentumok\n" +
                        "   -l   Kilistázza a feladatokat\n" +
                        "   -a   Új feladatot ad hozzá\n" +
                        "   -r   Eltávolít egy feladatot\n" +
                        "   -c   Teljesít egy feladatot";
        System.out.println(starter);
    }

    public void addTodosToFile() {
        Path path = Paths.get("todos.txt");
        List<String> todosName = new ArrayList<>();
        for (Todo td:todos
             ) {
            todosName.add(td.getName());
        }
        try {
            Files.write(path, todosName);
        } catch (IOException e) {
            throw new IllegalArgumentException("Nem sikerült a fájlba írás");
        }
    }

    public void printToConsole() {
        Path path = Paths.get("todos.txt");
        List<String> todos;

        try {
            todos = Files.readAllLines(path);
        } catch (IOException e) {
            throw new IllegalStateException("Nem sikerült a fájlbeolvasás");
        }

        for (int i = 0; i < todos.size(); i++) {
            System.out.println(i+1 + " - " + todos.get(i));
        }
    }
}
