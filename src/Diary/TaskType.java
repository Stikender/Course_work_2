package Diary;

public enum TaskType {

    WORK ("Рабочая задача"),
    PERSONAL("Личная задача");
public String taskType;

    TaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return taskType;
    }
}
