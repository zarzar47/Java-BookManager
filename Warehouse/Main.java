package Warehouse;

import Books.FileReading;
import Warehouse.BookStore;

import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        Scanner input =new Scanner(System.in);
        new FileReading();
        BookStore bookStore = BookStore.getInstance();

        System.out.println("Do you want to seach for a book via Name, Author or Genre.");
        String answer = input.next();

        // bookStore.listBooks(); // Works!
        String temp;
        if (answer.equalsIgnoreCase("Name"))
        {
            System.out.println("Enter the name of the book");
            temp = input.nextLine();
            System.out.println(bookStore.getBookByName(temp));
        }
        else if (answer.equalsIgnoreCase("Author"))
        {

        }
        else if (answer.equalsIgnoreCase("Genre"))
        {

        }
    }
}