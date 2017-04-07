package RestFul;

import org.apache.catalina.LifecycleException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizerBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;

@SpringBootApplication
@EnableScheduling
@ImportResource("classpath:applicationContext.xml")
@ConfigurationProperties(prefix = "application",locations = "classpath:application.properties") 
public class Application implements EmbeddedServletContainerCustomizer {
    public static void main(String[] args) throws LifecycleException {
    	//通过注解加载Bean
    	SpringApplication.run(Application.class, args);
//    	从Spring配置文件加载Bean
//    	SpringApplication.run("classpath:applicationContext.xml", args);
//    	SpringApplication app=new SpringApplication(Application.class);
//    	app.run(args);
    }

	public void customize(ConfigurableEmbeddedServletContainer container) {
		// TODO Auto-generated method stub
		container.setPort(9000);
	}
}