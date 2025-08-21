import org.json.JSONObject;

public class TestJson {
    public static void main(String[] args) {
        String str = "{ \"name\":\"우용\", \"age\":25 }";
        
        JSONObject obj = new JSONObject(str);
        System.out.println("이름: " + obj.getString("name"));
        System.out.println("나이: " + obj.getInt("age"));
    }
}