package org.stt.module.framework.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.method.HandlerMethod;

/**
 * action上下文
 * @author wangchao
 *
 */
public class ActionContext {

    HttpServletRequest request;

    HandlerMethod handlerMethod;

    public ActionContext(HttpServletRequest request, HandlerMethod handlerMethod) {

        this.request = request;
        this.handlerMethod = handlerMethod;
    }

    public HandlerMethod getHandlerMethod() {

        return handlerMethod;
    }

    public void setHandlerMethod(HandlerMethod handlerMethod) {

        this.handlerMethod = handlerMethod;
    }

    public HttpServletRequest getRequest() {

        return request;
    }

    public void setRequest(HttpServletRequest request) {

        this.request = request;
    }

}
