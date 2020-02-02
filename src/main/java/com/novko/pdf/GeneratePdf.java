package com.novko.pdf;

import com.novko.internal.orders.Order;

import java.io.IOException;
import java.util.Locale;

public interface GeneratePdf {

    void createPdf(Order order) throws IOException;
}
