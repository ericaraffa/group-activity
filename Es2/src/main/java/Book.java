import lombok.Data;
import java.util.ArrayList;

@Data
public class Book {
    private int idBook;
    private String title;
    private float price;
    private String category;
    private int publicationYear;
    private int numPages;
    private Publisher publisher;
    private ArrayList<Author> authors;
    private int quantity;

    public Book(int idBook,
                String title,
                float price,
                String category,
                int publicationYear,
                int numPages,
                Publisher publisher,
                ArrayList<Author> authors,
                int quantity){
        this.idBook = idBook;
        this.title = title;
        this.price = price;
        this.category = category;
        this.publicationYear = publicationYear;
        this.numPages = numPages;
        this.publisher = publisher;
        this.authors = authors;
        this.quantity = quantity;
    }

    public Book(){
        this(-1,
                null,
                (float)0.0,
                null,
                0,
                0,
                null,
                null,
                0);
    }

    public String getAuthorsOneLine(){
        String ret = "";
        for(int i = 0; i < this.authors.size(); i++){
            Author au = authors.get(i);
            ret = ret + au.getFirstName() + " " + au.getLastName();
            if(i != authors.size() - 1) ret = ret + ", ";
        }
        return ret;
    }

}
