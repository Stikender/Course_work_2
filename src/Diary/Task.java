package Diary;

import Diary.Exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


public abstract class Task implements Comparable<Task>{

    private static int idGenerator = 0;
    private final int id;
    private final String title;
    private final TaskType taskType;
    private final LocalDateTime dateTime;
    private final String description;

    public Task(String title, TaskType taskType, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        this.id = idGenerator++;
        if (title == null || title.isEmpty() || title.isBlank()) {
            throw new IncorrectArgumentException("Не корректно введен заголовок");
        } else {
            this.title = title;
        }
        this.taskType = taskType;
        if (dateTime == null) {
            throw new IncorrectArgumentException("Введено нулевое значение");
        } else {
            this.dateTime = dateTime;
        }
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

    public TaskType getTaskType() {
        return taskType;
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
        return getId() == task.getId() && Objects.equals(getTitle(), task.getTitle()) && Objects.equals(getTaskType(), task.getTaskType()) && Objects.equals(getDateTime(), task.getDateTime()) && Objects.equals(getDescription(), task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getTaskType(), getDateTime(), getDescription());
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
