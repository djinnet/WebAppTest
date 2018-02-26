package PackageFoo.restws;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class HelpMeExceptionMapper implements ExceptionMapper<Exception>{

    @Override
    public Response toResponse(Exception E){
        E.printStackTrace();
        return Response
                .serverError()
                .entity(E.getMessage())
                .build();
    }

}
