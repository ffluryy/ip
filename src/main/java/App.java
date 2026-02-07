public class App {
    private final Io io;
    private final TaskList taskList;
    private final Parser parser;

    public App(Io io, TaskList taskList, Parser parser) {
        this.io = io;
        this.taskList = taskList;
        this.parser = parser;
    }

    public void run() {
        boolean running = true;
        Response r;
        Io.show(parser.parseInput("hi", taskList).message());
        while (running) {
            String userInput = io.readCommand();
            r = parser.parseInput(userInput, taskList);
            Io.show(r.message());
            running = r.running();
        }
    }

}