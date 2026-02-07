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

    public boolean remove(int i) {
        try {
            tasks.remove(i);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

}