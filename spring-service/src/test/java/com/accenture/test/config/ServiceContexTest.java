package com.accenture.test.config;

import com.accenture.service.config.ServiceContext;
import com.accenture.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by l.camacho.carbajal on 2/10/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceContext.class)
public class ServiceContexTest {
    @Autowired
    protected BookService bookService;

    @Test
    public void test(){}

}
