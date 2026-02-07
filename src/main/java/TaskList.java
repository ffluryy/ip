import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean remove(Task task) {
        return tasks.remove(task);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

}