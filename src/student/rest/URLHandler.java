package student.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class URLHandler {

    public EndPoint getEndPoint(HttpServletRequest request)
    { EndPoint endPoint=new EndPoint();
        String URL=request.getPathInfo().toString();
        System.out.println(URL);
        String queryString=request.getQueryString();
        System.out.println(queryString);
    return endPoint;
    }
    
    public String getPathParameterId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String id;
        try {
            id = pathParts[2];
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
        return id;
    }
    
    public String getPathParameterClass(HttpServletRequest request) {
        String type;
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        try {
            type = pathParts[1];
            InputStream is = StudentRestService.class.getResourceAsStream("/config.properties");
            if (is != null) {
                Properties p = new Properties();
                p.load(is);
                return p.getProperty(type);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";

        } catch (IndexOutOfBoundsException e) {
            return "";
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return "";

    }

    
   
    
}
