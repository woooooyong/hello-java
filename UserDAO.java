import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String url = "jdbc:h2:~/test";   // DB 접속 주소
    private String user = "sa";              // 사용자명
    private String password = "";            // 비밀번호

    // 사용자 추가
    public void insertUser(User u) {
        String sql = "INSERT INTO users (name) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, u.name);   // 첫 번째 ? 에 name 값 넣음
            pstmt.executeUpdate();        // SQL 실행
            System.out.println("User 저장 완료: " + u.name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 사용자 전체 조회
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                list.add(new User(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
