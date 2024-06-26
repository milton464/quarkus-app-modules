package com.mt.quarkus;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author MILTON
 */
@Slf4j
public class ConnectionUtil {

	private static final String DB_NAME ="bulk_email_tenant_01";
	
	private static final int CONNECTION_TIMEOUT = 300000;
	
	private static final int MAX_CONNECTION = 50;
	
	private static final int IDLE_TIMEOUT = 120000;
	
	private static final int LEAK_DETECTION_THRESHOLD = 300000;
	
	private static final int MINIMUM_IDLE = 5;
	
	private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	
	private static final String CACHE_PREP_STMTS = "cachePrepStmts";
	
	private static final String PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
	
	private static final String PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";
	
	
	@SuppressWarnings("unused")
	public static Connection getConnection() throws SQLException {
		HikariDataSource hikariDataSource = null;

		synchronized (ConnectionUtil.class) {

			hikariDataSource = buildDataSource();
		}
		System.out.println("getConnection: hikariDataSource:" + hikariDataSource);
		return hikariDataSource == null ? null : hikariDataSource.getConnection();
	}
	
	private static HikariDataSource buildDataSource() {

		String jdbcUrl = new StringBuilder().append("jdbc:mysql://localhost:3306/").append("bulk_email_tenant_01").toString();
		
		if(StringUtils.isNotBlank(jdbcUrl)) {
			
			HikariConfig hikariConfig =  new HikariConfig();
			hikariConfig.setJdbcUrl(jdbcUrl);
			hikariConfig.setUsername("root");
			hikariConfig.setPassword("password");
			hikariConfig.setPoolName(DB_NAME+"_master_pool");
			
			hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT);
			hikariConfig.setMaximumPoolSize(MAX_CONNECTION);
			hikariConfig.setIdleTimeout(IDLE_TIMEOUT);
			hikariConfig.setLeakDetectionThreshold(LEAK_DETECTION_THRESHOLD);
			hikariConfig.setDriverClassName(DRIVER_CLASS_NAME);
			hikariConfig.setMinimumIdle(MINIMUM_IDLE);
			hikariConfig.addDataSourceProperty(CACHE_PREP_STMTS, "true");
			hikariConfig.addDataSourceProperty(PREP_STMT_CACHE_SIZE, "250");
			hikariConfig.addDataSourceProperty(PREP_STMT_CACHE_SQL_LIMIT, "2048");
			
			return new HikariDataSource(hikariConfig);
		}
		
		return null;
		
	}
	
}
