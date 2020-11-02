package cn.tedu.sp11.filter;

import cn.tedu.web.util.JsonResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessFilter extends ZuulFilter {

    //过滤器类型: pre,routes,post,error
    @Override
    public String filterType() {
        //return "pre";
        return FilterConstants.PRE_TYPE;
    }
    //当前过滤器添加到哪个位置,返回一个顺序号
    @Override
    public int filterOrder() {
        /*
        * 前置过滤器中,已经存在5个默认的过滤器
        * 在第5个过滤期中,向上下文对象添加了"serviceId"属性*/

        return 6;
    }
    //针对当前的请求进行判断,是否执行过滤代码(run方法)
    @Override
    public boolean shouldFilter() {
        //当前请求,调用的是否是item-service
        //如果是item,执行过滤代码,不是则跳过过滤代码

        //1.获得正在调用的服务id
        RequestContext context = RequestContext.getCurrentContext();//zuul请求上下文对象
        String serviceId =(String) context.get(FilterConstants.SERVICE_ID_KEY);//从上下文对象获取"服务id"属性

        return "item-service".equalsIgnoreCase(serviceId);
    }
    //过滤方法,判断权限写在这里
    @Override
    public Object run() throws ZuulException {
        //有无token判断是否登录,能不能访问

        //获得request
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //用request接受token 参数
        String token = request.getParameter("token");
        //如果token参数为空
        if(StringUtils.isBlank(token)){
            context.setSendZuulResponse(false);//阻止继续访问
            String json = JsonResult.err().code(JsonResult.NOT_LOGIN).msg("Not Login").toString();
            context.setResponseStatusCode(JsonResult.NOT_LOGIN);
            context.setResponseBody(json);
        }
        return null;//当前zuul版本中,该返回值没有使用,不起任何作用
    }
}
