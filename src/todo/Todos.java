package todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
        List<String> todosName = readFromFile();
        if (!todosName.isEmpty()) {
            for (String tdName : todosName
            ) {
                todos.add(new Todo(tdName));
            }
        }
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
        for (Todo td : todos
        ) {
            todosName.add(td.getName());
        }
        try {
            Files.write(path, todosName);
        } catch (IOException e) {
            throw new InvalidTodosOperationException("Nem sikerült a fájlba írás");
        }
    }

    public void printToConsole() {

        List<String> todos = readFromFile();

        if (todos.isEmpty()) {
            System.out.println("Nincs mára tennivalód :-)");
        } else {
            for (int i = 0; i < todos.size(); i++) {
                System.out.println(i + 1 + " - " + todos.get(i));
            }
        }
    }

    public void removeTodo(int todoIndex) {
        List<String> todosName = readFromFile();
        for (String todoName : todosName
        ) {
            todos.add(new Todo(todoName));
        }
        if (todoIndex > todos.size()) {
            throw new InvalidTodosOperationException("Nem lehetséges az eltávolítás: túlindexelési probléma adódott!");
        } else {
            todos.remove(todoIndex - 1);
            addTodosToFile();
        }

    }

    private List<String> readFromFile() {
        Path path = Paths.get("todos.txt");
        List<String> todos;

        try {
            todos = Files.readAllLines(path);
        } catch (IOException e) {
            throw new InvalidTodosOperationException("Nem sikerült a fájlbeolvasás");
        }
        return todos;
    }
}
