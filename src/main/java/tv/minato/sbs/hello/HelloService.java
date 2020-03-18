package tv.minato.sbs.hello;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import tv.minato.sbs.Util;

@Service
public class HelloService {
	private static final Logger LOG = Logger.getLogger(HelloService.class.getName());

	public String hello(final String str) {
		LOG.info(()->"cmd2(): START: "+Util.threadInfo());
		final String result = "Hello, "+str+"!";
		LOG.info(()->"cmd2(): END  : "+Util.threadInfo());
		return result;
	}
}
