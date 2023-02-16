public class TaskNotFoundException extends Throwable {
    public TaskNotFoundException() {
        System.out.println("Не корректно введен номер id");
    }
}
