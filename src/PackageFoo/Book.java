package PackageFoo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XStreamAlias("book")
@JsonbPropertyOrder({"id", "title","author", "isbn", "created_at"})
@XmlType(propOrder = {"id", "title","author", "isbn", "created_at", "updated_at"})
@XmlRootElement
public class Book implements Comparable<Book> {
    protected int id, isbn;
    protected String Title, Author;
    @XStreamAlias("date")
    protected String Created_at, Updated_at;


    public Book(){}

    Book(int id){
        this.id = id;
    }

    Book(int id, String title, String author, int isbn, String Create_At){
        this.id = id;
        this.Title = title;
        this.Author = author;
        this.isbn = isbn;
        this.Created_at = Create_At;
    }

    Book(int id, String title, String author, int isbn, String Created_at, String Updated_at){
        this.id = id;
        this.Title = title;
        this.Author = author;
        this.isbn = isbn;
        this.Created_at = Created_at;
        this.Updated_at = Updated_at;
    }

    Book(String title, String author, int isbn){
        this.Title = title;
        this.Author = author;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return Author;
    }

    public String getCreated_at() {
        return Created_at;
    }

    public String getTitle() {
        return Title;
    }

    public String getUpdated_at() {
        return Updated_at;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setCreated_at(String created_at) {
        Created_at = created_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setUpdated_at(String updated_at) {
        Updated_at = updated_at;
    }

    @Override
    public int compareTo(Book b){
        int idCommpare = ((Book) b).getId();
        return (int) (this.getId() - idCommpare);
    }
}
