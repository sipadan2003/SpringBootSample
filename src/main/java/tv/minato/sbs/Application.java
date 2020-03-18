package tv.minato.sbs;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 *Main class for Web app.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	private static final Logger LOG = Logger.getLogger(Application.class.getName());

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		LOG.config("configure()");
		return application.sources(Application.class);
	}

	public static void main(final String[] args) {
		LOG.config("main()");
		SpringApplication.run(Application.class, args);
	}
}
