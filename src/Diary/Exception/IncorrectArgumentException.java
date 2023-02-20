package Diary.Exception;

public class IncorrectArgumentException extends Throwable {
    private String argument;

    public String getArgument() {
        return argument;
    }

    public IncorrectArgumentException(String argument) {
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "IncorrectArgumentException: " +
                argument;
    }
}
