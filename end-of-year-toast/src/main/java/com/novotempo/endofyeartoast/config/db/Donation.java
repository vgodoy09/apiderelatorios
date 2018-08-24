package com.novotempo.endofyeartoast.config.db;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.novotempo.endofyeartoast.config.variableGlobals.ConstantsDB;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.novotempo.endofyeartoast.repository.donation", 
		entityManagerFactoryRef = "donationEntityManager", 
		transactionManagerRef = "donationTransactionManager"
		)

public class Donation {

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean donationEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(donationDataSource());
		em.setPackagesToScan(new String[] { 
				"com.novotempo.endofyeartoast.model.donation" 
		});

		HibernateJpaVendorAdapter vendorAdapter= new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.use_sql_comments", false);
		properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
		
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Primary
	@Bean
	public DataSource donationDataSource() {
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://10.21.0.25;databaseName=Donation");
		dataSource.setUsername(ConstantsDB.getDonationSysUser());
		dataSource.setPassword(ConstantsDB.getDonationPassword());
		
		return dataSource;
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager donationTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(donationEntityManager().getObject());
		return transactionManager;
	}
	
	@Primary
	@Bean(name="donationTransactionTemplate")
	public TransactionTemplate donationTransactionTemplate() {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(donationTransactionManager());
		return transactionTemplate;
	}
	
}