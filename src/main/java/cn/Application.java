package cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * @author sfpy
 */
@MapperScan("cn.college.dao")
@SpringBootApplication
@ServletComponentScan
public class Application {

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

	/**
	 * 加载yml配置文件,根目录为resources
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		yaml.setResources(new ClassPathResource[]{
				new ClassPathResource("config/db.yml"),
		});
		pspc.setProperties(yaml.getObject());
		return pspc;
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//关闭banner
		//SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
		//修改Banner的模式为OFF
		//builder.bannerMode(Banner.Mode.OFF).run(args);
	}
}
