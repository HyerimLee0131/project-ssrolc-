package com.ssrolc.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ssrolc.repository.AnotherRepositoryAnnoInterface;
import com.ssrolc.repository.PrimaryRepositoryAnnoInterface;


@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DataSourceConfig {
	
	//@Bean
	//@Primary
	public DataSource getPrimaryDataSource() {
//		final JndiDataSourceLookup basicDataSource = new JndiDataSourceLookup();
//		basicDataSource.setResourceRef(true);
//		return basicDataSource.getDataSource("java:comp/env/jdbc/ssrolc");
		final BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(ConnectSetting.mysqlDriverClassName);
		basicDataSource.setUsername(ConnectSetting.mysqlUsername);
		basicDataSource.setPassword(ConnectSetting.mysqlPassword);
		basicDataSource.setUrl(ConnectSetting.mysqlUrl);
		
		basicDataSource.setMaxActive(10);
		basicDataSource.setMaxIdle(4);
		basicDataSource.setMinIdle(4);
		basicDataSource.setMaxWait(5000);

		basicDataSource.setTestWhileIdle(true);
		basicDataSource.setValidationQuery("SELECT 1");

		basicDataSource.setRemoveAbandoned(true);
		basicDataSource.setRemoveAbandonedTimeout(100);
		basicDataSource.setLogAbandoned(true);

		basicDataSource.setTestOnBorrow(false);
		basicDataSource.setTimeBetweenEvictionRunsMillis(30000);
		basicDataSource.setNumTestsPerEvictionRun(-1);
		return basicDataSource;
	}
	
	//@Bean
	public DataSource getAnotherDataSource() {
//		final JndiDataSourceLookup basicDataSource = new JndiDataSourceLookup();
//		basicDataSource.setResourceRef(true);
//		return basicDataSource.getDataSource("java:comp/env/jdbc/jeiMssql");
		final BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(ConnectSetting.mssqlDriverClassName);
		basicDataSource.setUsername(ConnectSetting.mssqlUsername);
		basicDataSource.setPassword(ConnectSetting.mssqlPassword);
		basicDataSource.setUrl(ConnectSetting.mssqlUrl);
		
		basicDataSource.setMaxActive(10);
		basicDataSource.setMaxIdle(4);
		basicDataSource.setMinIdle(4);
		basicDataSource.setMaxWait(5000);

		basicDataSource.setTestWhileIdle(true);
		basicDataSource.setValidationQuery("SELECT 1");

		basicDataSource.setRemoveAbandoned(true);
		basicDataSource.setRemoveAbandonedTimeout(100);
		basicDataSource.setLogAbandoned(true);

		basicDataSource.setTestOnBorrow(false);
		basicDataSource.setTimeBetweenEvictionRunsMillis(30000);
		basicDataSource.setNumTestsPerEvictionRun(-1);
		return basicDataSource;
	}
	/**
	 * DB두대연결해보기위해 설정함
	 * @return
	 */
	@Bean
	public PlatformTransactionManager primaryTransactionManager() {
		return new DataSourceTransactionManager(getPrimaryDataSource());
	}
	/**
	 * DB두대연결해보기위해 설정함
	 * @return
	 */
	@Bean
	public PlatformTransactionManager anotherTransactionManager() {
		return new DataSourceTransactionManager(getAnotherDataSource());
	}
	/**
	 * DB두대연결해보기위해 설정함
	 * @return
	 */
	@Bean(name="mySqlSession")
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(getPrimaryDataSource());
		/**
		 * 리소스에 정의되어있는 mapper 읽어드릴 xml 위치설정
		 */
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
		/**
		 * alias 검색할 패키지 위치
		 */
		sqlSessionFactoryBean.setTypeAliasesPackage("com.ssrolc.domain");
		
		return sqlSessionFactoryBean.getObject();
	}
	/**
	 * DB두대연결해보기위해 설정함
	 * @return
	 */
	@Bean(name="myAnotherSqlSession")
	public SqlSessionFactory getAnotherSqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(getAnotherDataSource());
		/**
		 * 리소스에 정의되어있는 mapper 읽어드릴 xml 위치설정
		 */
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
		/**
		 * alias 검색할 패키지 위치
		 */
		sqlSessionFactoryBean.setTypeAliasesPackage("com.ssrolc.domain");
		
		return sqlSessionFactoryBean.getObject();
	}
	/**
	 * DB두대연결해보기위해 설정함
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer setPrimaryMapperScannerConfigurer(){
		MapperScannerConfigurer primaryMapperScanConfigurer = new MapperScannerConfigurer();
		primaryMapperScanConfigurer.setBasePackage("com.ssrolc");
		primaryMapperScanConfigurer.setAnnotationClass(PrimaryRepositoryAnnoInterface.class);
		primaryMapperScanConfigurer.setBeanName("primaryMapperScanConfigurer");
		primaryMapperScanConfigurer.setSqlSessionFactoryBeanName("mySqlSession");
		return primaryMapperScanConfigurer;
	}
	/**
	 * DB두대연결해보기위해 설정함
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer setAnotherMapperScannerConfigurer(){
		MapperScannerConfigurer anotherMapperScanConfigurer = new MapperScannerConfigurer();
		anotherMapperScanConfigurer.setBasePackage("com.ssrolc");
		anotherMapperScanConfigurer.setAnnotationClass(AnotherRepositoryAnnoInterface.class);
		anotherMapperScanConfigurer.setBeanName("anotherMapperScanConfigurer");
		anotherMapperScanConfigurer.setSqlSessionFactoryBeanName("myAnotherSqlSession");
		return anotherMapperScanConfigurer;
	}
	
}
