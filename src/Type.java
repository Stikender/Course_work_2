public enum Type {

    WORK("Рабочая"),
    PERSONAL("Личная");
public String type;

    Type(String type)  {
        if (type == null || type.isEmpty() || type.isBlank()) {
            this.type = "Не корректное значение";
        } else {
            this.type = type;
        }
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getType();
    }
}
