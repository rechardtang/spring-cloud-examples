package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class SimpleFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(SimpleFilter.class);

    private static final String FILTER_TYPE_PRE = "pre";
    private static final String FILTER_TYPE_ROUTE = "route";
    private static final String FILTER_TYPE_POST = "post";
    private static final String FILTER_TYPE_ERROR = "error";
    private static final String FILTER_TYPE_STATIC = "static";


    @Override
    public String filterType() {
        return FILTER_TYPE_PRE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info(String.format("%s %s", request.getMethod(), request.getRequestURL().toString()));

        return null;
    }
}
