package tv.minato.sbs.core;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
@EnableAsync
public class MainConfig {
	private static final Logger LOG = Logger.getLogger(MainConfig.class.getName());

	@Autowired
	ThreadPoolSettings threadPoolSettings;

	@Bean
	public HandlerInterceptor sampleInterceptor() throws Exception{
		LOG.info("sampleInterceptor()");
		return new SampleInterceptor();
	}

	@Bean
	public ThreadPoolTaskExecutor taskExecutors1() {
		LOG.info("taskExecutors1()");

		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(threadPoolSettings.getCorePoolSize());
		executor.setQueueCapacity(threadPoolSettings.getQueueCapacity());
		executor.setMaxPoolSize(threadPoolSettings.getMaxPoolSize());
		executor.setKeepAliveSeconds(threadPoolSettings.getKeepAliveSeconds());
		executor.setThreadNamePrefix("Executor1-");
		executor.setDaemon(false);
		executor.initialize();
		return executor;
	}
}
