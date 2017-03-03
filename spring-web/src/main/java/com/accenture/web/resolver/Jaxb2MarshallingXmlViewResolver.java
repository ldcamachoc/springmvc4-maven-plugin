package com.accenture.web.resolver;

import com.accenture.model.Book;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;
import org.springframework.web.servlet.view.xml.MarshallingView;


import java.util.List;
import java.util.Locale;

/**
 * Created by l.camacho.carbajal on 2/27/2017.
 */
public class Jaxb2MarshallingXmlViewResolver implements ViewResolver{

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        // Important: create XmlMapper; it will use proper factories, workarounds
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);

        MappingJackson2XmlView xmlView = new MappingJackson2XmlView(xmlMapper);
        xmlView.setPrettyPrint(true);
        xmlView.setModelKey("books");

        return xmlView;
    }
}
