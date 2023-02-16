import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {

    private int idGenerator;
    private String title;
    private Type type;
    private int id;
    private LocalDateTime dateTime;
    private String description;

    public Task(String title, Type type, int id, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        this.idGenerator = 1;
        if (title == null || title.isEmpty() || title.isBlank()) {
            throw new IncorrectArgumentException("Не корректно введен заголовок");
        } else {
            this.title = title;
        }
            this.type = type;
        if (Double.compare(id, 0) == 0) {
            throw new IncorrectArgumentException("Не корректно введен id задачи");
        } else {
            this.id = id;
        }
        this.dateTime = dateTime;
        if (description == null || description.isEmpty() || description.isBlank()) {
            throw new IncorrectArgumentException("Не корректное описание задачи");
        } else {
            this.description = description;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
