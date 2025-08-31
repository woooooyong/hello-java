import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String url = "jdbc:h2:~/test";   // DB 접속 주소
    private String user = "sa";              // 사용자명
    private String password = "";            // 비밀번호

    // ✅ 사용자 추가 (Create)
    public void insertUser(User u) {
        String sql = "INSERT INTO users (name) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, u.getName());
            pstmt.executeUpdate();
            System.out.println("User 저장 완료: " + u.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ 사용자 전체 조회 (Read-All)
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

    // ✅ 단건 조회 (Read-One)
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ 사용자 수정 (Update)
    public boolean updateUser(User u) {
        String sql = "UPDATE users SET name = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, u.getName());
            pstmt.setInt(2, u.getId());
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ 사용자 삭제 (Delete)
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
