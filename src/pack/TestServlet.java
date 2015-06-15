package pack;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

//Servlet answer to simple request. 

@SuppressWarnings("serial")
@WebServlet("/test1")
public class TestServlet extends HttpServlet {
@Override
public void doGet(HttpServletRequest request,
                 HttpServletResponse response)
   throws ServletException, IOException {
 response.setContentType("text/html");
 PrintWriter out = response.getWriter();
 out.println
   ("<!DOCTYPE html>\n" +
    "<html>\n" +
    "<head><title>Servlet-i vastus</title></head>\n" +
    "<body bgcolor=\"#fdf5e6\">\n" +
    "<h1>Proov</h1>\n" +
    "<p>Lihtne servlet proovimiseks.</p>\n" +
    "</body></html>");
}
}



