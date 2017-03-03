package com.accenture.web.resolver;

import com.accenture.web.view.ExcelView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * Created by l.camacho.carbajal on 2/27/2017.
 */
public class ExcelViewResolver implements ViewResolver{
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        ExcelView excelView = new ExcelView();
        return excelView;
    }
}
