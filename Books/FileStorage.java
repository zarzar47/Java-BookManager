package Books;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;

public class FileStorage {

    public static void saveToCsv(String filePath, Integer i, String d) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Append data to the CSV file
            writer.write(i + "," + d + "\n");

            System.out.println("Data appended to CSV file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public static Hashtable<Integer, String> readFromCsv(String filePath) {
        Hashtable<Integer, String> data = new Hashtable<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    data.put(Integer.parseInt(parts[0]), parts[1]);
                }
            }
            System.out.println("Data read from CSV file successfully.");
        } catch (IOException e) {
            System.err.println("Error reading from CSV file: " + e.getMessage());
        }

        return data;
    }
}
