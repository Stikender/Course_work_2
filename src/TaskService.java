import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class TaskService {
    private final Map<Integer, Task> taskMap = new HashMap<>();

    public void addTask(Task task) {
        this.taskMap.put(task.getId(), task);
    }

    public Collection<Task> getAllTasks() {
        return this.taskMap.values();
    }

    public Collection<Task> getAllByDate(LocalDate date) {
        TreeSet<Task> taskAllByDate = new TreeSet<>();
        for (Task task : taskMap.values()) {
            if (task.appearsIn(date)) {
                taskAllByDate.add(task);
            }
        }
        return getAllTasks();
    }

    public void removedTasks(int id) throws TaskNotFoundException{
        if (this.taskMap.containsKey(id)) {
            this.taskMap.remove(id);
        } else {
            throw new TaskNotFoundException();
        }

    }

}
