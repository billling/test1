package org.stt.module.framework.action;

/**
 * 在拦截器中执行的动作
 * @author wangchao
 *
 */
public interface IWorkAction {

    /**
     * 预处理
     */
    public void after(ActionContext context);

    /**
     * 主业务，如果正常处理完毕返回true
     * @return
     */
    public boolean doAction(ActionContext context) throws Exception;

    /**
     * 判断是否执行此action
     * @return true 需要执行 false 不需要
     */
    public boolean isMatch(ActionContext context);

}
