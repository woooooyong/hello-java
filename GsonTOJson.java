import com.google.gson.Gson;

class User {
    int id;
    String name;
}

public class GsonToJson {
    public static void main(String[] args) {
        // 1. User 객체 생성
        User user = new User();
        user.id = 2;
        user.name = "김우용";

        // 2. Gson 객체 생성
        Gson gson = new Gson();

        // 3. User 객체 → JSON 문자열 변환
        String json = gson.toJson(user);

        // 4. 결과 출력
        System.out.println(json);
    }
}
