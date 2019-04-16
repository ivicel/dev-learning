package info.ivicel.thymeleaf_demo.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

public class UserProfileController implements IGTVGController {

    public void process(HttpServletRequest request, HttpServletResponse response,
            ServletContext servletContext, ITemplateEngine engine) throws Exception {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        engine.process("userprofile", ctx, response.getWriter());
    }
}
