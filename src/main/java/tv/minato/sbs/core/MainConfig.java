package tv.minato.sbs.core;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
@EnableAsync
public class MainConfig {
	private static final Logger LOG = Logger.getLogger(MainConfig.class.getName());

	@Bean
	public HandlerInterceptor sampleInterceptor() throws Exception{
		LOG.info("sampleInterceptor()");
		return new SampleInterceptor();
	}
}
