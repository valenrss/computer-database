package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan({"dao","service","validator","controller", "view","main","dto"})
public class SpringConfig {
		
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

}
