package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@ComponentScan({"dao","service","validator","controller", "view","main","dto","servlet","mapper"})
public class SpringConfig implements WebApplicationInitializer {
		
	private static String configFile = "/config.properties";
	private static Logger logger  = LoggerFactory.getLogger(SpringConfig.class);

	  /**
	   * Data source.
	   *
	   * @return the hikari data source
	   */
	  @Bean
	  public static HikariDataSource dataSource() {
		  
		  HikariConfig cfg;
		  
		  try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
	}
	 
		cfg = new HikariConfig(configFile);
	    HikariDataSource dataSource = new HikariDataSource(cfg);
	    
	    dataSource.setLeakDetectionThreshold(60 * 1000);
	    dataSource.setMaximumPoolSize(16);

	    return dataSource;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SpringConfig.class);

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(rootContext));
	}

}
