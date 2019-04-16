package info.ivicel.thymeleaf_demo.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;

public interface IGTVGController {

    void process(HttpServletRequest request, HttpServletResponse response,
            ServletContext servletContext, ITemplateEngine engine) throws Exception;
}
