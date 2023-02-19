import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task{
    public MonthlyTask(String title, TaskType taskType, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, taskType, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return date.equals(taskDate) || (date.isAfter(taskDate) && date.getDayOfMonth() == taskDate.getDayOfMonth());
    }

    @Override
    public Repeatability getRepeatabilityType() {
        return Repeatability.MONTHLY_TASK;
    }
}
