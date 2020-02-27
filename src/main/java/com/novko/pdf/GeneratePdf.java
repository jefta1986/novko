package com.novko.pdf;

import com.novko.internal.orders.Order;

import javax.activation.DataSource;
import java.io.IOException;
import java.util.Locale;

public interface GeneratePdf {

    void createPdf(Order order) throws IOException;
    DataSource createPdfByteArrray(Order order) throws IOException;
}
