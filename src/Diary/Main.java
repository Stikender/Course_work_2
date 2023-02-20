package Diary;

import Diary.Exception.IncorrectArgumentException;
import Diary.Exception.TaskNotFoundException;

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
       TASK_SERVICE.addTask(new OneTimeTask("Награждение сотрудников", TaskType.WORK, LocalDateTime.now().plusDays(3), "Вручение благодарностей, премии"));
       TASK_SERVICE.addTask(new DailyTask("Отдых на свежем воздухе", TaskType.PERSONAL, LocalDateTime.now().plusDays(1),"Прогулка"));
       TASK_SERVICE.addTask(new WeeklyTask("Итоги недели", TaskType.WORK, LocalDateTime.now().plusHours(5), "Подведение итогов недели, планирование на будущую неделю"));
       TASK_SERVICE.addTask(new YearlyTask("Встреча Нового года!", TaskType.PERSONAL, LocalDateTime.of(2023,12, 31, 23, 59, 59), "Наливаем шампанское, загадываем желание"));

        addTask(scanner);
//        removeTasks(scanner);
//        printTaskForDate(scanner);
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
                break;
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
                    System.out.println(taskType.ordinal() + ". " + taskType.getTaskType());
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
                    System.out.println(repeatability.ordinal() + ". " + repeatability.getRepeatabilityType());
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
            System.out.println(task.getId() + ". Название задачи: " + task.getTitle() + ", Тип задачи: " + task.getTaskType() +
                    ", Дата задачи: " + task.getDateTime().format(DATE_FORMATTER) +
                    ", Время задачи: " + task.getDateTime().format(TIME_FORMATTER) +
                    ", Повторяемость задачи: " + task.getRepeatabilityType() +
                    ", Описание задачи: " + task.getDescription());
        }
    }

    private static void removeTasks(Scanner scanner) {
        System.out.println("Список всех задач:");
        for (Task task : TASK_SERVICE.getAllTasks()) {
            System.out.println(task.getId() + ". Название задачи: " + task.getTitle() +
                    ", Тип задачи: " + task.getTaskType() +
                    ", Дата задачи: " + task.getDateTime().format(DATE_FORMATTER) +
                    ", Время задачи: " + task.getDateTime().format(TIME_FORMATTER) +
                    ", Повторяемость задачи: " + task.getRepeatabilityType()+
                    ", Описание задачи: "  + task.getDescription());
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