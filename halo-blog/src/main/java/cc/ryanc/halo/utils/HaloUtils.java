package cc.ryanc.halo.utils;

import cc.ryanc.halo.model.dto.Theme;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

@Slf4j
public class HaloUtils {

    public static String parseSize(long size) {
        if (size < 1024) {
            // Byte
            return size + "B";
        } else if (size < 1024 * 1024) {
            // KB
            return String.format("%.2fKB", (float) size / 1024);
        } else {
            // MB
            return String.format("%.2fMB", (float) size / 1024 / 1024);
        }
    }

    public static String getImageWh(File file) {
        try {
            final BufferedImage image = ImageIO.read(new FileInputStream(file));
            return image.getWidth() + "x" + image.getHeight();
        } catch (Exception e) {
            log.error("不能读取图片 {} 的长宽度: {}", file.getName(), e.getMessage());
            return "0";
        }
    }

    public static String urlFilter(String url) {
        if (url != null) {
            final boolean urlEndsWithHtmlPostFix = url.endsWith(".html") || url.endsWith(".html");
            if (urlEndsWithHtmlPostFix) {
                return url.substring(0, url.lastIndexOf("."));
            }
        }
        return StrUtil.replace(url, " ", "-");
    }


    public static String buildImagePath(String base, String filename) {
        String dateDirectory = DateUtil.format(DateUtil.date(), "yyyy/MM/dd");
        if (base.endsWith("/")) {
            base = base.substring(0, base.length() - 1);
        }
        return String.format("/%s/%s/%s", base, dateDirectory, filename);
    }

    public static List<String> getCustomTmpl(String theme) {
        List<String> tmpls = new LinkedList<>();
        try {
            File classpath = new File(ResourceUtils.getFile("classpath:").getPath());
            File themePath = new File(classpath.getAbsolutePath(), "templates/themes/" + theme);
            File[] themeFiles = themePath.listFiles();
            if (themeFiles != null) {
                for (File f : themeFiles) {
                    String name = StrUtil.removeSuffix(f.getName(), ".html");
                    String[] split = name.split("_");
                    if (split.length == 2 && StrUtil.equalsIgnoreCase("page", split[0])) {
                        tmpls.add(name);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            log.warn("page template can not render: {}", e.getMessage());
            e.printStackTrace();
        }
        return tmpls;
    }

    public static List<Theme> getThemes() {
        List<Theme> themes = new LinkedList<>();
        try {
            File basePath = ResourceUtils.getFile("classpath:");
            File themesPath = new File(basePath, "templates/themes");
            File[] rootPathOfThemes = themesPath.listFiles(f ->
                    // 非文件
                    f.isDirectory() &&
                            // 非隐藏目录
                            !f.getName().startsWith(".") &&
                            // mac os 打包
                            !"__MACOSX".equalsIgnoreCase(f.getName())
            );

            if (rootPathOfThemes != null) {
                for (File file : rootPathOfThemes) {
                    Theme theme = new Theme();
                    theme.setThemeName(file.getName());
                    File optionsPath = new File(file, "/module/options.ftl");
                    theme.setHasOptions(optionsPath.exists());
                    File gitPath = new File(file, ".git");
                    theme.setHasUpdate(gitPath.exists());
                    themes.add(theme);
                }
            }
        } catch (IOException e) {
            log.error("Theme scan failed: {}", e.getMessage());
        }

        return themes;
    }
}
