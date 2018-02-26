package PackageFoo;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends DB {

    public BookDao(){}

    public boolean insertBook(Book e)
    throws SQLException
    {
        boolean rowInserted = false;
        try(Connection c = ConnectionDB()){

            String sql = "INSERT INTO BOOK (TITLE, AUTHOR, ISBN) " +
                    "VALUES (?, ?, ?);";

            PreparedStatement ps = c.prepareStatement(sql);
            //'"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
            ps.setString(1, e.getTitle());
            ps.setString(2, e.getAuthor());
            ps.setInt(3, e.getIsbn());

            rowInserted = ps.executeUpdate() > 0;

            return rowInserted;

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return rowInserted;

    }

    public int update(Book e)
    throws SQLException
    {
        int status = 0;

        try(Connection c = ConnectionDB()){

            String sql = "UPDATE BOOK SET TITLE = ?, AUTHOR = ?, ISBN = ?, CREATED_AT = ? WHERE BOOKID = ?;";
            PreparedStatement ps = c.prepareStatement(sql);
            //'"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
            ps.setString(1, e.getTitle());
            ps.setString(2, e.getAuthor());
            ps.setInt(3, e.getIsbn());
            ps.setString(4, e.getCreated_at());
            ps.setInt(5, e.getId());
            status = ps.executeUpdate();

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return status;
    }

    public int delete(int id)
    throws SQLException
    {
        int status = 0;

        try(Connection c = ConnectionDB()){

            String sql = "DELETE FROM BOOK  WHERE BOOKID = ?;";

            PreparedStatement ps = c.prepareStatement(sql);
            //'"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
            ps.setInt(1, id);

            status = ps.executeUpdate();

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return status;
    }

    public Book getBookById(int id)
    throws SQLException
    {
        Book b = null;

        try(Connection c = ConnectionDB()){

            String sql = "SELECT * FROM BOOK  WHERE BOOKID = ?;";

            PreparedStatement ps = c.prepareStatement(sql);
            //'"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int ide = rs.getInt("BOOKID");
                String  AUTHOR = rs.getString("AUTHOR");
                String  TITLE = rs.getString("TITLE");
                int ISBN  = rs.getInt("ISBN");
                String  CREATED_AT = rs.getString("CREATED_AT");

                b = new Book(ide, TITLE, AUTHOR, ISBN, CREATED_AT);
            }
            rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return b;
    }

    public ArrayList<Book> getallBook()
    throws SQLException
    {
        ArrayList<Book> list = new ArrayList<Book>();

        try(Connection c = ConnectionDB()){

            PreparedStatement ps = c.prepareStatement("select * from BOOK");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Book s= new Book();
                int id = rs.getInt("BOOKID");
                String  AUTHOR = rs.getString("AUTHOR");
                String  TITLE = rs.getString("TITLE");
                int ISBN  = rs.getInt("ISBN");
                String  CREATED_AT = rs.getString("CREATED_AT");


                s.setId(id);
                s.setTitle(TITLE);
                s.setAuthor(AUTHOR);
                s.setCreated_at(CREATED_AT);
                s.setIsbn(ISBN);
                list.add(s);
            }

        }catch(Exception e){e.printStackTrace();}

        return list;
    }

    public void InsertINTOBOOKTable(String title, String author, int isbn){



        try {

            Connection c = ConnectionDB();

            //todo: Change the values and use prepared statement!
            //https://software-security.sans.org/developer-how-to/fix-sql-injection-in-java-using-prepared-callable-statement
            String sql = "INSERT INTO BOOK (TITLE, AUTHOR, ISBN) " +
                    "VALUES (?, ?, ?);";

            PreparedStatement ps = c.prepareStatement(sql);
            //'"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, isbn);


            ps.executeUpdate();


            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public void SelectBookTable(PrintWriter out){

        Statement stmt = null;
        try(Connection c = ConnectionDB()) {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM BOOK;" );

            out.println("<button id=\"CreateBookBtn\" class=\"btn btn-block btn-lg btn-primary\" onclick=\"window.location='CreateBook';\">Create Book</button>");
            out.println("<TABLE>");
            out.println( "<tr>");
            out.println( "<th>" + "ID" + "</th>");
            out.println( "<th>" + "Author" + "</th>");
            out.println( "<th>" + "TITLE" + "</th>");
            out.println( "<th>" + "ISBN" + "</th>");
            out.println( "<th>" + "CREATED_AT" + "</th>");
            out.println( "<th>" + "Actions" + "</th>");
            out.println( "</tr>");
            out.println( "<tr>");
            while ( rs.next() ) {
                int id = rs.getInt("BOOKID");
                String  AUTHOR = rs.getString("AUTHOR");
                String  TITLE = rs.getString("TITLE");
                int ISBN  = rs.getInt("ISBN");
                String  CREATED_AT = rs.getString("CREATED_AT");

                //todo: fix the table
                out.println( "<td>" +  id +  "</td>");
                out.println( "<td>" +  AUTHOR +  "</td>");
                out.println( "<td>" +  TITLE +  "</td>");
                out.println(" <td>" +  ISBN +  "</td>");
                out.println( "<td>" +  CREATED_AT +  "</td>");

                out.println(" <td>" +  "<a href=\"/edit?id="+ id +"\">" + "Edit" + "</a>" );
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;");
                out.println("<a href=\"/delete?id="+ id +"\">"
                        + "Delete" + "</a>"
                        +  "</td>");

                out.println( "</tr>");
            }

            out.println("</TABLE>");
            rs.close();
            stmt.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }catch
         ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Operation done successfully");
    }

}
