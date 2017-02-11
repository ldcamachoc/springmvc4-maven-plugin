package com.accenture.service.config;

import com.accenture.dao.config.PersistenceContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by l.camacho.carbajal on 2/10/2017.
 */
@Configuration
@Import(PersistenceContext.class)  //Import config class from spring-data
@ComponentScan(basePackages = "com.accenture.service")
public class ServiceContext {

}
