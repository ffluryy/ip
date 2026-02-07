public class Main {
    public static void main(String[] args) {
        Io io = new Io();
        TaskList taskList = new TaskList();
        Parser parser = new Parser(Config.BOT_NAME);
        App app = new App(io, taskList, parser);
        app.run();
    }
}
