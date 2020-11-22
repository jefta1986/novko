package com.novko.api;


import com.novko.internal.orders.Order;
import com.novko.internal.orders.OrderService;
import com.novko.pdf.EmailModel;
import com.novko.pdf.EmailService;
import com.novko.pdf.GeneratePdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/reports")
public class PdfReportController {

    @Autowired
    private GeneratePdf generatePdfImpl;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailServiceImpl;


    @GetMapping(value = "/{id}")
    @ResponseBody
    public String saveReport(@PathVariable Long id) throws Exception {

        Order order = orderService.findById(id);

        EmailModel emailModel = generatePdfImpl.createPdfByteArrray(order);

        //izmeniti
        if (order.getUser().getLanguage().equals("SR")) {
            emailServiceImpl.sendMessageWithAttachment(
                    order.getUser().getUsername(),
                    "Porudzbina Green Land",
                    "Poštovani,\nUspešno ste poručili proizvode.\nU prilogu Vam šaljemo fakturu za plaćanje",
                    emailModel);
        }


        if (order.getUser().getLanguage().equals("EN")) {
            emailServiceImpl.sendMessageWithAttachment(
                    order.getUser().getUsername(),
                    "Order from Green Land",
                    "Dear, \nIn attachment we send you receipt.",
                    emailModel);
        }


        return "OK";
    }


}
