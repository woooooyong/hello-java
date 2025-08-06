import java.util.*;
import java.util.stream.*;

public class StreamExample {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("wooyong", "minji", "jihun"));

        names.stream()
             .map(name -> name.toUpperCase())
             .forEach(System.out::println);

        List<String> words = new ArrayList<>(Arrays.asList("cat", "elephant", "tiger", "ant"));

        words.stream()
             .filter(word -> word.length() >= 5)
             .forEach(System.out::printf);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        numbers.stream()
               .filter(n -> n%2 ==0)
               .map(n -> n*n)
               .forEach(System.out::println);

        List<String> list = Arrays.asList("apple", "banana", "kiwi", "grape");

        List<String> result = list.stream()
                                  .filter(s -> s.length() <= 5)
                                  .collect(Collectors.toList());
        
        System.out.println(result);
    }
}