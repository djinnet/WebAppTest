package PackageFoo;

import com.sun.net.httpserver.Authenticator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class ControllerServlet extends HttpServlet {

    private BookDao dao;

    public ControllerServlet(){
        super();
        dao = new BookDao();
    }

    //post
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
        //<link href="${pageContext.request.contextPath}/css/flat-ui.css" rel="stylesheet" >

        //try with resource statement

    }

    private void StachTest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException
    {

            //out.println("<link href=\"${pageContext.request.contextPath}/css/flat-ui.css\" rel=\"stylesheet\" >");

            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String isbn = request.getParameter("ISBN");

            String path = GetTmpPath();

            //System.out.println(path);

            dao.ConnectionDB(path);
            Book newbook = new Book(title, author, Integer.parseInt(isbn));
            dao.insertBook(newbook);


            //out.println("<a href='/'>" + "Back"+ "</a>");
            response.sendRedirect("/");



    }

    private void EditTest(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        String path = GetTmpPath();
        dao.ConnectionDB(path);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/BookForm.jsp");
        //request.setAttribute("book", ExistingBook);
        dispatcher.forward(request, response);
    }

    public String GetTmpPath(){
        String path = getServletContext().getRealPath("");
        String getWebPath = path.substring(0, 34);
        String patha = getWebPath + "\\web\\tmp\\";
        return patha;
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        String path = GetTmpPath();
        dao.ConnectionDB(path);
        dao.delete(id);
        response.sendRedirect("/");
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        try {
            String path = GetTmpPath();
            dao.ConnectionDB(path);
            request.setAttribute("books", dao.getallBook());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/index.jsp");
            dispatcher.forward(request, response);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        try {
            String path = GetTmpPath();
            dao.ConnectionDB(path);

            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            int isbn = Integer.parseInt(request.getParameter("isbn"));
            String  createAT = request.getParameter("createAT");
            Book b = new Book(id, title, author, isbn, createAT);
            System.out.println(b.getTitle() + " " + b.getAuthor() + " " + b.getCreated_at() + " "
            + b.getIsbn() + " " + b.getId());
            dao.update(b);
            response.sendRedirect("/");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String action = request.getServletPath();
        System.out.println(request.getServletPath());
        //<link href="${pageContext.request.contextPath}/css/flat-ui.css" rel="stylesheet" >

        try{
            switch (action){
                case "/test":
                    StachTest(request, response);
                    break;
                case "/delete":
                    deleteBook(request, response);
                    break;
                case "/edit":
                    EditTest(request, response);
                    break;
                case "/update":
                    updateBook(request, response);
                    break;
                default:
                case "/views/index.jsp":
                    listBook(request, response);
                        break;

            }
        }catch (SQLException E){
            throw new ServletException(E);
        }
        //try with resource statement

    }


}

