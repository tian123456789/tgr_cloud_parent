package com.tgr.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author tgr
 *注入到IOC赶紧去过滤token吧
 */
@Component
public class MyFilter extends ZuulFilter {

	private static Log log = LogFactory.getLog(MyFilter.class);
	
	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#run()
	 * 具体的过滤逻辑
	 * 加过看看有没有token
	 */
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		Object accessToken = request.getParameter("token");
		if(accessToken == null) {
			log.warn("token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			try {
				ctx.getResponse().getWriter().write("token is empty");
			} catch (Exception e) {
				return null;
			}
		}
		log.info("ok");
		return null;
		//http://127.0.0.1:5000/v1/hiapi/hi?name=opop		token is empty
		//http://127.0.0.1:5000/v1/hiapi/hi?name=opop&token=123		hi opop , i am from port:8764
		//可见， MyFilter 这个 Bean 注入 IoC 容器之后，对请求进行了过滤，并在请求路由转发
		//之前进行了逻辑判断。在实际开发中，可以用此过滤器进行安全验证
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 * 表示该过滤器是否过滤逻辑 为true则执行run()方法
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 * 过滤顺序 值为Int 值越小越早执行该过滤器
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 * 过滤器类型
	 * 四种: pre,post,routing,error
	 */
	@Override
	public String filterType() {
		return "pre";
	}

}
