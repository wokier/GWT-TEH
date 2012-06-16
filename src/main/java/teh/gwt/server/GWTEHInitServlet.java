package teh.gwt.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Servlet to declare in web.xml with a low loadonstartup level, in order to
 * activate gwt-teh on the server-side
 * 
 */
public class GWTEHInitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
	super.init();
	GWTEH.initServerSide();
    }
}
