package kr.or.ddit.servlet03;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/getBlood.do", loadOnStartup = 1)
public class BloodServlet extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		
		Map<String, String[]> bloodMap = new LinkedHashMap<String, String[]>();
		
		bloodMap.put("B001", new String[] {"O형", "blood/o.jsp"});
		bloodMap.put("B002", new String[] {"A형", "blood/a.jsp"});
		bloodMap.put("B003", new String[] {"AB형", "blood/ab.jsp"});
		bloodMap.put("B004", new String[] {"B형", "blood/b.jsp"});
		
		getServletContext().setAttribute("bloodMap", bloodMap);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/bloodType.jsp").forward(req, resp);
	}
	
}
