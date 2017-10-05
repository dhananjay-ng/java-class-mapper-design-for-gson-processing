package student.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import student.data.Service;

public class URLHandler {

    public EndPoint getEndPoint(HttpServletRequest request) {
        EndPoint endPoint = new EndPoint();
        String type = getPathParameterClass(request);

        StringTokenizer st = new StringTokenizer(type, "|");
        String serviceClass = st.nextToken();

        String resourceClass = st.nextToken();

        if (type != null) {
            Service service = null;
            try {
                service = (Service) Class.forName(serviceClass).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

                e.printStackTrace();
            }
            endPoint.setService(service);
        }

        try {
            Class resource = Class.forName(resourceClass);
            endPoint.setResource(resource);
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

        String id = getPathParameterId(request);
        endPoint.setId(id);

      
        if (isQuery(request)) {

            String jsonqueryString = null;
            try {
                jsonqueryString = request.getReader().lines().collect(Collectors.joining());
            } catch (IOException e) {
                jsonqueryString = null;
                e.printStackTrace();
            }
            if (jsonqueryString != null) {
                String sqlqueryString = new QueryParser().parse(jsonqueryString);
                System.out.println(sqlqueryString);
                endPoint.setQuery(sqlqueryString);
            }
        }
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
        String queryResource;
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

    private boolean isQuery(HttpServletRequest request)
    {
        boolean val=false;
       StringTokenizer st=new StringTokenizer(request.getPathInfo(),"/");
       st.nextToken();
       if(st.hasMoreTokens()&& st.nextToken().equals("query"))
           val=true;
       return val;
        
        
    }
}
