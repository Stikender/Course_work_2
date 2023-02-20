package TaskFunctionalProgram;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByKey;

public class Main {
    public static void main(String[] args) {

        String s = "yourapp the quick brown fox jumps over the lazy dog";
        System.out.println("TOP 10:");
        Arrays.stream(s.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(comparingByKey()))
                .limit(10)
                .forEach(e -> System.out.println(e.getKey() + " - "+ e.getValue()));
    }
}
