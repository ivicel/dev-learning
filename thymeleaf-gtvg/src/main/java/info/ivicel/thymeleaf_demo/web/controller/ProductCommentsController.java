package info.ivicel.thymeleaf_demo.web.controller;

import info.ivicel.thymeleaf_demo.business.enities.Product;
import info.ivicel.thymeleaf_demo.business.services.ProductService;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

public class ProductCommentsController implements IGTVGController {

    public void process(HttpServletRequest request, HttpServletResponse response,
            ServletContext servletContext, ITemplateEngine engine) throws Exception {
        final Integer prodId = Integer.valueOf(request.getParameter("prodId"));
        final ProductService productService = new ProductService();
        final Product product = productService.findById(prodId);

        final WebContext ctx = new WebContext(request, response, servletContext,
                request.getLocale());
        ctx.setVariable("prod", product);
        engine.process("product/comments", ctx, response.getWriter());
    }
}
