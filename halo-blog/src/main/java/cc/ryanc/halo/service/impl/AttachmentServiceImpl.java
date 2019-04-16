package cc.ryanc.halo.service.impl;

import static cc.ryanc.halo.model.dto.HaloConst.OPTIONS;

import cc.ryanc.halo.config.HaloProperties;
import cc.ryanc.halo.model.domain.Attachment;
import cc.ryanc.halo.model.enums.AttachLocation;
import cc.ryanc.halo.model.enums.BlogProperties;
import cc.ryanc.halo.repository.AttachmentRepository;
import cc.ryanc.halo.service.AttachmentService;
import cc.ryanc.halo.service.base.AbstractCrudService;
import cc.ryanc.halo.utils.HaloUtils;
import cn.hutool.core.util.StrUtil;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Setter(onMethod = @__({@Autowired}))
@Service("attachmentService")
public class AttachmentServiceImpl extends AbstractCrudService<Attachment, Long> implements AttachmentService {
    private HaloProperties haloProperties;
    private AttachmentRepository attachmentRepository;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        super(attachmentRepository);
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    @Transactional
    public Attachment upload(MultipartFile file, HttpServletRequest request) {
        String attachLoc = OPTIONS.get(BlogProperties.ATTACH_LOC.getProp());
        if (StrUtil.isBlank(attachLoc)) {
            attachLoc = "server";
        }

        switch (attachLoc) {
            case "server":
                return attachUpload(file, request);

            case "qiniu":
                // 七牛
                return attachQiniuUpload(file, request);

            case "upyun":
                // upyun
                return attachUpyunUpload(file, request);

            default:
                // 自定义
                return attachUpload(file, request);
        }
    }

    private Attachment attachUpyunUpload(MultipartFile file, HttpServletRequest request) {
        return null;
    }

    private Attachment attachQiniuUpload(MultipartFile file, HttpServletRequest request) {
        return null;
    }

    private Attachment attachUpload(MultipartFile file, HttpServletRequest request) {
        File uploadPath = new File(HaloUtils.buildImagePath(haloProperties.getImageUploadDirectory(), ""));
        if (!uploadPath.isDirectory() && !uploadPath.mkdirs()) {
            log.error("can not create directory {}", uploadPath.getAbsolutePath());
            return null;
        }

        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        originalFilename = originalFilename.replaceAll("[,\\s]", "_");

        // 原文件名及后缀
        String filename;
        String suffix;
        int index = originalFilename.lastIndexOf(".");
        if (index == -1) {
            filename = originalFilename;
            suffix = "";
        } else {
            filename = originalFilename.substring(0, index);
            suffix = originalFilename.substring(index);
            if (filename.length() == 0) {
                filename = UUID.randomUUID().toString();
            }
        }

        Random r = new Random(System.currentTimeMillis());
        String hashedFilename = DigestUtils.md5DigestAsHex((filename + r.nextLong()).getBytes(StandardCharsets.UTF_8));
        File uploadFile = new File(uploadPath, hashedFilename + suffix);
        File uploadSmallFile = new File(uploadPath, hashedFilename + "_small" + suffix);

        try {
            file.transferTo(uploadFile);
            Thumbnails.of(uploadFile).size(256, 256).keepAspectRatio(false).toFile(uploadSmallFile);
        } catch (IOException e) {
            log.error("can not upload file: {}", e.getMessage());
            return null;
        }

        String size = HaloUtils.parseSize(uploadFile.length());
        String wh = HaloUtils.getImageWh(uploadFile);


        Attachment attach = new Attachment();
        attach.setAttachName(file.getOriginalFilename());
        attach.setAttachLocation(AttachLocation.SERVER.getDesc());
        attach.setAttachPath(HaloUtils.buildImagePath(
                BlogProperties.DEFAULT_UPLOAD_IMAGE_URL_PREFIX.getProp(), uploadFile.getName()));
        attach.setAttachSmallPath(HaloUtils.buildImagePath(
                BlogProperties.DEFAULT_UPLOAD_IMAGE_URL_PREFIX.getProp(), uploadSmallFile.getName()));
        attach.setAttachSize(size);
        if (suffix.length() > 1) {
            attach.setAttachSuffix(suffix);
        } else {
            attach.setAttachSuffix(suffix.substring(1));
        }
        attach.setAttachType(file.getContentType());
        attach.setAttachWh(wh);

        attach = create(attach);
        log.info("Upload image {} to server path {}, map to {} successful", attach.getAttachName(),
                uploadFile.getPath() + "/" + uploadFile.getName(), attach.getAttachPath());
        return attach;
    }
}
