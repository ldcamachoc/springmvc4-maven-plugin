package com.accenture.test.config;

import com.accenture.dao.config.PersistenceContext;
import com.accenture.dao.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by l.camacho.carbajal on 2/7/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersistenceContext.class)
public class PersistenceContextTest {
    @Autowired
    protected BookRepository bookRepository;

    @Test
    public void test(){}
}
