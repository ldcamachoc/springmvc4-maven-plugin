package com.accenture.web.view;

import com.accenture.model.Book;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by l.camacho.carbajal on 2/27/2017.
 */
public class ExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Book> books = (List<Book>) model.get("books");

        Sheet sheet = workbook.createSheet("Books");
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        Row row = null;
        Cell cell = null;
        int rowCount = 0;
        int colCount = 0;

        // Create header cells
        row = sheet.createRow(rowCount++);
        row = sheet.createRow(rowCount++);

        cell = row.createCell(colCount++);
        cell.setCellStyle(style);
        cell.setCellValue("Isbn");

        cell = row.createCell(colCount++);
        cell.setCellStyle(style);
        cell.setCellValue("Publication Date");

        cell = row.createCell(colCount++);
        cell.setCellStyle(style);
        cell.setCellValue("Title");

        for (Book book: books ) {
            // Create data cells
            row = sheet.createRow(rowCount++);
            colCount = 0;
            row.createCell(colCount++).setCellValue(book.getIsbn());
            row.createCell(colCount++).setCellValue(book.getDateOfPublication());
            row.createCell(colCount++).setCellValue(book.getTitle());
        }

        for (int i = 0; i < 3; i++)
            sheet.autoSizeColumn(i, true);

    }
}

