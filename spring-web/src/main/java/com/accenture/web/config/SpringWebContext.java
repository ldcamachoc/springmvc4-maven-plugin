package com.accenture.web.config;

import com.accenture.model.Book;
import com.accenture.web.resolver.ExcelViewResolver;
import com.accenture.web.resolver.JsonViewResolver;
import com.accenture.web.resolver.PDFViewResolver;
import com.accenture.web.resolver.Jaxb2MarshallingXmlViewResolver;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.accenture.web.controller" })
public class SpringWebContext extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");		
	}
	
	/**
	 * Configure MessageSource to lookup any validation/error message in
	 * internationalized property files
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/messages");
		return messageSource;
	}
	
	@Bean
	public SpringResourceTemplateResolver templateResolver(){
	    // SpringResourceTemplateResolver automatically integrates with Spring's own
	    // resource resolution infrastructure, which is highly recommended.
	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setApplicationContext(this.applicationContext);
	    templateResolver.setPrefix("/WEB-INF/views/");
	    templateResolver.setSuffix(".html");
	    // HTML is the default value, added here for the sake of clarity.
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    // Template cache is true by default. Set to false if you want
	    // templates to be automatically updated when modified.
	    templateResolver.setCacheable(false);
	    return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine(){
	    // SpringTemplateEngine automatically applies SpringStandardDialect and
	    // enables Spring's own MessageSource message resolution mechanisms.
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver());
	    // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
	    // speed up execution in most scenarios, but might be incompatible
	    // with specific cases when expressions in one template are reused
	    // across different data types, so this flag is "false" by default
	    // for safer backwards compatibility.
	    templateEngine.setEnableSpringELCompiler(true);
	    templateEngine.addDialect(new LayoutDialect(new GroupingStrategy()));
	    return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(){
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    viewResolver.setCharacterEncoding("UTF-8");	
	    return viewResolver;
	}


	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer
				.defaultContentType(MediaType.TEXT_HTML)
				.parameterName("type")
				.favorParameter(true)
				.ignoreUnknownPathExtensions(false)
				.ignoreAcceptHeader(false)
				.useJaf(true);
	}

    @Bean
    public ViewResolver xmlViewResolver() {
       return new Jaxb2MarshallingXmlViewResolver();
    }

    @Bean
    public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
    }

    @Bean
    public ViewResolver pdfViewResolver() {
		return new PDFViewResolver();
    }
    @Bean
    public ViewResolver xlsViewResolver(){

		return new ExcelViewResolver();
	}

    /*
     * Configure ContentNegotiatingViewResolver
     */
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        // Define all possible view resolvers
        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

        resolvers.add(xmlViewResolver());
        resolvers.add(jsonViewResolver());
        resolvers.add(pdfViewResolver());
        resolvers.add(xlsViewResolver());
        resolvers.add(thymeleafViewResolver());

        resolver.setViewResolvers(resolvers);
        return resolver;
    }



	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;		
	}

}
