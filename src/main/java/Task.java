public abstract class Task {
    private String name;
    private boolean complete;

    public Task(String name, boolean complete) {
        setName(name);
        setComplete(complete);
    }

    public Task() {
        this("Empty", false);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public String getTypeLabel() {
        return "Task";
    }
}