import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sss extends HttpServlet {
    public Sss() {
        super();
        System.out.println("Sss:constructor");
    }

    public void init(ServletConfig sc) throws ServletException {
        super.init();
        System.out.println("Sss:init");
    }

    public void destroy() {
        super.destroy();
        System.out.println("Sss:destroy");
    }

    protected void doGet(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {
        System.out.println("Sss:doGet");

        String param = rq.getParameter("param");
        if (param.equals("redirect")) {
            rs.sendRedirect("/AS_7/GoGgg?param=redirect");
        } else if (param.equals("forward")) {
            RequestDispatcher rd = rq.getRequestDispatcher("/GoGgg?param=forward");
            rd.forward(rq, rs);
        } else {
            HttpClient hc = new HttpClient();
            String uri = "http://localhost:8000" + rq.getContextPath() + "/GoGgg?param=1";
            GetMethod gm = new GetMethod(uri);
            hc.executeMethod(gm);
            rs.setContentType("text/html");
            PrintWriter pw = rs.getWriter();
            pw.println(gm.getResponseBodyAsString());
            pw.flush();
        }
    }

    protected void service(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {
        System.out.println("Sss:service");

        if (rq.getMethod().equals("POST")) {

            String param = rq.getParameter("param");
            System.out.println("Sss:service param = " + param);
            switch(param){
                case "redirect":
                    rs.sendRedirect("/AS_7/GoGgg?param=redirect");
                    break;
                case "forward":
                    PrintWriter pw = rs.getWriter();
                    pw.println("Sss:service");
                    pw.close();

                    RequestDispatcher rd = rq.getRequestDispatcher("/GoGgg");
                    rd.forward(rq, rs);
                    break;
                default:
                    HttpClient hc = new HttpClient();
                    PostMethod pm = new PostMethod("http://localhost:8000"
                            + rq.getContextPath() + "/GoGgg");
                    NameValuePair[] params = {new NameValuePair("param", "1")};
                    pm.addParameters(params);
                    hc.executeMethod(pm);
                    if (pm.getStatusCode() == HttpStatus.SC_OK) {
                        InOutServlet inout = new InOutServlet(rs.getOutputStream(),
                                pm.getResponseBodyAsStream());
                        inout.perform();
                    } else
                        System.out.println("Sss:service:getStatusCode()="
                                + pm.getStatusCode());
                    break;
            }
        } else {
            super.service(rq, rs);
        }
    }
}