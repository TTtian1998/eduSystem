package edusystem.org.common.shiro;

import edusystem.org.common.entity.Result;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static edusystem.org.common.myEnum.ResultTypeEnum.ERROR_20011;

/**
 * @author 田泽鑫
 * @date 2019/11/22
 * @description: 对没有登录的请求进行拦截, 全部返回json信息. 覆盖掉shiro原本的跳转login.jsp的拦截方式
 */
public class AjaxPermissionFilter extends FormAuthenticationFilter {
    /**
     * 前后端分离项目中，由于跨域，会导致复杂请求，即会发送preflighted request，这样会导致在GET／POST等
     * 请求之前会先发一个OPTIONS请求，但OPTIONS请求并不带shiro的'Authorization'字段（shiro的Session），
     * 即OPTIONS请求不能通过shiro验证，会返回未认证的信息。
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Always return true if the request's method is OPTIONSif (request instanceof HttpServletRequest) {
        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response){
        Result result = new Result();
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = response.getWriter();
            out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }

    @Bean
    public FilterRegistrationBean registration(AjaxPermissionFilter filter){
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
