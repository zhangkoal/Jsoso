package cn.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author: sfpy
 * @Date: 5/15/2019 12:55 PM
 * @Descirption
 * @Version 1.0
 */
public class RolesOrFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest,
                                      ServletResponse servletResponse, Object o)  {
        Subject subject = getSubject(servletRequest, servletResponse);

        String[] roles = (String[]) o;

        if (roles == null || roles.length == 0) {
            return false;
        }

        for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }
        }

        return false;
    }

}