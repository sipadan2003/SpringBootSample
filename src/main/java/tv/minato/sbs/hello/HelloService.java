package tv.minato.sbs.hello;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import tv.minato.sbs.Util;

@Service
public class HelloService {
	private static final Logger LOG = Logger.getLogger(HelloService.class.getName());

	public String hello(final String str) {
		LOG.info(()->"hello(): START: "+Util.threadInfo());
		final String result = "Hello, "+str+"!";
		LOG.info(()->"hello(): END  : "+Util.threadInfo());
		return result;
	}
}
