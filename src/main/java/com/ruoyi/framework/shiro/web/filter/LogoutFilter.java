package com.ruoyi.framework.shiro.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.CommonConstant;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.system.user.domain.User;

/**
 * 退出过滤器
 * 
 * @author ruoyi
 */
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter
{
    private static final Logger log = LoggerFactory.getLogger(LogoutFilter.class);

    /**
     * 退出后重定向的地址
     */
    private String loginUrl;
    
    public String getLoginUrl()
    {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl)
    {
        this.loginUrl = loginUrl;
    }

	@Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception
    {
        try
        {
            Subject subject = getSubject(request, response);
            String redirectUrl = getRedirectUrl(request, response, subject);
            try
            {
            	if(CommonConstant.USERTYPE_PRISONER.equals(request.getParameter(CommonConstant.USERTYPE))){
            		Person person = ShiroUtils.getPerson();
            		if (StringUtils.isNotNull(person))
            		{
            			// 记录用户退出日志
            			AsyncManager.me().execute(AsyncFactory.recordLogininfor(person.getNumber(), Constants.LOGOUT, MessageUtils.message("user.logout.success")));
            		}
            	}else{
            		User user = ShiroUtils.getSysUser();
            		if (StringUtils.isNotNull(user))
            		{
            			// 记录用户退出日志
            			AsyncManager.me().execute(AsyncFactory.recordLogininfor(user.getLoginName(), Constants.LOGOUT, MessageUtils.message("user.logout.success")));
            		}
            	}
                // 退出登录
                subject.logout();
            }
            catch (SessionException ise)
            {
                log.error("logout fail.", ise);
            }
            issueRedirect(request, response, redirectUrl);
        }
        catch (Exception e)
        {
            log.error("Encountered session exception during logout.  This can generally safely be ignored.", e);
        }
        return false;
    }

    /**
     * 退出跳转URL
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject)
    {
    		String url;		//重定向URL
    		if(CommonConstant.USERTYPE_PRISONER.equals(request.getParameter(CommonConstant.USERTYPE))){
    			url = CommonConstant.LOGIN_PERSON_URL;
    		}else{
    			url = getLoginUrl();
    		}
        if (StringUtils.isNotEmpty(url))
        {
            return url;
        }
        return super.getRedirectUrl(request, response, subject);
    }
}
