package info.ivicel;


import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

import info.ivicel.web.RootConfig;
import info.ivicel.web.WebConfig;

public class WebAppInitializer {
//        extends AbstractAnnotationConfigDispatcherServletInitializer {
//    private static final String CHARACTER_ENCODING = "UTF-8";
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[]{RootConfig.class};
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[]{WebConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    @Override
//    protected javax.servlet.Filter[] getServletFilters() {
//        return new Filter[]{new CharacterEncodingFilter(CHARACTER_ENCODING, true)};
//    }
}
