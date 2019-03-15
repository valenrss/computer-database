package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan({ "dao", "service", "validator", "clicontroller", "view", "main", "dto", "mapper" })
public class SpringConfigCLI {

	private static String configFile = "/config.properties";
	private static Logger logger = LoggerFactory.getLogger(SpringConfigCLI.class);

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

		return dataSource;
	}

}
