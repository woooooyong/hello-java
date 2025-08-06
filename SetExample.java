import java.util.*;

public class SetExample {

    public static void main(String[] args) {
        Set<String> names = new HashSet<>();
        
        
        names.add("wooyong");
        names.add("future");
        names.add("wooyong");

        System.out.println("전체 출력:");
        for (String name : names){
            System.out.println(name);
        }

        System.out.println("사이즈: " + names.size());
        names.remove("wooyong");
        System.out.println("삭제 후: " + names);
    }
}