package hello.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	
	
	
}
