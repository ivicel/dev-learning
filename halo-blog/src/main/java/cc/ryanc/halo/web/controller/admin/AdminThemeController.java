package cc.ryanc.halo.web.controller.admin;

import cc.ryanc.halo.model.dto.HaloConst;
import cc.ryanc.halo.model.dto.JsonResult;
import cc.ryanc.halo.model.dto.LogsRecord;
import cc.ryanc.halo.model.enums.BlogProperties;
import cc.ryanc.halo.service.LogService;
import cc.ryanc.halo.service.OptionsService;
import cc.ryanc.halo.utils.HaloUtils;
import cc.ryanc.halo.utils.LocaleMessageUtil;
import cc.ryanc.halo.web.controller.core.BaseController;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@Setter(onMethod = @__(@Autowired))
@RequestMapping("/admin/themes")
public class AdminThemeController extends BaseController {

    private OptionsService optionsService;
    private LogService logService;
    private LocaleMessageUtil localeMessageUtil;

    @GetMapping
    public String getThemes(Model model) {
        model.addAttribute("activeTheme", BaseController.THEME);
        model.addAttribute("themes", HaloConst.THEMES);

        return "admin/admin_theme";
    }

    @GetMapping("/install")
    public String installTheme() {
        return "admin/widget/_theme-install";
    }

    @PostMapping("/upload")
    @ResponseBody
    public JsonResult uploadTheme(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            if (!file.isEmpty()) {
                File basePath = ResourceUtils.getFile("classpath:");
                File themePath = new File(basePath, "templates/themes" + file.getOriginalFilename());
                file.transferTo(themePath);
                log.info("upload theme '{}' successfully, locate at {}", file.getOriginalFilename(),
                        themePath.getAbsolutePath());
                logService.save(LogsRecord.UPLOAD_THEME, file.getOriginalFilename(), request);
                ZipUtil.unzip(themePath, new File(basePath, "templates/themes/"));
                FileUtil.del(themePath);
                HaloConst.THEMES.clear();
                HaloConst.THEMES.addAll(HaloUtils.getThemes());
            } else {
                log.error("upload theme error, no file selected.");
                return JsonResult.fail(localeMessageUtil.getMessage("code.admin.theme.upload-no-file"));
            }
        } catch (IOException e) {
            log.error("upload theme error: {}", e.getMessage());
            return JsonResult.fail(localeMessageUtil.getMessage("code.admin.theme.upload-failed"));
        }
        return JsonResult.success(localeMessageUtil.getMessage("code.admin.theme.upload-success"));
    }

    @GetMapping("/set")
    @ResponseBody
    public JsonResult activeTheme(@RequestParam("siteTheme") String siteTheme, HttpServletRequest request) {
        try {
            optionsService.saveOption(BlogProperties.THEME.getProp(), siteTheme);
            BaseController.THEME = siteTheme;
            HaloConst.OPTIONS.clear();
            HaloConst.OPTIONS.putAll(optionsService.findAllOptions());

            // todo
            return JsonResult.success(localeMessageUtil.getMessage(
                    "code.admin.theme.change-success", new Object[]{siteTheme}));
        } catch (Exception e) {
            return JsonResult.fail("code.admin.theme.change-failed");
        }
    }
}
