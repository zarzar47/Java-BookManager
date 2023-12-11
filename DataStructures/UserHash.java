package DataStructures;

import Books.FileReading;
import Books.FileStorage;
import User.User;

import java.util.HashMap;
import java.util.Hashtable;

public class UserHash {
    //TODO make into a custom hash
    private static UserHash userHash;

    public intHashMap<User> userCredentials;
    private UserHash(){
        userCredentials = FileStorage.readFromCsv();
    }

    public static intHashMap<User> getUsers(){
        return FileStorage.readFromCsv();
    }

    public intHashMap<User> getUserCredentials() {
        return userCredentials;
    }
    public static UserHash getInstance(){
        System.out.println("yes");
        if (userHash == null)
            userHash = new UserHash();
        return userHash;
    }

    public void saveNewData(){
        FileStorage.saveToCsv(userCredentials);
    }
}
