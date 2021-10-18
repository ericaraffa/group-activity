import lombok.Data;
import java.util.ArrayList;

@Data
public class Employee {
    private int idEmployee;
    private String companyName;

    public Employee(int idEmployee, String companyName){
        this.idEmployee = idEmployee;
        this.companyName = companyName;
    }

    public void readListBook(){
        try{
            ArrayList<Book> books = DbManager.getBooks();
            if(books.isEmpty()){
                System.out.println("The Book Shop is empty");
                return;
            }
            books.forEach((book)->{
                System.out.println("\nTitle: " + book.getTitle());
                System.out.println("Publisher: " + book.getPublisher().getName() + " at " + book.getPublisher().getLocation());
                System.out.println("Author(s): " + book.getAuthorsOneLine());
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertBook(Book b){
        try{
            ArrayList<Book> books = DbManager.getBooks();
            for(int i = 0; i < books.size(); i++){
                Book book = books.get(i);
                if(book.getIdBook() == b.getIdBook()){
                    System.out.println("Book '" + b.getTitle() + "'(" + b.getIdBook() + ") already exists");
                    return;
                }
            }
            DbManager.insertBook(b);
            System.out.println("Book '" + b.getTitle() + "'(" + b.getIdBook() + ") added");
        }catch (Exception e){ e.printStackTrace(); }
    }

    public void removeBook(int id){
        try{
            ArrayList<Book> books = DbManager.getBooks();
            for(int i = 0; i < books.size(); i++){
                Book book = books.get(i);
                if(book.getIdBook() == id){
                    DbManager.deleteBookById(id);
                    //DbManager.deleteByIdType(id, "book");
                    System.out.println("Book '" + book.getTitle() + "'(" + id + ") deleted");
                    return;
                }
            }
            System.out.println("Book id not found!");
        }catch (Exception e){ e.printStackTrace(); }
    }

    public void updateQuantity(int id, int quantity){
        try{
            ArrayList<Book> books = DbManager.getBooks();
            for(int i = 0; i < books.size(); i++){
                Book book = books.get(i);
                int prevQuantity = book.getQuantity();
                if(book.getIdBook() == id){
                    DbManager.updateQuantity(id, quantity);
                    System.out.println("Book '" + book.getTitle() + "'(" + id + ") quantity updated from: " + prevQuantity + " to: " + quantity);
                    return;
                }
            }
            System.out.println("Book id (" +  id + ") not found!");
        }catch (Exception e){ e.printStackTrace(); }
    }

    public void increaseQuantity(int id){
        try{
            ArrayList<Book> books = DbManager.getBooks();
            for(int i = 0; i < books.size(); i++){
                Book book = books.get(i);
                if(book.getIdBook() == id){
                    DbManager.updateQuantity(id, book.getQuantity() + 1);
                    System.out.println("Book '" + book.getTitle() + "'(" + id + ") quantity increased ");
                    return;
                }
            }
            System.out.println("Book id (" +  id + ") not found!");
        }catch (Exception e){ e.printStackTrace(); }
    }

    public void decreaseQuantity(int id){
        try{
            ArrayList<Book> books = DbManager.getBooks();
            for(int i = 0; i < books.size(); i++){
                Book book = books.get(i);
                if(book.getIdBook() == id){
                    DbManager.updateQuantity(id, book.getQuantity() - 1);
                    System.out.println("Book '" + book.getTitle() + "'(" + id + ") quantity decreased");
                    return;
                }
            }
            System.out.println("Book id (" +  id + ") not found!");
        }catch (Exception e){ e.printStackTrace(); }
    }

    public void readListAuthor(){
        try{
            ArrayList<Author> authors = DbManager.getAuthors();
            if(authors.isEmpty()){
                System.out.println("The author's list is empty");
                return;
            }
            authors.forEach((author)->{
                System.out.println("Name: " + author.getFirstName() + " " + author.getLastName() + "(" + author.getIdAuthor() + ")");
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertAuthor(Author au){
        try{
            ArrayList<Author> authors = DbManager.getAuthors();
            for(int i = 0; i < authors.size(); i++){
                Author author = authors.get(i);
                if(author.getIdAuthor() == au.getIdAuthor()){
                    System.out.println("Author " + au.getFirstName() + " " + au.getLastName() + "(" + au.getIdAuthor() + ") already exists");
                    return;
                }
            }
            DbManager.insertAuthor(au);
            System.out.println("Author " + au.getFirstName() + " " + au.getLastName() + "(" + au.getIdAuthor() + ") added");
        }catch (Exception e){ e.printStackTrace(); }
    }

    public void removeAuthor(int id){
        try{
            ArrayList<Author> authors = DbManager.getAuthors();
            for(int i = 0; i < authors.size(); i++){
                Author author = authors.get(i);
                if(author.getIdAuthor() == id){
                    System.out.println("Now removing related books on db");
                    removeBookCascade(id, "Author");
                    DbManager.deleteAuthorById(id);
                    //DbManager.deleteByIdType(id, "author");
                    System.out.println("Author " + author.getFirstName() + " " + author.getLastName() + "(" + author.getIdAuthor() + ") deleted");
                    return;
                }
            }
            System.out.println("Author id not found!");
        }catch (Exception e){ e.printStackTrace(); }
    }

    public void readListPublisher(){
        try{
            ArrayList<Publisher> publishers = DbManager.getPublishers();
            if(publishers.isEmpty()){
                System.out.println("The publisher's list is empty");
                return;
            }
            publishers.forEach((publisher)->{
                System.out.println("Name: " + publisher.getName() + "(" + publisher.getIdPublisher() + ")");
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertPublisher(Publisher pb){
        try{
            ArrayList<Publisher> publishers = DbManager.getPublishers();
            for(int i = 0; i < publishers.size(); i++){
                Publisher publisher = publishers.get(i);
                if(publisher.getIdPublisher() == pb.getIdPublisher()){
                    System.out.println("Publisher " + pb.getName() + "(" + pb.getIdPublisher() + ") already exists");
                    return;
                }
            }
            DbManager.insertPublisher(pb);
            System.out.println("Publisher " + pb.getName() + "(" + pb.getIdPublisher() + ") added");
        }catch (Exception e){ e.printStackTrace(); }
    }

    public void removePublisher(int id){
        try{
            ArrayList<Publisher> publishers = DbManager.getPublishers();
            for(int i = 0; i < publishers.size(); i++){
                Publisher publisher = publishers.get(i);
                if(publisher.getIdPublisher() == id){
                    System.out.println("Now removing related books on db");
                    removeBookCascade(id, "Publisher");
                    DbManager.deletePublisherById(id);
                    //DbManager.deleteByIdType(id, "publisher");
                    System.out.println("Publisher " + publisher.getName() + " deleted");
                    return;
                }
            }
            System.out.println("Publisher id not found!");
        }catch (Exception e){ e.printStackTrace(); }
    }

    private static void removeBookCascade(int id, String type){
        try {
            ArrayList<Book> books = DbManager.getBooks();
            books.forEach((book)->{
                switch (type){
                    case "Author":
                        book.getAuthors().forEach((author)->{
                            if(author.getIdAuthor() == id){
                                try{
                                    DbManager.deleteBookById(book.getIdBook());
                                    //DbManager.deleteByIdType(book.getIdBook(), "book");
                                }catch (Exception e){
                                    e.printStackTrace();
                                    return;
                                }
                                System.out.println("\tBook '" + book.getTitle() + "'(" + id + ") deleted");
                            }
                        });
                        break;
                    case "Publisher":
                        if(book.getPublisher().getIdPublisher() == id){
                            try{
                                DbManager.deleteBookById(book.getIdBook());
                                //DbManager.deleteByIdType(book.getIdBook(), "book");
                            }catch (Exception e){
                                e.printStackTrace();
                                return;
                            }
                            System.out.println("\tBook '" + book.getTitle() + "'(" + id + ") deleted");
                        }
                        break;
                    default:
                        System.out.println("Error type parameter");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
