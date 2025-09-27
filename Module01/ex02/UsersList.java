package Module01.ex02;

public interface UsersList {
    void addUser(User newUser);
    User getUserById(int id);
    User getUserByIndex(int idx);
    int size();
}
