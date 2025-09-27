package Module01.ex04;

public class UsersArrayList implements UsersList {
    private User[] users;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public UsersArrayList() {
        this.users = new User[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void addUser(User user) {
        if (size >= users.length) {
            resize();
        }
        users[size++] = user;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with ID " + id + " not found");
    }

    @Override
    public User getUserByIndex(int index) throws UserNotFoundException {
        if (index < 0 || index >= size) {
            throw new UserNotFoundException("User at index " + index + " not found");
        }
        return users[index];
    }

    @Override
    public int getNumberOfUsers() {
        return size;
    }

    private void resize() {
        User[] newUsers = new User[users.length * 2];
        System.arraycopy(users, 0, newUsers, 0, size);
        users = newUsers;
    }
}
