import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task implements Comparable<Task>{

    private static int idGenerator = 0;
    private final int id;
    private final String title;
    private final Type type;
    private final LocalDateTime dateTime;
    private final String description;

    public Task(String title, Type type, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        this.id = idGenerator++;
        if (title == null || title.isEmpty() || title.isBlank()) {
            throw new IncorrectArgumentException("Не корректно введен заголовок");
        } else {
            this.title = title;
        }
        this.type = type;
        if (Double.compare(id, 0) == 0) {
            throw new IncorrectArgumentException("Не корректно введен id задачи");
        } else {
        }
        this.dateTime = dateTime;
        if (description == null || description.isEmpty() || description.isBlank()) {
            throw new IncorrectArgumentException("Не корректное описание задачи");
        } else {
            this.description = description;
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return idGenerator == task.idGenerator && getId() == task.getId() && Objects.equals(getTitle(), task.getTitle()) && Objects.equals(getType(), task.getType()) && Objects.equals(getDateTime(), task.getDateTime()) && Objects.equals(getDescription(), task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenerator, getTitle(), getType(), getId(), getDateTime(), getDescription());
    }

    @Override
    public String toString() {
        return "Task{" +
                "idGenerator=" + (idGenerator++) +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                '}';
    }

    public abstract boolean appearsIn(LocalDate date);

    public abstract Repeatability getRepeatabilityType();

    public int compareTo(Task otherTask) {
        if (otherTask == null) {
        return 1;
        }
        return this.dateTime.toLocalTime().compareTo(otherTask.dateTime.toLocalTime());
    }
}
