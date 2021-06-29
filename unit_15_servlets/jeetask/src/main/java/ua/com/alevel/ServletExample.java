package ua.com.alevel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "example-servlet", urlPatterns = "/example")
public class ServletExample extends HttpServlet {
    private static final long serialVersionUID = -8948379822734246956L;

    private static final Logger log = LoggerFactory.getLogger(ServletExample.class);

    private Map<String, String> ipAddresses;

    public ServletExample() {
        ipAddresses = new ConcurrentHashMap<String, String>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter responseBody = resp.getWriter();

        resp.setContentType("text/html");
        responseBody.println("<h1 align=\"center\">Sample Servlet GET method processing</h1>");
        responseBody.println("<h3 align=\"center\">Request from: " + req.getRemoteHost() + "</h3>");

        String clientAddr = req.getRemoteAddr();
        String userAgent = req.getHeader("User-Agent");

        ipAddresses.put(clientAddr, userAgent);
        responseBody.println("Page visitors so far:");
        for (String key: ipAddresses.keySet()) {
            responseBody.println("<h3 align=\"center\">" + key + " " + ipAddresses.get(key) + "</h3>");

            if (clientAddr.equals(key) && userAgent.equals(ipAddresses.get(key))) {
                responseBody.println("<h3 align=\"center\"> <b>" + key + " " + ipAddresses.get(key) + "</b></h3>");
            }
        }

    }

    @Override
    public void destroy() {
        log.info(getServletName() + "destroyed");
    }

    @Override
    public void init() throws ServletException {
        log.info(getServletName() + "initialized");
    }
}
