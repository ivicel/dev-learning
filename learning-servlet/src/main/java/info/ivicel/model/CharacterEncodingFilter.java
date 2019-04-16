package info.ivicel.model;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharacterEncodingFilter implements Filter {
    private static final String PARAM_ENCODING = "encoding";
    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String paramEncoding = filterConfig.getInitParameter(PARAM_ENCODING);
        encoding = paramEncoding != null ? paramEncoding : encoding;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println(((HttpServletRequest)request).getRequestURL());
        chain.doFilter(request, response);
    }
}
