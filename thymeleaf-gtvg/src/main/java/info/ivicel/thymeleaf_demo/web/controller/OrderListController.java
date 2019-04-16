package info.ivicel.thymeleaf_demo.web.controller;

import info.ivicel.thymeleaf_demo.business.enities.Order;
import info.ivicel.thymeleaf_demo.business.services.OrderService;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

@Controller
public class OrderListController {
    @RequestMapping("/order/list")
    public ModelAndView process() {
        final OrderService orderService = new OrderService();
        final List<Order> allOrders = orderService.findAll();
//        final WebContext ctx = new WebContext(request, response, servletContext,
//                request.getLocale());
//        ctx.setVariable("orders", allOrders);
//        engine.process("order/list", ctx, response.getWriter());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order/list");
        mv.addObject("orders", allOrders);
        return mv;
    }
}
