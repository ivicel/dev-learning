package cc.ryanc.halo.web.controller.admin;

import static org.springframework.data.domain.Sort.Direction.DESC;

import cc.ryanc.halo.model.domain.Attachment;
import cc.ryanc.halo.model.dto.JsonResult;
import cc.ryanc.halo.model.enums.PostType;
import cc.ryanc.halo.service.AttachmentService;
import cc.ryanc.halo.utils.LocaleMessageUtil;
import cn.hutool.core.util.StrUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Setter(onMethod = @__({@Autowired}))
@Controller
@RequestMapping("/admin/attachments")
public class AdminAttachmentController {
    private AttachmentService attachmentService;
    private LocaleMessageUtil localeMessageUtil;

    @GetMapping
    public String index(Model model, @PageableDefault(sort = "attachId", direction = DESC) Pageable pageable) {
        model.addAttribute("attachments", attachmentService.listAll(pageable));
        return "admin/admin_attachment";
    }

    @GetMapping("/select")
    public String selectAttachment(Model model, HttpServletResponse response,
            @PageableDefault(size = 18, sort = "attachId", direction = DESC) Pageable pageable,
            @RequestParam(value = "id", defaultValue = "none") String id,
            @RequestParam(value = "type", defaultValue = "normal") String type) {
        Page<Attachment> attachments = attachmentService.listAll(pageable);
        model.addAttribute("attachments", attachments);
        model.addAttribute("id", id);

        if (StrUtil.equals(type, PostType.POST_TYPE_POST.getType())) {
            return "admin/widget/_attachment-select-post";
        }
        return "admin/widget/_attachment-select";
    }

    @PostMapping(value = "/upload", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public JsonResult upload(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        Attachment attach = attachmentService.upload(file, request);
        if (attach == null) {
            return JsonResult.fail("can't upload image");
        }

        return JsonResult.success("upload image success", attach);
    }
}
