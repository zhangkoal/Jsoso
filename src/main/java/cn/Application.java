package cn;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author sfpy
 */
@SpringBootApplication
@ServletComponentScan
public class Application{

	/**
	 * 监听的http请求的端口,需要在application配置中添加http.port=端口号  如80
	 */
	@Value("${http.port}")
	Integer httpPort;

	@Bean
	public WebServerFactoryCustomizer webServerFactoryCustomizer(){
		return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
			@Override public void customize(ConfigurableServletWebServerFactory factory) {
				factory.setPort(httpPort);
			}};
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//关闭banner
		//SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
		//修改Banner的模式为OFF
		//builder.bannerMode(Banner.Mode.OFF).run(args);
	}
}
