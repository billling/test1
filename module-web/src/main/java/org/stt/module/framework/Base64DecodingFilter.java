package org.stt.module.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * 统一处理base64编码的入参
 * @author wangchao
 *
 */
public class Base64DecodingFilter implements Filter {

    private PathMatcher matcher = new AntPathMatcher();

    private List<String> noDecodeList = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        noDecodeList.add("/test/demo");
        
        // TODO just test for calling esb
        noDecodeList.add("/ability/call/index");
        noDecodeList.add("/ability/call/type");
        
        noDecodeList.add("/captcha/validCaptcha");
        noDecodeList.add("/file/upload");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (isNoDecodeList(req.getServletPath())) {
            chain.doFilter(req, resp);
        } else {
            Base64HttpServletRequestWrapper DecodingRequest = new Base64HttpServletRequestWrapper(req);
            chain.doFilter(DecodingRequest, resp);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 目标路径是否在忽略列表
     * @param servletPath
     * @return true 在； false 不在
     */
    private boolean isNoDecodeList(String servletPath) {

        boolean ret = false;
        if (noDecodeList.contains(servletPath)) {
            ret = true;
        } else if (!noDecodeList.isEmpty()) {
            for (String filtermapping : noDecodeList) {
                if (matcher.match(filtermapping, servletPath)) {
                    ret = true;
                    break;
                }
            }
        }
        return ret;
    }

}
