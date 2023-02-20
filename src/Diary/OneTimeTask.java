package Diary;

import Diary.Exception.IncorrectArgumentException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {
    public OneTimeTask(String title, TaskType taskType, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, taskType, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return date.equals(this.getDateTime().toLocalDate());
    }

    @Override
    public Repeatability getRepeatabilityType() {
        return Repeatability.ONE_TIME_TASK;
    }
}
