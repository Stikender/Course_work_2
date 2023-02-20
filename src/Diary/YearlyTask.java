package Diary;

import Diary.Exception.IncorrectArgumentException;
import Diary.Repeatability;
import Diary.Task;
import Diary.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask(String title, TaskType taskType, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, taskType, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return date.equals(taskDate) || (date.isAfter(taskDate) && date.getDayOfYear() == taskDate.getDayOfYear());
    }

    @Override
    public Repeatability getRepeatabilityType() {
        return Repeatability.YEARLY_TASK;
    }
}
