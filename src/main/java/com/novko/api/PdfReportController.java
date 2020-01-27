package com.novko.api;


import com.novko.internal.orders.JpaOrders;
import com.novko.internal.orders.JpaOrdersRepository;
import com.novko.internal.orders.Order;
import com.novko.pdf.GeneratePdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/rest/reports")
public class PdfReportController {

    @Autowired
    private GeneratePdf generatePdfImpl;

    @Autowired
    private JpaOrdersRepository jpaOrdersRepository;



    @GetMapping(value = "/{id}")
    @ResponseBody
    public String saveReport(@PathVariable Long id) throws Exception {

        Order order = jpaOrdersRepository.get(id);

        generatePdfImpl.createPdf(order);

        return "OK";
    }



}
