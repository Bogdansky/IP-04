import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class Ggg extends HttpServlet {
    public Ggg() {
        super();
        System.out.println("Ggg:constructor");
    }

    public void init(ServletConfig sc) throws ServletException {
        super.init();
        System.out.println("Ggg:init");
    }

    public void destroy() {
        super.destroy();
        System.out.println("Ggg:destroy");
    }

    protected void doGet(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {
        System.out.println("Ggg:doGet");

        String param = rq.getParameter("param");
        if (param.equals("redirect")) {
            rs.sendRedirect("/AS_7/about.html");
        } else if (param.equals("forward")) {
            RequestDispatcher rd = rq.getRequestDispatcher("./about.html");
            rd.forward(rq, rs);
        } else {
            PrintWriter pw = rs.getWriter();
            pw.println(param);
            pw.flush();
        }
    }

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws IOException {
        System.out.println("Ggg:doPost");

        String param = rq.getParameter("param");
        PrintWriter pw = rs.getWriter();
        pw.println(param);
        pw.flush();

//        PrintWriter pw = rs.getWriter();
//        pw.println("Ggg:doPost");
//        pw.close();
    }
}