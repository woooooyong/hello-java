public class JdbcDaoExample {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();

        // INSERT (저장)
        dao.insertUser(new User(0, "김우용"));

        // SELECT (조회)
        for (User u : dao.getUsers()) {
            System.out.println("id=" + u.id + ", name=" + u.name);
        }
    }
}
