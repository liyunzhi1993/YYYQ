package yyyq.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class YYYQPortalApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(YYYQPortalApplication.class).web(WebApplicationType.REACTIVE).run(args);
	}
}
