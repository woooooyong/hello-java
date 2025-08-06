
import java.util.*;

public class ListExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("wooyong");
        names.add("future");
        names.add("wooyong");

        System.out.println("두번째 요소 : " + names.get(1));
        System.err.println("총 개수 : " + names.size());
        System.err.println("삭제 후: ");
        names.remove("wooyong");

        System.err.println(names);
    }
}