package PackageFoo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class downloadServlet extends HttpServlet{
    BookDao dao;

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        //String filename = "Bookinfo.xml";
       try{
           //response.setContentType("text/html");
           PrintWriter out = response.getWriter();

           //todo: file is xml... create xml and parse it out in a string?


           //String filePath = GetTmpPath();
           //response.setContentType("APPLICATION/OCTET-STREAM");
           //response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
           //fos.write("<?xml version=\"1.0\"?>");
           //byte[] bytes = getxmlfile().getBytes("UTF-8");
           out.write("Banana");
           //Writer br = new BufferedWriter(fos);
           //br.write(getxmlfile());

           //br.close();
           out.close();
       }catch (Exception E){
           E.printStackTrace();
       }
    }

    public String getxmlfile(){
        XStream xStream = new XStream(new DomDriver());
        String xml = "";
        ArrayList<Book> books = new ArrayList<Book>();
        try{
            dao = new BookDao();
            String webRootPath = "F:\\Java_folder_projects\\WebAppTest";
            String path = webRootPath+"\\web\\tmp\\";
            dao.ConnectionDB(path);
            dao.CreateBOOKTable();
            books = dao.getallBook();
            xStream.processAnnotations(Book.class);
            xml =  xStream.toXML(books);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return xml;
    }

    public String GetTmpPath(){
        String path = getServletContext().getRealPath("");
        String getWebPath = path.substring(0, 34);
        String patha = getWebPath + "\\web\\tmp\\";
        return patha;
    }

}


