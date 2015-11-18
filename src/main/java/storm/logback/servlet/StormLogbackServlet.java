package storm.logback.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import storm.logback.test.TestLogback;

/**
 * Servlet implementation class StormLogbackServlet
 */
public class StormLogbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = (Logger) LoggerFactory.getLogger(StormLogbackServlet.class);
	//开启的线程数
	private static int threadCount;
	//线程休眠时间
	private static long sleepTime;
	private static List<Thread> threads;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StormLogbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stop = request.getParameter("stop");
		if(stop == null){
			threads = new ArrayList<Thread>();
			threadCount = Integer.valueOf(request.getParameter("t"));
			sleepTime = Long.valueOf(request.getParameter("s"));
			for(int i = 0;i < threadCount;i++){
				Thread thread = new TestLogback(sleepTime,request.getContextPath());
				thread.start();
				threads.add(thread);
				log.info("thread启动 ，id = " + thread.getId());
			}
			response.getWriter().append("project start:" + request.getContextPath() + ",threadCount:").append(threadCount + "").append(",sleepTime:" + sleepTime);
		}else{
			for(Thread t : threads){
				t.stop();
			}
			threadCount = 0;
			//线程休眠时间
			sleepTime = 0;
			threads = null;
			response.getWriter().append("project stop!").append("project name:" + request.getContextPath() + ",threadCount:").append(threadCount + "").append(",sleepTime:" + sleepTime);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
