package com.ptopalidis.cecloud.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ConfigurationPropertiesScan
@ComponentScan(basePackages = {
		"com.ptopalidis.cecloud.platform",
		"com.topcode.web",
		"com.topcode.pdfgenerator",
		"com.topcode.files"
})
@EnableJpaRepositories(basePackages = {
		"com.ptopalidis.cecloud.platform",
		"com.topcode.web",
		"com.topcode.pdfgenerator"
})
@EntityScan(basePackages = {
		"com.ptopalidis.cecloud.platform",
		"com.topcode.web",
		"com.topcode.pdfgenerator"
})
public class PlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
	}

}
