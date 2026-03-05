package chatbot.task;

/**
 * Represents a task that must be completed by a specific date or time.
 * <p>
 * A {@code Deadline} extends {@link Task} by adding a due date string.
 * It is identified by the "D" symbol.
 */
public class Deadline extends Task {

    /**
     * The date or time by which the task must be completed.
     */
    private String dueDate;

    /**
     * The symbol representing the Deadline task type.
     */
    private static final String SYMBOL = "D";

    /**
     * {@inheritDoc}
     *
     * @param name     the name or description of the task
     * @param complete whether the task is completed
     * @param dueDate  the date or time the task is due
     */
    public Deadline(String name, boolean complete, String dueDate) {
        super(name, complete);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a default {@code Deadline} with placeholder values.
     */
    public Deadline() {
        this("Empty", false, "No due date");
    }

    /**
     * Sets the due date of the task.
     *
     * @param dueDate the new due date
     */
    public void setBy(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns the due date of the task.
     *
     * @return the due date string
     */
    public String getBy() {
        return dueDate;
    }

    /**
     * Returns a string representation of the Deadline task, including its type symbol,
     * completion status, name, and the due date.
     *
     * @return a formatted string representing the Deadline
     */
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

    /**
     * {@inheritDoc}
     *
     * @return {@code "Deadline"}
     */
    @Override
    public String getTypeLabel() {
        return "Deadline";
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation concatenates the task name and the due date.
     *
     * @return {@inheritDoc} plus the due date
     */
    @Override
    public String getInfo() {
        return (getName() + getBy()).toLowerCase();
    }
}