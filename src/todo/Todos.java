package todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Todos {

    private List<Todo> todos = new ArrayList<>();

    public Todos() {
        readAndAddTodos();
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
        for (Todo todo:todos
             ) {
            todosName.add(todo.getName());
        }

        try {
            Files.write(path, todosName);
        } catch (IOException e) {
            throw new InvalidTodosOperationException("Nem sikerült a fájlba írás");
        }

    }

    public void printToConsole() {
        if (todos.isEmpty()) {
            System.out.println("Nincs mára tennivalód :-)");
        } else {
            for (int i = 0; i < todos.size(); i++) {
                if (todos.get(i).isDone()) {
                    System.out.println((i + 1) + " - " + "[x] " + todos.get(i).getName());
                } else {
                    System.out.println((i + 1) + " - " + "[ ] " + todos.get(i).getName());
                }
            }
        }
    }

    public void removeTodo(int todoIndex) {
        if (todoIndex > todos.size()) {
            throw new InvalidTodosOperationException("Nem lehetséges az eltávolítás: túlindexelési probléma adódott!");
        } else {
            todos.remove(todoIndex - 1);
            addTodosToFile();
        }

    }

    public void todoDone(int todoIndex) {
        if (todoIndex > todos.size()) {
            throw new InvalidTodosOperationException("Nem lehetséges az eltávolítás: túlindexelési probléma adódott!");
        } else {
            for (int i = 0; i < todos.size(); i++) {
                if (i == (todoIndex - 1)) {
                    todos.get(i).setDone(true);
                }
            }
        }
    }

    private List<String> readFromFile() {
        Path path = Paths.get("todos.txt");
        List<String> todosName;
        try {
            todosName = Files.readAllLines(path);
        } catch (IOException e) {
            throw new InvalidTodosOperationException("Nem sikerült a fájlbeolvasás");
        }
        return todosName;
    }

    private void readAndAddTodos() {
        List<String> todosName = readFromFile();
        for (String tdName : todosName
        ) {
            todos.add(new Todo(tdName));
        }
    }
}
