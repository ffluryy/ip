package chatbot.task;

public class Deadline extends Task {
    private String dueDate;
    private static final String SYMBOL = "D";

    public Deadline(String name, boolean complete, String dueDate) {
        super(name, complete);
        this.dueDate = dueDate;
    }

    public Deadline() {
        this("Empty", false, "No due date");
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "][" +
                (isComplete() ? "X" : " ") +
                "] " +
                getName().strip() +
                " (by: " +
                dueDate.strip() +
                ")";

    }
    @Override
    public String getTypeLabel() {
        return "Deadline";
    }
}