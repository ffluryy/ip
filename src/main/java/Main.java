public class Main {
    public static void main(String[] args) {
        Io io = new Io();
        TaskList taskList = new TaskList();
        App app = new App(io, taskList);
        app.run();
    }
}
