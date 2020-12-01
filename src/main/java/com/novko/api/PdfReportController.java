package com.novko.api;


import com.novko.internal.orders.Order;
import com.novko.internal.orders.OrderService;
import com.novko.pdf.EmailService;
import com.novko.pdf.GeneratePdf;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/rest/reports")
public class PdfReportController {

    @Autowired
    private GeneratePdf generatePdfImpl;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailServiceImpl;

    @Autowired
    private GeneratePdf pdfService;


    @PostMapping(value = "/save")
    @ApiOperation(value = "PDF generate and save file")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public ResponseEntity<String> savePdf(@RequestParam("id") Long id) {

        Order order = orderService.findById(id);
//        String dest = "C:\\pdfFile\\pdfSaved\\order" + order.getId() + ".pdf";
//        File file = new File(dest);
//        file.getParentFile().mkdirs();

            try {
                emailServiceImpl.sendMessageWithAttachment(
                        "jefticivan0@gmail.com",
                        "Porudzbina Green Land",
                        "Poštovani,\nUspešno ste poručili proizvode.\nU prilogu Vam šaljemo fakturu za plaćanje",
                        order);
            } catch (MessagingException e) {
                return new ResponseEntity<>("IO EXCEPTION", HttpStatus.OK);
            }

        return new ResponseEntity<>("pdf saved", HttpStatus.OK);
    }


//    @PostMapping(value = "/save")
//    @ApiOperation(value = "PDF generate and save file")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
//    public ResponseEntity<String> savePdf() {
//        String dest = "C:\\pdfFile\\pdfSaved\\pdfFile.pdf";
//        File file = new File(dest);
//        file.getParentFile().mkdirs();
//
//        try {
//            pdfService.createPdf(dest);
//        } catch (IOException e) {
//            return new ResponseEntity<>("IO EXCEPTION", HttpStatus.OK);
//        } catch (DocumentException e) {
//            return new ResponseEntity<>("DocumentException", HttpStatus.OK);
//        }
//
//    return new ResponseEntity<>("pdf saved", HttpStatus.OK);
//    }


//    @GetMapping(value = "/{id}")
//    @ResponseBody
//    public String saveReport(@PathVariable Long id) throws Exception {
//
//        Order order = orderService.findById(id);
//
//        EmailModel emailModel = generatePdfImpl.createPdfByteArrray(order);
//
//        //izmeniti
//        if (order.getUser().getLanguage().equals("SR")) {
//            emailServiceImpl.sendMessageWithAttachment(
//                    order.getUser().getUsername(),
//                    "Porudzbina Green Land",
//                    "Poštovani,\nUspešno ste poručili proizvode.\nU prilogu Vam šaljemo fakturu za plaćanje",
//                    emailModel);
//        }
//
//
//        if (order.getUser().getLanguage().equals("EN")) {
//            emailServiceImpl.sendMessageWithAttachment(
//                    order.getUser().getUsername(),
//                    "Order from Green Land",
//                    "Dear, \nIn attachment we send you receipt.",
//                    emailModel);
//        }
//
//
//        return "OK";
//    }


}
