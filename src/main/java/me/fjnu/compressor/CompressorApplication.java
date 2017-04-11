package me.fjnu.compressor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * spring boot 应用启动入口
 */
@SpringBootApplication
public class CompressorApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CompressorApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CompressorApplication.class, args);
	}
}
