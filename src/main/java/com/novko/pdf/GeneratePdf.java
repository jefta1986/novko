package com.novko.pdf;

import com.itextpdf.text.DocumentException;
import com.novko.internal.orders.Order;

import javax.activation.DataSource;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

public interface GeneratePdf {

    void createPdf(String dest) throws IOException, DocumentException;
    void createPdfFromOrder(OutputStream outputStream, Order order) throws IOException, DocumentException;
    EmailModel createPdfByteArrray(Order order) throws IOException;
}
