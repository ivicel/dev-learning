package com.mmall.service.impl;

import com.mmall.service.IFileService;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service("fileService")
public class FileServiceImpl implements IFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String upload(String path, MultipartFile file) {
        String filename = file.getOriginalFilename();
        String fileExtension = filename.substring(filename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + fileExtension;

        LOGGER.info("开始上传文件, 上传的文件名: {}, 上传的路径: {}, 新文件名为: {}", filename, path, newFilename);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(fileDir, newFilename);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            LOGGER.error("上传文件异常", e);
            return null;
        }

        return targetFile.getName();
    }
}
