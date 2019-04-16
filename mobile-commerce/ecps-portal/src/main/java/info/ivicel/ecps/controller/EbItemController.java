package info.ivicel.ecps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class EbItemController {

    @RequestMapping("/index")
    public String toIndex(Model model) {
        System.out.println("EbItemController.toIndex");
        return "index";
    }

    @RequestMapping("/productDetail")
    public String productDetail(Long id, Model model) {
        return "productDetail";
    }
}
