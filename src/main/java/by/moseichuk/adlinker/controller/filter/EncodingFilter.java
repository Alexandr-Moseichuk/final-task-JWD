package by.moseichuk.adlinker.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Sets encoding to incoming requests
 *
 * @author Moseichuk Alexandr
 */
public class EncodingFilter implements Filter {

    /**
     * Sets "UTF-8" encoding. Causes the next filter in the chain to be invoked,
     * or if the calling filter is the last filter in the chain, causes the
     * resource at the end of the chain to be invoked.
     *
     * @param servletRequest    the ServletRequest object contains the client's request
     * @param servletResponse   the ServletResponse object contains the filter's response
     * @param filterChain       the FilterChain for invoking the next filter or the resource
     * @throws IOException      if an I/O related error has occurred during the processing
     * @throws ServletException if an exception occurs that interferes with the filter's normal operation
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
