package com.accenture.web.view;

import com.accenture.model.Book;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static org.hibernate.type.descriptor.java.JdbcDateTypeDescriptor.DATE_FORMAT;

/**
 * Created by l.camacho.carbajal on 2/27/2017.
 */
public class ItextPdfView extends AbstractITextPdfView{
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>) model.get("books");

        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{10, 60, 30});

        table.addCell("ISBN");
        table.addCell("Publication Date");
        table.addCell("Title");

        for (Book book : books){
            table.addCell(book.getIsbn());
            table.addCell(String.valueOf(book.getDateOfPublication()));
            table.addCell(book.getTitle());
        }

        document.add(table);
    }
}
