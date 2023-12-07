package DataStructures;

import Books.FileReading;
import Books.FileStorage;
import User.User;

import java.util.HashMap;
import java.util.Hashtable;

public class UserHash {
    private static UserHash userHash;

    public Hashtable<Integer, String> userCredentials;
    private UserHash(){
        userCredentials = FileStorage.readFromCsv("Books/users.csv");
    }
    public Hashtable<Integer,String> getUserCredentials() {
        return userCredentials;
    }
    public static UserHash getInstance(){
        if (userHash == null)
            userHash = new UserHash();
        return userHash;
    }

    public void saveNewData(Integer i,String d){
        FileStorage.saveToCsv("Books/users.csv",i,d);
    }
}
