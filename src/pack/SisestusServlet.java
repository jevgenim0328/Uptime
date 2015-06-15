package pack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//Servlet for answer on product searching request. 

@SuppressWarnings("serial")
@WebServlet("/data-controller")
public class SisestusServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rowsOfData = null;
//		System.out.println(" Olen servlet-is data-controller!");
		rowsOfData = DataController.DataRequest(request.getParameter("sisestus"));
		if (rowsOfData != null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(rowsOfData);
		}
	}
}
