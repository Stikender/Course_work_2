import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;

//import java.lang.reflect.Type;

public class Main {
    private static final TaskService TASK_SERVICE = new TaskService();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) throws IncorrectArgumentException {
        Scanner scanner = new Scanner(System.in);
       TASK_SERVICE.addTask(new OneTimeTask("title1", TaskType.PERSONAL, LocalDateTime.now().plusDays(1),"Walk"));
        addTask(scanner);
    }

    private static void addTask(Scanner scanner) throws IncorrectArgumentException {
        System.out.println("Введите заголовок задачи:");
        String title = scanner.nextLine();
        TaskType taskType = readType(scanner);
        LocalDateTime taskDate = readDateTime(scanner);
        System.out.println("Введите описание задачи: ");
        String description = scanner.nextLine();
        Repeatability repeatability = readRepeatability(scanner);
        Task task;
        switch (repeatability) {
            case ONE_TIME_TASK:
                task = new OneTimeTask(title, taskType, taskDate, description);
                break;
            case DAILY_TASK:
                task = new DailyTask(title, taskType, taskDate, description);
                break;
            case WEEKLY_TASK:
                task = new WeeklyTask(title, taskType, taskDate, description);
            case MONTHLY_TASK:
                task = new MonthlyTask(title, taskType, taskDate, description);
                break;
            case YEARLY_TASK:
                task = new YearlyTask(title, taskType, taskDate, description);
            default:
                throw new IllegalArgumentException();
        };
        TASK_SERVICE.addTask(task);
    }

    private static TaskType readType(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберете тип задачи:");
                for (TaskType taskType : TaskType.values()) {
                    System.out.println(taskType.ordinal() + ". " + localizeType(taskType));
                }
                System.out.println("Введите тип:");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return TaskType.values()[ordinal];
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не верный номер задачи");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Не найдет тип задачи");
            }
        }
    }

    private static Repeatability readRepeatability(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберете тип повторяемости задачи:");
                for (Repeatability repeatability : Repeatability.values()) {
                    System.out.println(repeatability.ordinal() + ". " + localizeRepeatability(repeatability));
                }
                System.out.println("Введите тип:");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return Repeatability.values()[ordinal];
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не верный номер задачи");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Не найдет тип задачи");}
        }
    }
    private static String localizeType(TaskType taskType) {
        switch (taskType) {
            case WORK:
            System.out.println("Рабочая задача");
            break;
            case PERSONAL:
            System.out.println("Личная задача");
            break;
            default:
            throw new IllegalArgumentException();
        };
        return localizeType(taskType);
    }
    private static String localizeRepeatability(Repeatability repeatability) {
        switch (repeatability) {
            case ONE_TIME_TASK:
                System.out.println("Разовая");
                break;
            case DAILY_TASK:
                System.out.println("Ежедневная");
                break;
            case WEEKLY_TASK:
                System.out.println("Еженедельная");
                break;
            case MONTHLY_TASK:
                System.out.println("Ежемесячная");
                break;
            case YEARLY_TASK:
                System.out.println("Ежегодная");
                break;
            default:
                throw new IllegalArgumentException();
        };
        return null;
    }

    private static LocalDate readDate(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Введите дату задачи (формат d.MM.yyyy):");
                String dateLine = scanner.nextLine();
                return LocalDate.parse(dateLine, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Введен не верный формат даты");
            }
        }
    }

    private static LocalTime readTime(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Введите время задачи (формат HH:mm):");
                String dateLine = scanner.nextLine();
                return LocalTime.parse(dateLine, TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Введен не верный формат времени");
            }
        }
    }

    private static LocalDateTime readDateTime(Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        LocalTime localTime = readTime(scanner);
        return localDate.atTime(localTime);
    }
    private static void printTaskForDate(Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        Collection<Task> taskForDate = TASK_SERVICE.getAllByDate(localDate);
        System.out.println("Задачи на " + localDate.format(DATE_FORMATTER));
        for (Task task : taskForDate) {
            System.out.println(task.getTitle() + ", " + task.getTaskType() +
                    ", " + task.getDateTime().format(TIME_FORMATTER) + ", " + task.getDescription());
        }
    }

    private static void removeTasks(Scanner scanner) {
        System.out.println("Список всех задач:");
        for (Task task : TASK_SERVICE.getAllTasks()) {
            System.out.println(task.getId() + ", " + task.getTitle() +
                    ", " + task.getTaskType() +
                    ", " + localizeRepeatability(task.getRepeatabilityType()));
        }
        while (true) {
            try {
                System.out.println("Выберите задачу для удаления:");
                String idLine = scanner.nextLine();
                int id = Integer.parseInt(idLine);
                TASK_SERVICE.removedTasks(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не верный id номер");
            } catch (TaskNotFoundException e) {
                throw new RuntimeException("Задача для удаления не найдена");
            }
        }
    }
}