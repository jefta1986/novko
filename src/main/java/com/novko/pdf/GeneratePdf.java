package com.novko.pdf;

import com.itextpdf.text.DocumentException;
import com.novko.internal.orders.Order;

import java.io.IOException;
import java.io.OutputStream;

public interface GeneratePdf {

    void createPdfFromOrder(OutputStream outputStream, Order order) throws IOException, DocumentException;
}
