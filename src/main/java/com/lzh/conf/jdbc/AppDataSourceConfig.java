package com.lzh.conf.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:/config.properties" })
public class AppDataSourceConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	SqlSessionTemplate sessionTemplate;
	

	@Bean(destroyMethod = "close")
	public DataSource dataSource(final Environment env) {
		return this.getDataSource(env);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(final Environment env) {
		return this.getSqlSessionFactory(env);
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(final Environment env) {
		return new SqlSessionTemplate(this.getSqlSessionFactory(env));
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(final Environment env) {
		return new DataSourceTransactionManager(this.getDataSource(env));
	}

	@Bean 
	public MapperScannerConfigurer mapperScannerConfigurer(final Environment env){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage(env.getProperty("jdbc.mapper.scanner.basepackage"));
		mapperScannerConfigurer.setSqlSessionFactoryBeanName(env.getProperty("jdbc.mapper.sqlSessionFactory"));
		return mapperScannerConfigurer;
	}
	
	
private DataSource getDataSource(final Environment env) {
		
		if( this.dataSource != null ) {
			return this.dataSource; 
		}
		
		final DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(env.getRequiredProperty("jdbc.url"));
		druidDataSource
				.setDriverClassName(env.getRequiredProperty("jdbc.driver"));
		druidDataSource.setUsername(env.getRequiredProperty("jdbc.username"));
		druidDataSource.setPassword(env.getRequiredProperty("jdbc.password"));
		druidDataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class));
		druidDataSource.setMinIdle(env.getProperty("jdbc.minIdle", Integer.class));
		druidDataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class));

		druidDataSource.setMaxWait(env.getProperty("jdbc.maxWait", Long.class));
		druidDataSource.setTimeBetweenEvictionRunsMillis(env.getProperty("jdbc.timeBetweenEvictionRunsMillis", Long.class));
		druidDataSource.setMinEvictableIdleTimeMillis(env.getProperty("jdbc.minEvictableIdleTimeMillis", Long.class));

		druidDataSource.setPoolPreparedStatements(env.getProperty("jdbc.poolPreparedStatements", Boolean.class));
		
		try {
			// 增加监控配置
			this.addDSMonitor(druidDataSource,env);
			// 初始化数据源
			druidDataSource.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.dataSource = druidDataSource; 

		return druidDataSource;
	}
	

private void addDSMonitor(DruidDataSource druidDataSource, Environment env) throws SQLException {

	if(druidDataSource != null){
		StatFilter statFileter = new StatFilter();
		statFileter.setMergeSql(true);
		statFileter.setLogSlowSql(true);
		statFileter.setSlowSqlMillis(3000);
		
		List<Filter> proxyFilter = new ArrayList<Filter>();
		proxyFilter.add(statFileter);
		
		druidDataSource.setProxyFilters(proxyFilter);
		druidDataSource.addFilters(env.getProperty("jdbc.monitor.stat"));
	}}


private SqlSessionFactory getSqlSessionFactory(final Environment env) {
	if(this.sqlSessionFactory!=null) return this.sqlSessionFactory;		
	SqlSessionFactoryBean bean = new SqlSessionFactoryBean();						
	
	ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	Resource[] resources;
	try {
		resources = resolver.getResources(env.getProperty("jdbc.mapper.scanner.resource"));
		bean.setMapperLocations(resources);
	} catch (IOException e1) {
		
		e1.printStackTrace();
	}
	
	bean.setDataSource(this.getDataSource(env));
	
	// 分页插件
	/*PageHelper pageHelper = new PageHelper();
	Properties properties = new Properties();
	properties.setProperty("dialect",env.getProperty("jdbc.dialect"));
	//properties.setProperty("offsetAsPageNum", "true");
	pageHelper.setProperties(properties);
	Interceptor[] plugins = {pageHelper};
	
	bean.setPlugins(plugins);*/
	
	SqlSessionFactory sqlSessionFactoryToCreate = null;
	try {
		sqlSessionFactoryToCreate = bean.getObject();
	} catch (Exception e) {
		e.printStackTrace();
		System.exit(0);
	}
	this.sqlSessionFactory = sqlSessionFactoryToCreate;
	return sqlSessionFactoryToCreate;
}
}
