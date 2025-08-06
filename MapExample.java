import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        
        Map<String, String> phoneBook = new HashMap<>();

        phoneBook.put("우용", "01086614089");
        phoneBook.put("미래", "010-9876-5432");
        phoneBook.put("우용", "010-0000-0000"); 

        System.out.println("미래의 번호: " + phoneBook.get("미래"));
        System.out.println("전체 출력:");

        for (String name : phoneBook.keySet()){
            System.out.println(name + " : " + phoneBook.get(name));
        }

        System.out.println("총 등록 수: " + phoneBook.size());
        phoneBook.remove("우용");
        System.out.println("우용 삭제 후: " + phoneBook);
    }
}
