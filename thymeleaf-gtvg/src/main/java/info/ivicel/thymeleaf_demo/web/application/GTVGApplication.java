package info.ivicel.thymeleaf_demo.web.application;

import info.ivicel.thymeleaf_demo.web.controller.HomeController;
import info.ivicel.thymeleaf_demo.web.controller.IGTVGController;
import info.ivicel.thymeleaf_demo.web.controller.OrderDetailsController;
import info.ivicel.thymeleaf_demo.web.controller.OrderListController;
import info.ivicel.thymeleaf_demo.web.controller.ProductCommentsController;
import info.ivicel.thymeleaf_demo.web.controller.ProductListController;
import info.ivicel.thymeleaf_demo.web.controller.SubscribeController;
import info.ivicel.thymeleaf_demo.web.controller.UserProfileController;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class GTVGApplication {
    private TemplateEngine templateEngine;
    private Map<String, IGTVGController> controllersByURL;

    public GTVGApplication(ServletContext servletContext) {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(
                servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(10L * 3600);
        templateResolver.setCacheable(true);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

//        controllersByURL = new HashMap<>();
//        controllersByURL.put("/", new HomeController());
//        controllersByURL.put("/product/list", new ProductListController());
//        controllersByURL.put("/product/comments", new ProductCommentsController());
//        controllersByURL.put("/order/list", new OrderListController());
//        controllersByURL.put("/order/details", new OrderDetailsController());
//        controllersByURL.put("/subscribe", new SubscribeController());
//        controllersByURL.put("/userprofile", new UserProfileController());
    }


    public IGTVGController resolveControllerForRequest(HttpServletRequest request) {
        final String path = getRequestPath(request);
        return controllersByURL.get(path);
    }

    private String getRequestPath(final HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        final String contextPath = request.getContextPath();
        final int fragmentIndex = requestURI.indexOf(';');
        if (fragmentIndex != -1) {
            requestURI = requestURI.substring(0, fragmentIndex);
        }

        if (requestURI.startsWith(contextPath)) {
            return requestURI.substring(contextPath.length());
        }

        return requestURI;
    }

    public ITemplateEngine getTemplateEngine() {

        return templateEngine;
    }
}
