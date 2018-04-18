package cn.filter;

import cn.Util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;



import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * 使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * @author Angel(QQ:412887952)
 * @version v.0.1
 */
@WebFilter(filterName="myFilter",urlPatterns="/*")
public class SessionFilter implements Filter {


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
        if((requestUrl.equalsIgnoreCase("/") || requestUrl.equalsIgnoreCase("/register")) ||
                requestUrl.endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        } else {
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
                //采用重定义可以去年
                servletResponse.sendRedirect("");
                //request.getRequestDispatcher("").forward(request, response);
            }
        }
    }



    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
