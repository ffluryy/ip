package chatbot.task;

/**
 * Represents a generic task in the chatbot task list.
 * <p>
 * A {@code Task} contains a name and a completion status. This class serves
 * as the base class for specific task types such as {@code Todo}, {@code Deadline},
 * and {@code Event}. Subclasses may extend this class to include additional
 * information relevant to the specific task type.
 */
public abstract class Task {

    /** The name or description of the task. */
    private String name;

    /** Indicates whether the task has been completed. */
    private boolean complete;

    /**
     * Constructs a {@code Task} with the specified name and completion status.
     *
     * @param name the name or description of the task
     * @param complete whether the task is completed
     */
    public Task(String name, boolean complete) {
        setName(name);
        setComplete(complete);
    }

    /**
     * Constructs a default {@code Task} with placeholder values.
     */
    public Task() {
        this("Empty", false);
    }

    /**
     * Sets the name of the task.
     *
     * @param name the new task name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the task.
     *
     * @return the task name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param complete {@code true} if the task is completed, {@code false} otherwise
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    /**
     * Returns whether the task has been completed.
     *
     * @return {@code true} if the task is complete, otherwise {@code false}
     */
    public boolean isComplete() {
        return this.complete;
    }

    /**
     * Returns a label representing the type of task.
     * <p>
     * Subclasses may override this method to return a more specific type label.
     *
     * @return the task type label
     */
    public String getTypeLabel() {
        return "Task";
    }

    /**
     * Returns the information used for searching or indexing the task.
     * <p>
     * By default, this returns the task name. Subclasses may override this
     * method to include additional fields such as dates or times.
     *
     * @return a string containing searchable task information
     */
    public String getInfo(){
        return name.toLowerCase();
    }
}