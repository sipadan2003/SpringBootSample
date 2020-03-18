package tv.minato.sbs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandExecutor {
	private static final Logger LOG = Logger.getLogger(CommandExecutor.class.getName());
	
	private CommandExecutor() {}
	
	public static Result run(final String[] command) {
		final List<String> outputs = new ArrayList<>();

		try{
			final Process p = new ProcessBuilder(command).redirectErrorStream(true).start(); //throws IOException

			try(final BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))){
				String str;
				while((str = r.readLine()) != null) {
					outputs.add(str);
				}
			}

			final int exitValue = p.waitFor(); //throws InterruptedException
			LOG.fine(()->"exitValue="+exitValue);
			return new Result(command, exitValue, outputs);
		}catch(IOException | InterruptedException e) {
			LOG.log(Level.SEVERE, "Error", e);
			return new Result(command, outputs, e);
		}
	}
	
	public static class Result {
		private final String[] command;
		private final int exitValue; 
		private final List<String> outputs;
		private final Throwable throwable;
		
		Result(String[] command, int exitValue, List<String> outputs){
			this.command = command;
			this.exitValue = exitValue;
			this.outputs = outputs;
			this.throwable = null;
		}

		Result(String[] command, List<String> outputs, Throwable throwable){
			this.command = command;
			this.exitValue = Integer.MIN_VALUE;
			this.outputs = outputs;
			this.throwable = throwable;
		}

		public String[] getCommand()     { return command; }
		public int getExitValue()        { return exitValue; }
		public List<String> getOutputs() { return outputs;	}
		public Throwable getThrowable()  { return throwable; }
	}
}
