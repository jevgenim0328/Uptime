package pack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


//Servlet for answer on product searching request. 

@SuppressWarnings("serial")
@WebServlet("/next-page")
public class NextPageServlet extends HttpServlet {
@Override
public void doGet(HttpServletRequest request,
                 HttpServletResponse response)
   throws ServletException, IOException {
	String rowsOfData = null;
//System.out.println(" Olen servlet-is next-page!");	
	rowsOfData = DataController.getLitleList(pack.Uptime.stringToInt(request.getParameter("startrow"))); 
	if(rowsOfData!=null) {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.print(rowsOfData);

			} 
	}
}
