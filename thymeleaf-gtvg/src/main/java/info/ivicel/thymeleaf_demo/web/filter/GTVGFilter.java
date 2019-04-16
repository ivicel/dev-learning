package info.ivicel.thymeleaf_demo.web.filter;

import info.ivicel.thymeleaf_demo.business.enities.User;
import info.ivicel.thymeleaf_demo.web.application.GTVGApplication;
import info.ivicel.thymeleaf_demo.web.controller.IGTVGController;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;

public class GTVGFilter implements Filter {
    private ServletContext servletContext;
    private GTVGApplication application;

    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
        application = new GTVGApplication(servletContext);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        addUserToSession((HttpServletRequest) request);
        if (!process((HttpServletRequest) request, (HttpServletResponse) response)) {
            chain.doFilter(request, response);
        }
    }

    private static void addUserToSession(final HttpServletRequest request) {
        request.getSession().setAttribute("user", new User("John", "Apricot", "Antarctica", null));
    }

    private boolean process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String uri = request.getRequestURI();
        try {
            if (uri.startsWith("/css") || uri.startsWith("/images") || uri.startsWith("/favicon")) {
                return false;
            }

            IGTVGController controller = application.resolveControllerForRequest(request);
            if (controller == null) {
                return false;
            }
            ITemplateEngine engine = application.getTemplateEngine();

            response.setContentType("text/html; charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            controller.process(request, response, servletContext, engine);

            return true;
        } catch (Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page Not Found");
            } catch (final IOException ignored) {
            }
            throw new ServletException(e);
        }
    }
}
