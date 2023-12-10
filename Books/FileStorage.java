package Books;

import java.io.*;

import DataStructures.LinkedList;
import DataStructures.intHashMap;
import User.User;
import Warehouse.BookStore;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class FileStorage {

    public static void saveToCsv(intHashMap<User> userList) {
        String filename = "./User/users.txt";
        File file = new File(filename);
        try {
            // If the file doesn't exist, create it
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + filename);
            }
            // Write to the file using FileWriter
            FileWriter writer = new FileWriter(file, false); // true for append mode
            LinkedList<User> users = userList.values();
            System.out.println(users.size());
            for (int i = 0; i < users.size(); i++) {
                writer.write(users.get(i).toString());
                System.out.println(users.get(i));
                writer.write("\n");
            }
            writer.close();
            System.out.println("Content successfully written to the file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static intHashMap<User> readFromCsv() {
        intHashMap<User> userList = new intHashMap<>();

        File file = new File("./User/users.txt");
        try {
            if (file.exists()) {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String line;
                User currentUser = null;
                while ((line = in.readLine()) != null) {
                    if (!line.isEmpty()) {
                        String[] parts = line.split(":");
                        if (parts.length == 4) {
                            // If line contains "User ID", create a new user
                            int userID = Integer.parseInt(line.split(" ")[2].substring(0, line.split(" ")[2].length() - 1));
                            String password = line.split(" ")[4].substring(0, line.split(" ")[4].length() - 1);
                            currentUser = new User(userID, password);
                            userList.put(userID, currentUser);
                        } else {
                            // If not, assume it's a book line and parse it
                            String[] bookParts = line.split(":");
                            String isbn = extractValue(line, "ISBN: (\\d+)");
                            String name = extractName(line);
                            String genre = extractValue(line, "Genre: (.+?),");

                            System.out.println("ISBN: " + isbn);
                            System.out.println("Name: " + name);
                            System.out.println("Genre: " + genre);
                            if (currentUser != null) {
                                currentUser.addBook(BookStore.getInstance().getBook(name.trim(), genre.trim()));
                                BookStore.getInstance().buyBook(Integer.parseInt(isbn), currentUser.getUserID());

                                //currentUser.addBook(booklist.get(Integer.parseInt(bookParts[bookParts.length-1].trim())));
                                //booklist.get(Integer.parseInt(bookParts[bookParts.length-1].trim())).decrement();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }


    private static String extractValue(String input, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Not Found";
        }
    }

    private static String extractName(String input) {
        // The name can contain commas and colons, so we extract everything between "Name:" and the next comma
        Pattern namePattern = Pattern.compile("Name: (.*?)(?:,|$)");
        Matcher nameMatcher = namePattern.matcher(input);

        if (nameMatcher.find()) {
            return nameMatcher.group(1).trim();
        } else {
            return "Not Found";
        }
    }


}
