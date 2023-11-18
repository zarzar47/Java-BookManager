public class Book implements Comparable<Book>{
    int ISBN;
    String name;
    String author;
    int Year;
    String publisher;
    String genre;
    float price;
    int inStock;

    public Book(int ISBN, String name, String author, int Year, String publisher, String genre,float price, int inStock){
        this.ISBN = ISBN;
        this.name = name;
        this.author = author;
        this.Year = Year;
        this.publisher = publisher;
        this.genre = genre;
        this.price = price;
        this.inStock = inStock;
    }

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

    @Override
    public String toString() {
        return "ISBN: "+ISBN+", Name: "+name+", Author: "+author+", Publisher: "+publisher+", Genre: "+genre+", Price: "+price+", In stock: "+inStock;
    }

    @Override
    public int compareTo(Book o) {
        return this.name.compareTo(o.name);
    }
}
