package tv.minato.sbs.core;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOG = Logger.getLogger(SampleInterceptor.class.getName());
	private static final String ATTR_START_TIME = "startTime";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LOG.info("START: URL: " + request.getRequestURL().toString());
		request.setAttribute(ATTR_START_TIME, System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		final long startTime = (long) request.getAttribute(ATTR_START_TIME);
		LOG.info("END  : URL: " + request.getRequestURL().toString() + ": Elapsed="+(System.currentTimeMillis()-startTime));
		super.postHandle(request, response, handler, modelAndView);
    }
}
