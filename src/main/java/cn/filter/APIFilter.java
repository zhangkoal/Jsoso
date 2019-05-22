package cn.filter;

import cn.token.TokenUtil;
import cn.util.EmptyUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: sfpy
 * @Date: 5/21/2019 9:34 PM
 * @Descirption
 * @Version 1.0
 */

@WebFilter(urlPatterns = "/api/*", filterName = "APIFilter")
public class APIFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(APIFilter.class);

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("================进入过滤器======================");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.reset();

        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        response.setHeader("Access-Control-Allow-Methods","POST, GET, ");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        String token = request.getHeader("token");//header方式

        /**
         * 原因为代码中有打开的Response.getWriter()，未关闭，因调用点较多，不好一一排查。
         * 通过查看代码，看到response中的usingWriter=true，随即想办法将该标志位设置为false
         */
        Map<String, Object> resultMap = new HashMap<>();

        //校验token是否有效
        if(EmptyUtils.isEmpty(token)) {
            resultMap.put("code", "-1");
            resultMap.put("msg", "缺少接口认证参数 token");
        } else {
            boolean isValid = tokenUtil.checkToken(token);
            if(!isValid) {
                resultMap.put("code", "-1");
                resultMap.put("msg", "token失效，请重新获取！");
            }
        }

        if(EmptyUtils.isNotEmpty(resultMap)) {
            response.getWriter().append(new Gson().toJson(resultMap));
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
