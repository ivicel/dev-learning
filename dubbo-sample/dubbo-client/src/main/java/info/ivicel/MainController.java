package info.ivicel;

import info.ivicel.service.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(method = RequestMethod.GET)
public class MainController {

    // @Reference
    @Autowired
    private DubboService dubboService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView hello(@RequestParam String name) {
        String result = dubboService.sayHello(name);
        System.out.println(result);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("name", result);

        return mv;
    }
}
