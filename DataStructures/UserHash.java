package DataStructures;

import Books.FileReading;
import Books.FileStorage;
import User.User;

import java.util.HashMap;
import java.util.Hashtable;

public class UserHash {
    //TODO make into a custom hash
    private static UserHash userHash;
    private static boolean read = false;

    public static intHashMap<User> userCredentials;
    private UserHash(){
        if (!read){
            userCredentials = FileStorage.readFromCsv();
            read = true;
        }
    }

    public static intHashMap<User> getUsers(){
        if (!read) {
            userCredentials = FileStorage.readFromCsv();
            read = true;
        }
        return userCredentials;
    }

    public intHashMap<User> getUserCredentials() {
        return userCredentials;
    }
    public static UserHash getInstance(){
        if (userHash == null)
            userHash = new UserHash();
        return userHash;
    }

    public void saveNewData(){
        FileStorage.saveToCsv(userCredentials);
    }
}
