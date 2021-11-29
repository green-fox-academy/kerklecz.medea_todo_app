package todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Todos {

    private static List<Todo> todos = new ArrayList<>();

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
        List<String> todosStrings = new ArrayList<>();
        for (Todo todo : todos
        ) {
            todosStrings.add(todo.toString());
        }
        try {
            Files.write(path, todosStrings);
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
            todos.get(todoIndex-1).setDone(true);
            addTodosToFile();
        }
    }



    public Todo parseTodoString(String todoString) {
        String[] parseArray = todoString.split(";");
        return new Todo(parseArray[0], Boolean.parseBoolean(parseArray[1]));
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
            todos.add(parseTodoString(tdName));
        }
    }
}
