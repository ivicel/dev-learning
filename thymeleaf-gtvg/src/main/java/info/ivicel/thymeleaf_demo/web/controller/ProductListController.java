package info.ivicel.thymeleaf_demo.web.controller;

import info.ivicel.thymeleaf_demo.business.enities.Product;
import info.ivicel.thymeleaf_demo.business.enities.repositories.ProductRepository;
import info.ivicel.thymeleaf_demo.business.services.ProductService;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

@Controller
public class ProductListController {
    @RequestMapping("/product/list")
    public ModelAndView process() throws Exception {
        ProductService service = new ProductService();
        List<Product> products = service.findAll();

//        final WebContext ctx = new WebContext(request, response, servletContext,
//                request.getLocale());
//        ctx.setVariable("prods", products);
//        engine.process("product/list", ctx, response.getWriter());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("product/list");
        mv.addObject("prods", products);
        return mv;
    }
}
