package thymeleafexamples.stsm.aa;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

import thymeleafexamples.stsm.business.SpringBusinessConfig;
import thymeleafexamples.stsm.web.SpringWebConfig;

public class SpringWebAppcationInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {
    public static final String CHARACTER_ENCODING = "UTF-8";

    public SpringWebAppcationInitializer() {
        super();
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SpringBusinessConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected javax.servlet.Filter[] getServletFilters() {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(CHARACTER_ENCODING);
        encodingFilter.setForceEncoding(true);
        return new Filter[]{encodingFilter};
    }
}
