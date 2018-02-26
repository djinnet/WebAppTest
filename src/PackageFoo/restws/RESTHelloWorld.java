package PackageFoo.restws;

import PackageFoo.Book;
import PackageFoo.BookDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Path("/hello")
public class RESTHelloWorld {

    BookDao dao;
    String webRootPath = "F:\\Java_folder_projects\\WebAppTest";

    @Path("/xml")
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Book> getXMLMessage() {
        ArrayList<Book> books = new ArrayList<Book>();
        try{
            dao = new BookDao();
            String path = webRootPath+"\\web\\tmp\\";
            dao.ConnectionDB(path);
            dao.CreateBOOKTable();
            books = dao.getallBook();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }

    @Path("/json")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Book> getJsonMessage() {
        ArrayList<Book> books = new ArrayList<Book>();
        try{
            dao = new BookDao();
            String path = webRootPath+"\\web\\tmp\\";
            dao.ConnectionDB(path);
            dao.CreateBOOKTable();
            books = dao.getallBook();
            //books.sort(Book::compareTo);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }


    @GET
    @Produces({MediaType.TEXT_HTML})
    public String getHTMLMessage() {
        return "<a href=\"/rest/hello/json\">json</a>" +
                "<br> " +
                "<a href=\"/rest/hello/xml\">xml</a>" +
                "<br>" +
                "<a href=\"/soap\">soap</a>";
    }




}
