import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task{
    public OneTimeTask(int idGenerator, String title, Type type, int id, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, type, id, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return date.equals(this.getDateTime().toLocalDate());
    }
}
