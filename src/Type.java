public enum Type {

    WORK("Рабочая"),
    PERSONAL("Личная");
public String type;

    Type(String type) {
        if (type == null || type.isEmpty() || type.isBlank()) {
            try {
                throw new IncorrectArgumentException("Не корректно введен тип задачи");
            } catch (IncorrectArgumentException e) {
                System.out.println("Не корректно введен тип задачи");
            }

        } else {
            this.type = type;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getType();
    }

}
