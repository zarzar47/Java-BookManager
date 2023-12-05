package Books;
import Warehouse.BookStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileReading
{
    public FileReading(){
        ReadFile();
    }

    public void ReadFile()
    {
        File book_file = new File("Books/books.csv");
        FileReader fileReader;

        try
        {
            fileReader = new FileReader(book_file);

            BookStore bookStore = BookStore.getInstance();
            Scanner scaninput = new Scanner(fileReader);
            scaninput.nextLine();

            while (scaninput.hasNext())
            {
                String string = scaninput.nextLine().replaceAll(", ","-");
                String[] s =string.split(",");

                //I'm converting and saving the files individually because it helps is debugging
                int ISBN = Integer.parseInt(s[0]);
                String name = s[1].replaceAll("\"","");
                String author = s[2];
                int Year = Integer.parseInt(s[3].trim());
                String publisher = s[4];
                String genre = s[5];
                float price = Float.parseFloat(s[6].trim());
                int inStock = Integer.parseInt(s[7].trim());
                int pop = Integer.parseInt(s[8].trim());
                Book book = new Book(ISBN, name, author, Year, publisher, genre, price, inStock,pop);
                bookStore.insert(book);

            }
            bookStore.getSavedUsers();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("There was an error reading the file.");
            throw new RuntimeException(e);
        }
    }
}
