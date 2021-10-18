import lombok.Data;

@Data
public class Publisher {
    private int idPublisher;
    private String name;
    private String location;

    public Publisher(int idPublisher, String name, String location){
        this.idPublisher = idPublisher;
        this.name = name;
        this.location = location;
    }

    public Publisher(){
        this(-1, null, null);
    }
}
