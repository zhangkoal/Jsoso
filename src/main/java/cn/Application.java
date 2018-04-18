package cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ServletComponentScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);


		//关闭banner
		//SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
		//修改Banner的模式为OFF
		//builder.bannerMode(Banner.Mode.OFF).run(args);
	}

}
