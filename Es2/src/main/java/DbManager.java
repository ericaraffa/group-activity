import lombok.NonNull;

import java.sql.*;
import java.util.ArrayList;

public class DbManager {
    public static Connection con = null;
    private static final String path = "jdbc:mysql://localhost:3306/bookshop";
    private static final String user = "root";
    private static final String password = "root";

    private static void connect()throws SQLException{
        con = DriverManager.getConnection(path, user, password);
    }

    public static ArrayList<Book> getBooks()throws SQLException{
        ArrayList <Book> books = new ArrayList<Book>();
        try {
            if(con == null || con.isClosed()) connect();

            String query = "SELECT * FROM book ORDER BY title";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setIdBook((rs.getInt("idbook")));
                book.setTitle(rs.getString("title"));
                book.setAuthors(DbManager.getAuthorsByIdBook(rs.getInt("idbook")));
                book.setPublisher(DbManager.getPublisherById(rs.getInt("_idpublisher")));
                book.setQuantity(rs.getInt("quantity"));
                books.add(book);
            }
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
            if(con != null) con.close();
        }
        con.close();
        return books;
    }

    private static ArrayList<Author> getAuthorsByIdBook(int id)throws SQLException{
        ArrayList <Author> authors = new ArrayList<Author>();
        String query = "SELECT * FROM book_has_author INNER JOIN author ON (_idauthor = idauthor) WHERE _idbook = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            Author au = getAuthorById(rs.getInt("idauthor"));
            authors.add(au);
        }
        rs.close();
        return authors;
    }

    private static Author getAuthorById(int id)throws SQLException{
        Author au = new Author();
        String query = "SELECT * FROM author WHERE idauthor = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            au.setIdAuthor(id);
            au.setLastName(rs.getString("lastname"));
            au.setFirstName(rs.getString("firstname"));
            au.setBiography(rs.getString("biography"));
        }
        rs.close();
        return au;
    }

    private static Publisher getPublisherById(int id)throws SQLException{
        Publisher pb = new Publisher();
        String query = "SELECT * FROM publisher WHERE idpublisher = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            pb.setIdPublisher(id);
            pb.setName(rs.getString("name"));
            pb.setLocation(rs.getString("location"));
        }
        rs.close();
        return pb;
    }

    public static void updateQuantity(int id, int quantity)throws  SQLException{
        try{
        if(con == null || con.isClosed()) connect();
        String query = "UPDATE book SET quantity = ? WHERE idbook = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, quantity);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            if(con != null) con.close();
        }
    }

    public static void insertBook(Book b)throws SQLException{
        try{
            if(con == null || con.isClosed()) connect();
            String query = "INSERT INTO book(idbook, title, price, category,publicationyear, numpages, _idpublisher, quantity) VALUE(?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, b.getIdBook());
            stmt.setString(2, b.getTitle());
            stmt.setFloat(3,b.getPrice());
            stmt.setString(4, b.getCategory());
            stmt.setInt(5, b.getPublicationYear());
            stmt.setInt(6, b.getNumPages());
            stmt.setInt(7, b.getPublisher().getIdPublisher());
            stmt.setInt(8, b.getQuantity());
            stmt.executeUpdate();
            for(int i = 0; i < b.getAuthors().size(); i++){
                Author au = b.getAuthors().get(i);
                query = "INSERT INTO book_has_author VALUES(?,?)";
                stmt = con.prepareStatement(query);
                stmt.setInt(1, b.getIdBook());
                stmt.setInt(2, au.getIdAuthor());
                stmt.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
            if(con != null) con.close();
        }
    }

    public static void deleteBookById(int id)throws SQLException{
        try {
            if (con == null || con.isClosed()) connect();
            String query = "DELETE FROM book_has_author WHERE _idbook = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            query = "DELETE FROM book WHERE idbook = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(Exception e ){
            e.printStackTrace();
            if(con != null) con.close();
        }
    }

    public static ArrayList<Author> getAuthors()throws SQLException{
        ArrayList<Author> authors = new ArrayList<Author>();
        try{
            if(con == null || con.isClosed()) connect();
            String query = "SELECT * FROM author";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Author au = new Author();
                au.setIdAuthor(rs.getInt("idauthor"));
                au.setFirstName(rs.getString("firstname"));
                au.setLastName(rs.getString("lastname"));
                authors.add(au);
            }
        }catch (Exception e){
            e.printStackTrace();
            if(con != null) con.close();
        }
        return authors;
    }

    public static ArrayList<Publisher> getPublishers()throws SQLException{
        ArrayList<Publisher> publishers = new ArrayList<Publisher>();
        try{
            if(con == null || con.isClosed()) connect();
            String query = "SELECT * FROM publisher";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Publisher pb = new Publisher();
                pb.setIdPublisher(rs.getInt("idpublisher"));
                pb.setName(rs.getString("name"));
                publishers.add(pb);
            }
        }catch (Exception e){
            e.printStackTrace();
            if(con != null) con.close();
        }
        return publishers;
    }

    public static void insertAuthor(Author au)throws SQLException{
        try{
            if(con == null || con.isClosed()) connect();
            String query = "INSERT INTO author(idauthor, firstname, lastname, biography) VALUE(?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, au.getIdAuthor());
            stmt.setString(2, au.getFirstName());
            stmt.setString(3, au.getLastName());
            stmt.setString(4, au.getBiography());
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            if(con != null) con.close();
        }
    }

    public static void insertPublisher(Publisher pb)throws SQLException{
        try{
            if(con == null || con.isClosed()) connect();
            String query = "INSERT INTO publisher(idpublisher, name, location) VALUE(?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, pb.getIdPublisher());
            stmt.setString(2, pb.getName());
            stmt.setString(3, pb.getLocation());
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            if(con != null) con.close();
        }
    }

    public static void deleteAuthorById(int id)throws  SQLException{
        try {
            if (con == null || con.isClosed()) connect();
            String query = "DELETE FROM author WHERE idauthor = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(Exception e ){
            e.printStackTrace();
            if(con != null) con.close();
        }
    }

    public static void deletePublisherById(int id)throws SQLException{
        try {
            if (con == null || con.isClosed()) connect();
            String query = "DELETE FROM publisher WHERE idpublisher = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(Exception e ){
            e.printStackTrace();
            if(con != null) con.close();
        }
    }

}
