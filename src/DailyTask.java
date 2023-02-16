import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(int idGenerator, String title, Type type, int id, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, type, id, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return date.equals(taskDate) || date.isAfter(taskDate);
    }
}