package tv.minato.sbs.hello;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tv.minato.sbs.Util;

@RestController
public class HelloController {
	private static final Logger LOG = Logger.getLogger(HelloController.class.getName());
	private AtomicInteger counter = new AtomicInteger(1);

	//自動的にSampleServiceクラスを探してこのフィールドに注入してくれる
	@Autowired
	private HelloService helloService;

	@RequestMapping(value=Util.API1_PATH+"/hello", method=RequestMethod.GET)
	@ResponseBody
	@ApiResponses(value= {
			@ApiResponse(code=400, message="必須のパラメータが指定されていない")
	})
	public ResponseEntity<HelloResponse> hello(@RequestParam(value="name", required=true)final String name) {
		LOG.fine("hello()");
		final String id = String.format("#%03d", counter.getAndIncrement());
		final String hello = helloService.hello(name);
		return new ResponseEntity<HelloResponse>(new HelloResponse(id, hello), HttpStatus.OK);
	}
	
	public class HelloResponse {
		private final String id;
		private final String str;

		public HelloResponse() {
			this(null, null);
		}
		
		HelloResponse(String id, String str) {
			this.id = id;
			this.str = str;
		}
		
		public String getId()  { return this.id; }
		public String getStr() { return str; }
	}
}
