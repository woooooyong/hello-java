import com.google.gson.Gson;

class User {
    int id;
    String name;
}

public class GsonExample {

    public static void main(String[] args) {
        String json = "{\"id\":1,\"name\":\"홍길동\"}";

        Gson gson = new Gson();

        User user = gson.fromJson(json, User.class);

        System.out.println("id: " + user.id);
        System.out.println("name: " + user.name);
    }
}
