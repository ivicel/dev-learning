package cc.ryanc.halo.web.controller.core;

public abstract class BaseController {

    public static String THEME = "anatole";

    // todo: template prefix
    public String render(String pageName) {
        return String.format("%s/%s", THEME, pageName);
    }

    // todo: 404 跳转
    public String renderNotFound() {
        // return "redirect:/404";
        return "404";
    }
}
