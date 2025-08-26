import java.sql.*;

public class JdbcExample {

    public static void main(String[] args) {
        String url = "jdbc:h2:~/test";
        String user = "sa";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("id=" + id + ", name=" + name);
            }

            rs.close();
            stmt.close();
            conn.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}