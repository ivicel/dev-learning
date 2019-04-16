package info.ivicel.thymeleaf_demo.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import info.ivicel.thymeleaf_demo.business.enities.User;


@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView process(HttpServletRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        Calendar cal = Calendar.getInstance();
//        WebContext webContext = new WebContext(request, response, servletContext,
//                request.getLocale());
//
//        webContext.setVariable("today", dateFormat.format(cal.getTime()));
//        webContext.setVariable("body", Calendar.getInstance());
//        engine.process("home", webContext, response.getWriter());
        HttpSession session = request.getSession();
        session.setAttribute("user", new User("John", "Apricot", "Antarctica", null));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        mv.addObject("today", dateFormat.format(cal.getTime()));
        mv.addObject("body", Calendar.getInstance());
        return mv;
    }
}
