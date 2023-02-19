import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(String title, TaskType taskType, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, taskType, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return date.equals(taskDate) || date.isAfter(taskDate);
    }

    @Override
    public Repeatability getRepeatabilityType() {
        return Repeatability.DAILY_TASK;
    }
}
