import java.lang.reflect.Array;
import java.util.ArrayList;

/*
* Actions:
* 1) Read the list of the books                     ok
* 2) Insert new book                                ok
* 3) Remove a book                                  ok
* 4) Update the quantity of a book                  ok
* 5) Increase/Decrease the quantity of a book       ok
* 6) Read the list of the authors                   ok
* 7) Insert an author                               ok
* 8) Delete an author                               ok
* 9) Read the list of the publisher                 ok
* 10) Insert a publisher                            ok
* 11) Delete a publisher                            ok
*/

public class Main {
    public static void main(String[] args){
        Employee em = new Employee(1, "Unipi");

        Author au = new Author(1, "Donald", "Duck", "I'm a duck");
        Author au1 = new Author(2, "Mickey", "Mouse", "I'm a mouse");
        Author au2 = new Author(3, "Minnie", "Mouse", "I'm a mouse");
        Author au3 = new Author(4, "Clarabelle", "Cow", "I'm a cow");
        Author au4 = new Author(5, "Daisy", "Duck", "I'm a duck");

        Publisher pb = new Publisher(1, "Pearson", "Mouseton");
        Publisher pb1 = new Publisher(2, "Mondadori","Duckburg");
        Publisher pb2 = new Publisher(3, "De Agostini", "Brutopia");
        Publisher pb3 = new Publisher(4, "Hachette Livre", "Spoonerville");

        ArrayList<Author> auts = new ArrayList<Author>();
        auts.add(au);
        Book b = new Book(1,
                "Mickey Mouse & Friends",
                (float)2.5,
                "Fumetto",
                1980,
                50,
                pb,
                auts,
                20);

        ArrayList<Author> auts1 = new ArrayList<Author>();
        auts1.add(au1);
        auts1.add(au2);
        Book b1 = new Book(2,
                "Donald Duck & Friends",
                (float)1.5,
                "Comic book",
                1965,
                20,
                pb3,
                auts1,
                10);

        ArrayList<Author> auts2 = new ArrayList<Author>();
        auts2.add(au);
        auts2.add(au3);
        Book b2 = new Book(3,
                "Duck Avenger",
                (float)4.0,
                "Comic book",
                1989,
                20,
                pb,
                auts2,
                10);

        //7
        System.out.println("Adding some authors:");
        em.insertAuthor(au);
        em.insertAuthor(au1);
        em.insertAuthor(au2);
        em.insertAuthor(au3);
        em.insertAuthor(au4);

        System.out.println("\nTrying to insert an existing id of author:");
        em.insertAuthor(au);
        em.insertAuthor(au3);

        //6
        System.out.println("\nReading list of authors");
        em.readListAuthor();

        //9
        System.out.println("\nAdding some publishers:");
        em.insertPublisher(pb);
        em.insertPublisher(pb1);
        em.insertPublisher(pb2);
        em.insertPublisher(pb3);

        System.out.println("\nTrying to insert an existing id of publisher:");
        em.insertPublisher(pb);
        em.insertPublisher(pb3);

        //10
        System.out.println("\nReading list of publishers");
        em.readListPublisher();

        //2
        System.out.println("\nAdding some books:");
        em.insertBook(b);
        em.insertBook(b1);
        em.insertBook(b2);

        System.out.println("\nTrying to insert an existing id of book:");
        em.insertBook(b);

        //1
        System.out.println("\nReading list of books");
        em.readListBook();

        //4
        System.out.println("\nTrying to update the quantity of a book:");
        em.updateQuantity(3, 50);

        System.out.println("\nTrying to update the quantity of a non existing book:");
        em.updateQuantity(10, 20);

        //5
        System.out.println("\nTrying increase & decrease the quantity of a book:");
        em.increaseQuantity(1);
        em.decreaseQuantity(2);

        System.out.println("\nTrying to increase & decrease the quantity of a non existing book:");
        em.increaseQuantity(10);
        em.decreaseQuantity(10);

        //3
        System.out.println("\nTrying to remove a book:");
        em.removeBook(1);

        //8
        System.out.println("\nTrying to remove an author:");
        em.removeAuthor(2);
        em.removeAuthor(4);

        //11
        System.out.println("\nTrying to remove a publisher");
        em.removePublisher(1);
        em.removePublisher(3);

        System.out.println("\nRemoving everything:");
        em.removeAuthor(1);
        em.removeAuthor(2);
        em.removeAuthor(3);
        em.removeAuthor(4);
        em.removeAuthor(5);

        em.removePublisher(1);
        em.removePublisher(2);
        em.removePublisher(3);
        em.removePublisher(4);

        em.removeBook(1);
        em.removeBook(2);
        em.removeBook(3);

    }
}
