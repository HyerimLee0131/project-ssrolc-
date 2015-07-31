package com.ssrolc.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ssrolc.repository.AnotherRepositoryAnnoInterface;
import com.ssrolc.repository.PrimaryRepositoryAnnoInterface;


@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DataSourceConfig {
	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
	
	@Bean
	@Primary
	public DataSource getPrimaryDataSource() {
		logger.debug("======================DataSource=================초기화!");
//		final JndiDataSourceLookup basicDataSource = new JndiDataSourceLookup();
//		basicDataSource.setResourceRef(true);
//		return basicDataSource.getDataSource("java:comp/env/jdbc/myJeiGlobal");
		final BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUsername("devadmin");
		basicDataSource.setPassword("smart2012)*@&mydb");
		basicDataSource.setUrl("jdbc:mysql://192.1.27.209:3306/ssrolc");
		
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
//		return DataSourceBuilder
//	            .create()
//	            .build();
	}
	
	@Bean
	public DataSource getAnotherDataSource() {
//		final JndiDataSourceLookup basicDataSource = new JndiDataSourceLookup();
//		basicDataSource.setResourceRef(true);
//		return basicDataSource.getDataSource("java:comp/env/jdbc/jeiMssql");
		final BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		basicDataSource.setUsername("sa");
		basicDataSource.setPassword("16658202");
		basicDataSource.setUrl("jdbc:sqlserver://192.1.27.245:1433;DatabaseName=JEIGLOBAL;");
		
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
//		return DataSourceBuilder
//	            .create()
//	            .build();
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
