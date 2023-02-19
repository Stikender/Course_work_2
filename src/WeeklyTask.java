import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task{

    public WeeklyTask(String title, TaskType taskType, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, taskType, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return date.equals(taskDate) || (date.isAfter(taskDate) && date.getDayOfWeek().equals(taskDate.getDayOfWeek()));
    }

    @Override
    public Repeatability getRepeatabilityType() {
        return Repeatability.WEEKLY_TASK;
    }
}
