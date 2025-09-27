package Module01.ex02;

public class Program {
    public static void main(String[] args) {
        UsersArrayList usersList = new UsersArrayList();
        
        System.out.println("Testing UsersArrayList implementation:");
        
        User user1 = new User("Alice", 1000);
        User user2 = new User("Bob", 2000);
        User user3 = new User("Charlie", 3000);
        
        usersList.addUser(user1);
        usersList.addUser(user2);
        usersList.addUser(user3);
        
        System.out.println("Number of users: " + usersList.size());
        
        try {
            User foundUser = usersList.getUserById(user2.getId());
            System.out.println("Found user by ID: " + foundUser.getName() + 
                              " with balance " + foundUser.getBalance());
        } catch (UserNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        try {
            User notFoundUser = usersList.getUserById(9999);
            System.out.println("This should not print: " + notFoundUser.getName());
        } catch (UserNotFoundException e) {
            System.out.println("Exception caught (expected): " + e.getMessage());
        }
        
        try {
            User userAtIndex1 = usersList.getUserByIndex(1);
            System.out.println("User at index 1: " + userAtIndex1.getName());
        } catch (UserNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        

        try {
            User userAtInvalidIndex = usersList.getUserByIndex(10);
            System.out.println("This should not print: " + userAtInvalidIndex.getName());
        } catch (UserNotFoundException e) {
            System.out.println("Exception caught (expected): " + e.getMessage());
        }
        
        System.out.println("\nTesting array resizing by adding more users:");
        for (int i = 4; i <= 15; i++) {
            usersList.addUser(new User("User" + i, i * 100));
        }
        System.out.println("Added users. New count: " + usersList.size());
        
        try {
            User lastUser = usersList.getUserByIndex(usersList.size() - 1);
            System.out.println("Last user: " + lastUser.getName() + 
                              " with ID " + lastUser.getId());
        } catch (UserNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
