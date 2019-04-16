package thymeleafexamples.stsm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import thymeleafexamples.stsm.business.entities.Feature;
import thymeleafexamples.stsm.business.entities.SeedStarter;
import thymeleafexamples.stsm.business.entities.Type;
import thymeleafexamples.stsm.business.entities.Variety;
import thymeleafexamples.stsm.business.services.SeedStarterService;
import thymeleafexamples.stsm.business.services.VarietyService;

@Controller
public class SeedStarterMngController {
    @Autowired
    private VarietyService varietyService;

    @Autowired
    private SeedStarterService seedStarterService;

    @ModelAttribute("allTypes")
    public List<Type> populateTypes() {
        return Arrays.asList(Type.ALL);
    }

    @ModelAttribute("allFeatures")
    public List<Feature> populateFeatures() {
        return Arrays.asList(Feature.ALL);
    }

    @ModelAttribute("allVarieties")
    public List<Variety> populateVarieties() {
        return varietyService.findAll();
    }

    @ModelAttribute("allSeedStarters")
    public List<SeedStarter> populatesSeedStarters() {
        return seedStarterService.findAll();
    }

    @RequestMapping({"/", "/seedstartermng"})
    public String showSeedStarters(final SeedStarter seedStarter) {
        System.out.println("SeedStarterMngController.showSeedStarters");
        seedStarter.setDatePlanted(Calendar.getInstance().getTime());
        return "seedstartermng";
    }

    @RequestMapping(value = "/seedstartermng", params = {"save"})
    public String saveSeedStarter(final SeedStarter seedStarter, final BindingResult bindingResult,
                                  final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "seedstartermng";
        }
        seedStarterService.add(seedStarter);
        model.clear();
        return "redirect:/seedstartermng";
    }

    @RequestMapping(value = "/seedstartermng", params = {"removeRow"})
    public String removeRow(final SeedStarter seedStarter, final BindingResult bindingResult,
                            HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        seedStarter.getRows().remove(rowId);
        return "seedstartermng";
    }
}
