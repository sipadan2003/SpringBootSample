package tv.minato.sbs.async1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tv.minato.sbs.Util;

@RestController
public class Async1Controller {
	private static final Logger LOG = Logger.getLogger(Async1Controller.class.getName());
	private static final String[] COMMAND = new String[] {"cmd", "/c", "ping", "-n", "10", "localhost", ">", "nul"};
	private AtomicInteger counter = new AtomicInteger(1);
	private HashMap<String, Future<String>> futures = new HashMap<>();
	
	@Autowired
	private Async1Service async1Service;

	@RequestMapping(value=Util.API1_PATH+"/async1", method=RequestMethod.GET)
	@ResponseBody
	@ApiResponses(value= {
			@ApiResponse(code=400, message="必須のパラメータが指定されていない")
	})
	public ResponseEntity<Async1Response> cmd() {
		LOG.fine("cmd()");

		final String id = String.format("<noTenantId>#%03d", counter.getAndIncrement());
		try{
			final CompletableFuture<String> future = async1Service.cmd(COMMAND)
				.exceptionally(ex -> id+": Exception: "+ex.getMessage()+"\n");
			synchronized(this.futures) {
				this.futures.put(id, future);
			}
			return new ResponseEntity<Async1Response>(new Async1Response(id, "Accepted"), HttpStatus.OK);
		}catch(RejectedExecutionException e) {
			LOG.info("Rejected: "+id);

			final CompletableFuture<String> future = new CompletableFuture<>();
			future.complete("Rejected");
			synchronized(this.futures) {
				this.futures.put(id, future);
			}

			return new ResponseEntity<Async1Response>(new Async1Response(id, "Rejected"), HttpStatus.TOO_MANY_REQUESTS);
		}
	}
	
	@RequestMapping(value=Util.API1_PATH+"/status", method=RequestMethod.GET)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public String status() {
		LOG.fine("status()");

		final Map<String, Future<String>> map;
		synchronized(this.futures) {
			map = (Map<String, Future<String>>)this.futures.clone();
		}
		return map.entrySet().stream()
			.map(entry->Async1Controller.futureGet(entry.getKey(), entry.getValue()))
			.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
			.toString();
	}

	//非同期タスクの状況/結果を文字列に変換する
	private static String futureGet(String tenantId, Future<String> future) {
		if(future.isDone()==false && future.isCancelled()==false) {
			return tenantId+": Running\n";
		}else if(future.isCancelled()) {
			return tenantId+": Canceled\n";
		}
		try {
			return tenantId+": "+future.get()+"\n";
		} catch (InterruptedException | ExecutionException | CancellationException e) {
			LOG.log(Level.SEVERE, "Error", e);
			return tenantId+": Exception\n";
		}
	}

	public class Async1Response {
		private final String id;
		private final String str;

		public Async1Response() {
			this(null, null);
		}
		
		Async1Response(String id, String str) {
			this.id = id;
			this.str = str;
		}
		
		public String getId()  { return this.id; }
		public String getStr() { return str; }
	}
}
