package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan({"dao","service","validator","controller", "view","main","dto"})
public class SpringConfig implements WebApplicationInitializer {
		
	private static String configFile = "/config.properties";
	private static HikariConfig cfg;

	  /**
	   * Data source.
	   *
	   * @return the hikari data source
	   */
	  //private static final HikariConfig hikariConfig = new HikariConfig("/config.properties");
	  @Bean
	  public HikariDataSource dataSource() {
	 
		cfg = new HikariConfig(configFile);
	    HikariDataSource ds = new HikariDataSource(cfg);

	    return ds;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SpringConfig.class);

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(rootContext));
	}

}
