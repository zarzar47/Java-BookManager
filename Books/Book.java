package Books;

import java.awt.*;

public class Book implements Comparable<Book> {
    int popularity;
    int ISBN;
    String name;
    String author;
    int Year;
    String publisher;
    String genre;
    float price;
    int inStock;
    int[] colour = new int[3];

    // Constructor
    public Book(int ISBN, String name, String author, int Year, String publisher, String genre, float price, int inStock, int popularity)
    {
        this.ISBN = ISBN;
        this.name = name;
        this.author = author;
        this.Year = Year;
        this.publisher = publisher;
        this.genre = genre;
        this.price = price;
        this.inStock = inStock;
        this.popularity = popularity;
    }

    // Methods
    public float getPrice() {
        return price;
    }

    public int getInStock() {
        return inStock;
    }

    public int getISBN() {
        return ISBN;
    }

    public int getYear() {
        return Year;
    }

    public String getAuthor() {
        return author;
    }

    ;

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    // Method for BookContainerColour
    public Color setContainerColor()
    {
        return switch (this.genre)
        {
            case "Science Fiction" -> new Color(210, 170, 150);
            case "Thriller" -> new Color(70, 200, 90);
            case "Biography" -> new Color(90, 50, 150);
            case "Romance" -> new Color(125, 121, 123);
            case "Horror" -> new Color(160, 60, 221);
            case "Fantasy" -> new Color(77, 69, 66);
            case "Mystery" -> new Color(200, 50, 175);
            case "Young Adult" -> new Color(90, 180, 25);
            case "Historical Fiction" -> new Color(176, 225, 175);
            case "Self-Help" -> new Color(213, 123, 99);
            default -> new Color(50, 50, 50);
        };
    }
    // toString
    @Override
    public String toString() {
        return "ISBN: " + ISBN +
                ", Name: " + name +
                ", Author: " + author +
                ", Publisher: " + publisher +
                ", Genre: " + genre +
                ", Price: " + price +
                ", In stock: " + inStock +
                ", Popularity: " + popularity;
    }

    public String getDetailsOnly() {
        return ISBN +
                "%&" + name +
                "%&" + author +
                "%&" + publisher +
                "%&" + genre +
                "%&" + price +
                "%&" + inStock +
                "%&" + popularity;
    }

    @Override
    public int compareTo(Book o) {
        return this.name.compareTo(o.name);
    }

}
