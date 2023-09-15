package SDEmpApp.infrastructure.configuration;

import SDEmpApp.ComponentScanMarker;
import SDEmpApp.infrastructure.database.entities._EntityMarker;
import SDEmpApp.infrastructure.database.repository.jpa._JPARepositoriesMarker;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.hibernate.cfg.Environment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;
@Configuration
@AllArgsConstructor
@EnableTransactionManagement
@PropertySource({"classpath:database.properties"})
@ComponentScan(basePackageClasses = ComponentScanMarker.class)
@EnableJpaRepositories(basePackageClasses = _JPARepositoriesMarker.class)
public class ApplicationConfiguration implements WebMvcConfigurer, ApplicationContextAware {


    private final org.springframework.core.env.Environment environment;

    @Setter
    private ApplicationContext applicationContext;


    @Bean
    @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(_EntityMarker.class.getPackageName());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(jpaProperties());
        return entityManagerFactoryBean;
    }

    private Properties jpaProperties() {
        final Properties properties = new Properties();
        properties.setProperty(Environment.DIALECT, environment.getProperty(Environment.DIALECT));
        properties.setProperty(Environment.HBM2DDL_AUTO, environment.getProperty(Environment.HBM2DDL_AUTO));
        properties.setProperty(Environment.SHOW_SQL, environment.getProperty(Environment.SHOW_SQL));
        properties.setProperty(Environment.FORMAT_SQL, environment.getProperty(Environment.FORMAT_SQL));
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean(initMethod = "migrate")
    Flyway flyway() {
        ClassicConfiguration configuration = new ClassicConfiguration();
        configuration.setBaselineOnMigrate(true);
        configuration.setLocations(new Location("filesystem:src/main/resources/flyway/migrations"));
        configuration.setDataSource(dataSource());
        return new Flyway(configuration);
    }

    //    @Bean(destroyMethod = "close")
    @Bean
    public DataSource dataSource() {
//        HikariConfig hikariConfig = new HikariConfig();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty(Environment.DRIVER)));
        dataSource.setUrl(environment.getProperty(Environment.URL));
        dataSource.setUsername(environment.getProperty(Environment.USER));
        dataSource.setPassword(environment.getProperty(Environment.PASS));

//        hikariConfig.setConnectionTestQuery("SELECT 1");
//        hikariConfig.setPoolName("springHikariCP");

//        hikariConfig.setMaximumPoolSize(20);
//        hikariConfig.setConnectionTimeout(20_000);
//        hikariConfig.setMinimumIdle(10);
//        hikariConfig.setIdleTimeout(300_000);
//        return new HikariDataSource(hikariConfig);
        return dataSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        templateResolver.setTemplateMode(TemplateMode.HTML);;
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return resolver;
    }
}