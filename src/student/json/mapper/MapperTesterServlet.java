package student.json.mapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet("/mapperTester")
public class MapperTesterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MapperTesterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			String data1=request.getReader().lines().collect(Collectors.joining());
			out.println(data1);
			String requestData = request.getReader().lines().collect(Collectors.joining());
			Gson gson = new Gson();
			Type type = new TypeToken<Map<String, String>>() {}.getType();
			Map<String, String> data = new Gson().fromJson(data1, type);
			out.println(data);

		}
	}

	
}
