
package Module01.ex03;

public class UserIdsGenerator {
    private static int lastId;
    private static UserIdsGenerator instance;

    private UserIdsGenerator() {
        lastId = 0;
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null)
            instance = new UserIdsGenerator();
        return instance;
    }

    public int generateId(){
        return ++lastId;
    }
}
