import java.io.*;
import javax.servlet.*;

class InOutServlet {
    private InputStream ist;
    private ServletOutputStream ost;

    InOutServlet(ServletOutputStream ost, InputStream ist){
        this.ist = ist;
        this.ost = ost;
    }

    void perform()throws IOException{
        int buf;
        while ((buf = this.ist.read()) > 0)
            this.ost.write(buf);
        this.ost.flush();
    }
}