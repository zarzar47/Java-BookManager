package Books;

import DataStructures.DynamicArray;

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

    DynamicArray<String> reviewList;

    // Constructor
    public Book(int ISBN, String name, String author, int Year, String publisher, String genre, float price, int inStock, int popularity) {
        this.ISBN = ISBN;
        this.name = name;
        this.author = author;
        this.Year = Year;
        this.publisher = publisher;
        this.genre = genre;
        this.price = price;
        this.inStock = inStock;
        this.popularity = popularity;
        setReviews();
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

    public int getPopularity() {
        return popularity;
    }

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
    public Color setContainerColor() {
        return switch (this.genre) {
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

    public void setReviews() {
        String[] reviews = {
                "An enthralling exploration of human resilience and the consequences of unchecked power. A must-read for dystopian fiction enthusiasts."
                ,
                "A masterfully crafted mystery that keeps you guessing until the last page. The plot twists are both unexpected and satisfying."
                ,
                "This book seamlessly blends science and philosophy, challenging readers to ponder the ethical implications of cutting-edge technologies."
                ,
                "A compelling narrative that delves into the complexities of human relationships, offering profound insights into the human condition."
                ,
                "A classic tale of adventure and self-discovery, this book resonates with timeless themes and vivid character development."
                ,
                "The author's eloquent prose paints a vivid picture of a bygone era, immersing readers in the rich historical tapestry of the narrative."
                ,
                "A thought-provoking exploration of artificial intelligence and its potential impact on society. Raises crucial questions about our technological future."
                ,
                "This book is a treasure trove of practical advice and strategies for personal development, offering actionable insights for real-life application."
                ,
                "A riveting journey through the realms of fantasy, with a well-crafted world and memorable characters that captivate the imagination."
                ,
                "An insightful analysis of economic trends and their sociopolitical ramifications, written with clarity and relevance for a broad audience."
                ,
                "A poignant coming-of-age story that navigates the challenges of adolescence with sensitivity and an authentic portrayal of human emotions."
                ,
                "This book is a beacon for innovation enthusiasts, providing a roadmap for navigating the ever-evolving landscape of technological advancements."
                ,
                "A captivating exploration of cultural diversity and the impact of societal norms on individual identity. A thought-provoking narrative."
                ,
                "A riveting thriller that keeps readers on the edge of their seats, skillfully combining suspense and plot twists for a gripping experience."
                ,
                "A groundbreaking work that challenges conventional wisdom, offering fresh perspectives on familiar subjects and reshaping our intellectual landscape."
                ,
                "This book seamlessly integrates theoretical concepts with practical applications, making it an indispensable resource for students and professionals alike."
                ,
                "A heartwarming tale that celebrates the triumph of the human spirit over adversity. A feel-good story with universal appeal."
                ,
                "An ambitious exploration of environmental issues, blending scientific insights with a compelling narrative to foster awareness and action."
                ,
                "A meticulously researched historical account that sheds light on lesser-known events, enriching our understanding of the past."
                ,
                "A visionary work that anticipates future societal shifts, providing a roadmap for adapting to the challenges and opportunities ahead.",
        };
        reviewList = new DynamicArray<>();
        int numOfReview = (int)(Math.random()*3 + 1);
        for (int i = 0; i < numOfReview; i++) {
            reviewList.Insert(reviews[(int)(Math.random()*reviews.length)]);
        }
    }

    public DynamicArray<String> getReviewList() {
        return reviewList;
    }
}
