import lombok.Data;

@Data
public class Author {
    private int idAuthor;
    private String firstName;
    private String lastName;
    private String biography;
    public Author(int idAuthor, String firstName, String lastName, String biography){
        this.idAuthor = idAuthor;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
    }

    public Author(){
        this(-1, null, null, null);
    }
}
