public class App {
    private static Io io;
    private static TaskList taskList;

    public App(Io io, TaskList taskList) {
        App.io = io;
        App.taskList = taskList;
    }

    public void run() {
        boolean running = true;
        Response r;
        Parser.setName(Config.BOT_NAME);
        Io.show(Parser.parseInput("hi", taskList).message());
        while (running) {
            String userInput = io.readCommand();
            r = Parser.parseInput(userInput, taskList);
            Io.show(r.message());
            running = r.running();
        }
    }

}