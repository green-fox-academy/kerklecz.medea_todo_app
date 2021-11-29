package todo;

public class TodoApp {


    public static void executeOperation(String[] args, Todos todos){
        if (args.length == 0) {
            todos.startWithoutArgument();
        } else if (!args[0].equals("-l")&& !args[0].equals("-a")&& !args[0].equals("-r")&&!args[0].equals("-c")){
            System.out.println("Nem támogatott argumentum\n");
            todos.startWithoutArgument();
        } else if (args[0].equals("-l")) {
            todos.printToConsole();
        } else if (args[0].equals("-a")) {
            if (args.length == 1) {
                throw new InvalidTodosOperationException("Nem lehetséges új feladat hozzáadása: nincs megadva a feladat!");
            } else {
                todos.setTodos(args[1]);
                todos.addTodosToFile();
            }
        } else if(args[0].equals("-r")){
            if(args.length == 1){
                throw new InvalidTodosOperationException("Nem lehetséges az eltávolítás: nem adott meg indexet!");
            }
            try{
                int index = Integer.parseInt(args[1]);
                todos.removeTodo(index);
            } catch (NumberFormatException e){
                throw new InvalidTodosOperationException("Nem lehetséges az eltávolítás: a megadott index nem szám!");
            }
        } else if(args[0].equals("-c")){
            if(args.length == 1){
                throw new InvalidTodosOperationException("Nem lehetséges a feladat végrehajtása: nem adtál meg indexet!");
            }
            try{
                int index = Integer.parseInt(args[1]);
                todos.todoDone(index);
            } catch (NumberFormatException e){
                throw new InvalidTodosOperationException("Nem lehetséges az eltávolítás: a megadott index nem szám!");
            }
        }
    }

    public static void main(String[] args) {

        Todos todos = new Todos();

        executeOperation(args, todos );

        System.out.println(todos.getTodos().get(0).isDone());







    }
}
