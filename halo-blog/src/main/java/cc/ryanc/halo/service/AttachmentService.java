package cc.ryanc.halo.service;

import cc.ryanc.halo.model.domain.Attachment;
import cc.ryanc.halo.service.base.CrudService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService extends CrudService<Attachment, Long> {

    Attachment upload(MultipartFile file, HttpServletRequest request);
}
