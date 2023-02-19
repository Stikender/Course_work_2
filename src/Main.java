import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final TaskService TASK_SERVICE = new TaskService();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название задачи:");
        if (scanner.hasNext()) {
            String title = scanner.nextLine();
        } else {
            System.out.println("Введено не корректное значение");
        }

    }

    private static Type readType(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберете тип задачи:");
                for (Type type : Type.values()) {
                    System.out.println(type.ordinal() + ". " + localizeType(type));
                }
                System.out.println("Введите тип:");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return Type.values()[ordinal];
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




    private static String localizeType(Type type) {
        return switch (type) {
            case WORK:
                System.out.println("Рабочая задача");
                break;
            case PERSONAL:
                System.out.println("Личная задача");
                break;
            default:
                throw new IllegalArgumentException();
        };
    }

    private static String localizeRepeatability(Repeatability repeatability) {
        return switch (repeatability) {
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
    }

    private static LocalDateTime readDateTime(Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        LocalTime localTime = readTime(scanner);
        return localDate.atTime(localTime);
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
}