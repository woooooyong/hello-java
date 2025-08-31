import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public void addUser(User u) {
        if (u.getName()== null || u.getName().isEmpty()){
            throw new IllegalArgumentException("이름은 필수입니다");
        }
        userDAO.insertUser(u);
    }

    public List<User> getAllUsers(){
        return userDAO.getUsers();
    }

    public User getUser(int id){
        return userDAO.getUserById(id);
    }

    public boolean updateUser(User u){
        return userDAO.updateUser(u);
    }

    public boolean deleteUser(int id){
        return userDAO.deleteUser(id);
    }
}
