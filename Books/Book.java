package Books;

public class Book implements Comparable<Book>{
    int popularity;
    int ISBN;
    String name;
    String author;
    int Year;
    String publisher;
    String genre;
    float price;
    int inStock;

    // Constructor
    public Book(int ISBN, String name, String author, int Year, String publisher, String genre,float price, int inStock, int popularity)
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
    };

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    // toString
    @Override
    public String toString() {
        return "ISBN: "+ISBN+
                ", Name: "+name+
                ", Author: "+author+
                ", Publisher: "+publisher+
                ", Genre: "+genre+
                ", Price: "+price+
                ", In stock: "+inStock+
                ", Popularity: "+popularity;
    }

    @Override
    public int compareTo(Book o) {
        return this.name.compareTo(o.name);
    }
}
