package PackageFoo.soapws;

import PackageFoo.Book;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.util.List;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface SOAPHelloWorld {

    @WebMethod String getHelloWorldAsString(String name);

    @WebMethod String getXMLMessage();
}
