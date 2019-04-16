package com.mmall.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtils.class);

    private static final String PROPERTIES_FILEANME = "mmall.properties";

    private static final Properties props;

    static {
        props = new Properties();
        try {
            InputStream in = PropertiesUtils.class.getClassLoader()
                    .getResourceAsStream(PROPERTIES_FILEANME);
            if (in == null) {
                throw new IOException("配置文件 mmall.properties 不存在");
            }
            props.load(in);
        } catch (IOException e) {
            LOGGER.error("加载配置文件错误.", e);
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
}
