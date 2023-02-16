public enum Type {

    WORK("Рабочая"),
    PERSONAL("Личная");
public String type;

    Type(String type) throws IncorrectArgumentException {
        if (type == null || type.isEmpty() || type.isBlank()) {
            throw new IncorrectArgumentException("Не корректно введен тип задачи");
        } else {
            this.type = type;
        }
    }
}
