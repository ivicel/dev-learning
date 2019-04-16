package info.ivicel.thymeleaf_demo.web.controller;

import info.ivicel.thymeleaf_demo.business.enities.Order;
import info.ivicel.thymeleaf_demo.business.services.OrderService;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import java.io.IOException;

@Controller
public class OrderDetailsController{
    @RequestMapping("/order/details")
    public ModelAndView process(@RequestParam("orderId") Integer orderId) {
//        final Integer orderId = Integer.valueOf(request.getParameter("orderId"));
        final OrderService orderService = new OrderService();
        Order order = orderService.findById(orderId);
//        final WebContext ctx = new WebContext(request, response, servletContext,
//                request.getLocale());
//        ctx.setVariable("order", order);
//        engine.process("/order/details", ctx, response.getWriter());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order/details");
        mv.addObject("order", order);
        return mv;
    }
}
