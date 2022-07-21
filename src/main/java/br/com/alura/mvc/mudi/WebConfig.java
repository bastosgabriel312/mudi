package br.com.alura.mvc.mudi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.alura.interceptor.InterceptadorDeAcesso;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport{
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new InterceptadorDeAcesso()).addPathPatterns("/**");
	}
}
