package cn.filter;

import cn.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *
 * 使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * @author Angel(QQ:412887952)
 * @version v.0.1
 */

public class SessionFilter implements Filter {
    /**
     * @Component
     @WebFilter(filterName="myFilter",urlPatterns="/*")
     */


    @Autowired
    private SessionUtil sessionUtil;



    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                        FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hrequest = (HttpServletRequest)request;
        String requestUrl = hrequest.getRequestURI();
        if(requestUrl.endsWith(".jso")) {
            Cookie[] cookie = hrequest.getCookies();
            System.out.println(cookie);
            System.out.println("执行过滤操作");
            boolean flag = false;
            if(cookie != null) {
                for(Cookie c : cookie) {
                    if(sessionUtil.checkUserSession(c.getValue())) {
                        flag = true;
                        break;
                    }
                }
            }
            if(!flag) {
                HttpServletResponse servletResponse = (HttpServletResponse) response;
                //采用重定义可以去掉
                servletResponse.sendRedirect("login");
                //request.getRequestDispatcher("").forward(request, response);
            }
        }
        chain.doFilter(request, response);
        return;
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
