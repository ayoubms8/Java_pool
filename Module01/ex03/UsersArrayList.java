package Module01.ex03;

public class UsersArrayList implements UsersList{
    private User[] Users;
    private int size;
    private int capacity;

    public UsersArrayList() {
        this.Users = new User[10];
        this.size = 0;
        this.capacity = 10;
    }

    public void addUser(User newUser) {
        if (size == capacity) {
            capacity = capacity + (capacity / 2);
            User[] newUsers = new User[capacity];
            for (int i = 0; i < size; i++) {
                newUsers[i] = Users[i];
            }
            Users = newUsers;
        }
        Users[size] = newUser;
        size++;
    }

    public User getUserById(int id){
        for(int i = 0; i < size ; i++) {
            if (Users[i].getId() == id) {
                return (Users[i]);
            }
        }
        throw new UserNotFoundException("User with id " + id + " not found");
    }

    public User getUserByIndex(int idx){
        if (idx < size && idx >= 0)
            return Users[idx];
        throw new UserNotFoundException("User at index " + idx + " not found");
    }
    
    public int size() {
        return size;
    }
}
