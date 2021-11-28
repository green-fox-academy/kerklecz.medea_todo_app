package todo;

public class TodoApp {

    public static void main(String[] args) {

        Todos todos = new Todos();

        todos.setTodos("Kutyát sétáltatni.");
        todos.setTodos("Tejet venni.");
        todos.setTodos("Megcsinálni a leckét.");

        todos.addTodosToFile();

        if (args.length==0) {
            todos.startWithoutArgument();
        } else if (args[0].equals("-l")){
            todos.printToConsole();
        } else if (args[0].equals("-a")){
            todos.setTodos(args[1]);
            todos.addTodosToFile();
        }

        todos.printToConsole();

    }
}
