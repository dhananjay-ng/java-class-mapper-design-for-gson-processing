package student.data;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import student.data.DbUtil;

@WebListener
public class StartupShutdownListener implements ServletContextListener {

    public StartupShutdownListener() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	DbUtil.stopDerby();
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	DbUtil.startDerby();
    }
    
	
}
