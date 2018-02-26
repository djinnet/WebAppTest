package PackageFoo.soapws;

import PackageFoo.Book;
import PackageFoo.BookDao;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.jws.WebService;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.soap.SOAPPart;
import javax.xml.xpath.XPath;

@WebService(endpointInterface = "PackageFoo.soapws.SOAPHelloWorld")
public class HelloWorldImpl implements SOAPHelloWorld{

    private BookDao dao;

    @Override
    public String getHelloWorldAsString(String name){
        return "hello world " + name;
    }


    @Override
    @Produces({MediaType.APPLICATION_XML})
    public String getXMLMessage() {
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

}
