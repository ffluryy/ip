public class ToDo extends Task {
    private static final String symbol = "T";

    public ToDo(String name, boolean complete) {
        super(name, complete);
    }

    public ToDo() {
        super();
    }

    @Override
    public String toString() {
        return "[" + symbol + "][" +
                (isComplete() ? "X" : " ") +
                "] " +
                getName().strip();
    }
}