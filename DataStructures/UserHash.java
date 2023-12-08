package DataStructures;

import Books.FileReading;
import Books.FileStorage;
import User.User;

import java.util.HashMap;
import java.util.Hashtable;

public class UserHash {
    //TODO make into a custom hash
    private static UserHash userHash;

    public Hashtable<Integer, String> userCredentials;
    private UserHash(){
        userCredentials = FileStorage.readFromCsv("User/users.csv");
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
        FileStorage.saveToCsv("User/users.csv",i,d);
    }
}
