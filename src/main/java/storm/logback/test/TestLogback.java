package storm.logback.test;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class TestLogback extends Thread {
	private Logger log = (Logger) LoggerFactory.getLogger(TestLogback.class);
	private int num = 0;
	private long sleepTime;
	private String projectName;
	
	public TestLogback(long _sleepTime,String _projectName){
		sleepTime = _sleepTime;
		projectName = _projectName;
	}
	public void testWriter(){
		log.info("projectName = " + projectName + ",log = " + log.hashCode() + ",threadId = " + Thread.currentThread().getId() + ",num = " + (num++));
	}
	public void run() {
		try{
			while(true){
				testWriter();
				Thread.sleep(sleepTime);
			}
		}catch(Throwable t){
			log.error(t.getMessage());
		}
	}
}
