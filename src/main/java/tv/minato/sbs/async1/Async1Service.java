package tv.minato.sbs.async1;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import tv.minato.sbs.CommandExecutor;

@Service
public class Async1Service {
	private static final Logger LOG = Logger.getLogger(Async1Service.class.getName());

	@Async("taskExecutors1")
	public CompletableFuture<String> cmd(final String[] command) {
		final Thread t = Thread.currentThread();
		LOG.info(()->"cmd(): START: thread="+t.getName());

		//外部コマンドを実行
		final CommandExecutor.Result r = CommandExecutor.run(command);

//		final String dt2 = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
		LOG.info(()->"cmd(): END: ");
		return CompletableFuture.completedFuture("exitValue="+r.getExitValue());
	}
}
