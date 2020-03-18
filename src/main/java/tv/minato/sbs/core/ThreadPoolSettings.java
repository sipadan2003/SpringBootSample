package tv.minato.sbs.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "thread-pool")
public class ThreadPoolSettings {
	//corePoolSize  = 初期のThread数
	//queueCapacity = corePoolSizeが一杯になったときにキューイングする数
	//maxPoolSize   = queueCapacityを越えたときの最大Thread数（maxPoolSizeを越えるとrejectされる）
	private String corePoolSize;
	private String queueCapacity;
	private String maxPoolSize;
	private String keepAliveSeconds;

	public void setCorePoolSize(String corePoolSize)         {this.corePoolSize = corePoolSize;}
	public void setQueueCapacity(String queueCapacity)       {this.queueCapacity = queueCapacity;}
	public void setMaxPoolSize(String maxPoolSize)           {this.maxPoolSize = maxPoolSize;}
	public void setKeepAliveSeconds(String keepAliveSeconds) {this.keepAliveSeconds = keepAliveSeconds;}

	public int getCorePoolSize()     {return Integer.valueOf(corePoolSize);}
	public int getQueueCapacity()    {return Integer.valueOf(queueCapacity);}
	public int getMaxPoolSize()      {return Integer.valueOf(maxPoolSize);}
	public int getKeepAliveSeconds() {return Integer.valueOf(keepAliveSeconds);}
}
